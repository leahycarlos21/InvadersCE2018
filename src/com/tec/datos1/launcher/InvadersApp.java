package com.tec.datos1.launcher;

import com.tec.datos1.ClasesInvasores.*;
import com.tec.datos1.ClasesInvasores.ADT.Nodo;
import com.tec.datos1.Enemigos.JuegoObjeto;
import com.tec.datos1.Jugabilidad.ControlNivel;
import com.tec.datos1.Jugabilidad.TCPServer;

import javafx.scene.input.KeyCode;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.awt.*;
import java.awt.event.KeyEvent;

public class InvadersApp extends Application {

    private Pane ventana;
    private Jugador jugador = Jugador.getInstance();
    private JuegoObjeto[] balas = new JuegoObjeto[20];
    private int posicionBala = 0;
    double velocidad = 1;
    boolean cambio1 = true;//cambio al llegar al extremo derecho
    boolean cambio2 = false;//Cambio al llegar al extremo izquiedo
    boolean estadoJuego = true;
    boolean reset = false;
    String posicionApp;
    boolean disparoApp = false;
    Text text2 = new Text();
    public TCPServer serveris = new TCPServer();
    private int avanzaNivel = 1;

    /**
     * ControlNivel es donde se ingresan los niveles con los que va a luchar el jugador
     */
    private ControlNivel nivelHorda = new ControlNivel(avanzaNivel);
    //posHorda ubica el array de enemigos de nivelHorda
    private int posHorda = 0;
    private Clase horda;

    String nameHorda = nivelHorda.getNameHorda()[posHorda];
    String nameHordaActual = nivelHorda.getNameHorda()[posHorda];
    private String datosJugador = "Nivel: " + 1 + "Puntaje: " + 0;
    Text text1 = new Text(25, 25, datosJugador);


    private Parent createContent() {

        /** Definiendo las dimensiones de la ventana principal */
        ventana = new Pane();
        ventana.setPrefSize(600, 600);

        addJuegoObjeto(jugador.getJugadorObjeto(), 300, 500);
        /**
         * Genera las balas que se van a utilizar, las posiciones, se reutilizan
         */
        for (int i = 0; i < balas.length; i++) {
            balas[i] = new Bala();
        }

        text1.setFill(Color.CHOCOLATE);
        text1.setFont(Font.font(java.awt.Font.SERIF, 25));
        ventana.getChildren().add(text1);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    onUpdate();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();
        return ventana;
    }


    /**
     * @param bala añade una bala que sale desde la posicion del jugador
     * @param pos
     * @param x
     * @param y
     */
    private void addBala(JuegoObjeto bala, int pos, double x, double y) {
        balas[pos] = bala;
        addJuegoObjeto(bala, x, y);
    }


    private void addJuegoObjeto(JuegoObjeto object, double x, double y) {
        object.getVista().setTranslateX(x);
        object.getVista().setTranslateY(y);
        ventana.getChildren().add(object.getVista());
    }

    /**
     * Añade los enemigos que hay en toda una lista
     *
     * @param objecto
     */
    private void addJuegoObjeto(Clase objecto) {
        if (horda instanceof ClaseC) {//Los tipos ClaseC son Listas Circulares
            Nodo auxNodo = objecto.getListaEnemigos().getRaiz();
            ventana.getChildren().add(auxNodo.getDato().getEnemigoObjeto().getVista());
            auxNodo = auxNodo.getSiguiente();
            while (auxNodo != horda.getListaEnemigos().getRaiz()) {
                ventana.getChildren().add(auxNodo.getDato().getEnemigoObjeto().getVista());
                auxNodo = auxNodo.getSiguiente();

            }
        } else {//Las claseBasic e hijas, son listas dobles
            Nodo auxNodo = objecto.getListaEnemigos().getRaiz();
            while (auxNodo != null) {
                ventana.getChildren().add(auxNodo.getDato().getEnemigoObjeto().getVista());
                auxNodo = auxNodo.getSiguiente();
            }
        }
    }

    /**
     * Esta clase es la que crea los objetos bala en la interfaz
     */
    private static class Bala extends JuegoObjeto {
        Bala() {
            super(new Circle(5, 4, 5, Color.BLACK));
        }
    }

    /**
     * Es el método que tiene la lógica del juego.
     *
     * @throws InterruptedException
     */
    private void onUpdate() throws InterruptedException {
        /**
         * Movimiento con app
         */
        if (serveris.isDisparo() == true) {
            disparar();
            serveris.setDisparo(false);
            disparoApp = true;
        }
        if (serveris.getPosicion() == "Izquierda") {
            if (jugador.getJugadorObjeto().getVista().getBoundsInParent().getMaxX() > 40.0)
                jugador.getJugadorObjeto().movIzq();

        }
        if (serveris.getPosicion() == "Derecha") {
            if (jugador.getJugadorObjeto().getVista().getBoundsInParent().getMaxX() < 600.0)
                jugador.getJugadorObjeto().movDer();
        }

        posicionApp = serveris.getPosicion();


        if (reset == true && estadoJuego == false) {
            resetJuego();
            ventana.getChildren().remove(text2);


        }
        /**Texto de interfaz*/
        ventana.getChildren().remove(text1);//Quita el texto definido en los atributos de esta clase

        if (posHorda != 3)
            nameHordaActual = nivelHorda.getNameHorda()[posHorda];

        if (posHorda != 2) {
            nameHorda = nivelHorda.getNameHorda()[posHorda + 1];
        } else if (posHorda == 2 && avanzaNivel != 3) {
            ControlNivel auxDato = new ControlNivel(avanzaNivel + 1);
            nameHorda = auxDato.getNameHorda()[0];

        } else {
            nameHordaActual = nivelHorda.getNameHorda()[posHorda];

            nameHorda = "FINAL";
        }

        datosJugador = "Nivel: " + this.jugador.getEstadisticas().getNivelAlcanzado() + "- Puntaje: " + this.jugador.getEstadisticas().getPuntaje() +
                " Actual:" + nameHordaActual +
                " Siguiente:" + nameHorda;
        serveris.setDatosJuego(datosJugador);

        // ventana.getChildren().remove(text1);
        text1 = new Text(10, 25, datosJugador);
        text1.setFill(Color.CHOCOLATE);
        text1.setFont(Font.font(java.awt.Font.SERIF, 25));
        ventana.getChildren().add(text1);
        /**Fin de texto de interfaz*/

        if (this.jugador.getEstadisticas().getNivelAlcanzado() == 0) {
            horda = nivelHorda.getHordaEnemigos()[posHorda];
            horda.setCoordenas(5, 100);
            addJuegoObjeto(horda);
            this.jugador.getEstadisticas().setNivelAlcanzado(nivelHorda.getNivel());

        }  if (posHorda == 2 && horda.cantidadLista() == 0 && avanzaNivel == 3) {
            String GG = "\n GG Prrooo  Ganaste:" + "Nivel: " + this.jugador.getEstadisticas().getNivelAlcanzado() + "- Puntaje: " + this.jugador.getEstadisticas().getPuntaje();
            text2 = new Text(100, 200, GG);
            text2.setFill(Color.CHOCOLATE);
            text2.setFont(Font.font(java.awt.Font.SERIF, 25));

            ventana.getChildren().setAll(text2);
           // Thread.sleep(5000);

            estadoJuego = false;



        } else if (horda.cantidadLista() == 0 && estadoJuego == true) {
            if (velocidad < 0)// si va para la izquierda ocupo restarle velocidad porque va negativo
                velocidad -= 0.2;
            velocidad += 0.2;
            posHorda += 1;
            if (posHorda == 3) {
                avanzaNivel++;
                if (avanzaNivel == 4) {
                    System.out.print("Ganaste");
                } else {
                    this.jugador.getEstadisticas().setNivelAlcanzado(this.jugador.getEstadisticas().getNivelAlcanzado() + 1);
                    nivelHorda = new ControlNivel(avanzaNivel);
                    posHorda = 0;
                }
            } else {
                horda = nivelHorda.getHordaEnemigos()[posHorda];
                horda.setCoordenas(5, 100);
                addJuegoObjeto(horda);
            }

        }
        int posEnemigoEliminar = 0; //Al detectar a un enemigo destruido, se define su posicon

        int pos = 0;
        for (int i = 0; i < balas.length; i++) {
            Nodo auxLista = horda.getListaEnemigos().getRaiz();

            if (horda instanceof ClaseC)
                pos = 0;
            else if (horda instanceof ClaseBasic)
                pos = 1;

            for (int b = 1; b <= horda.cantidadLista(); b++) {
                if (balas[i].colision(auxLista.getDato().getEnemigoObjeto())) {
                    balas[i].setMuerto();

                    horda.getListaEnemigos().obtenerDato(pos).getEnemigoObjeto().restarVida();
                    horda.getListaEnemigos().obtenerDato(pos).setVida(horda.getListaEnemigos().obtenerDato(pos).getEnemigoObjeto().getVida());

                    if (horda.getListaEnemigos().obtenerDato(pos).getTipo() == 1 && horda instanceof ClaseC
                            && horda.getListaEnemigos().obtenerDato(pos).getEnemigoObjeto().getVida() == 0) {
                        if (horda.getListaEnemigos().cantidad() > 1) {
                            ((ClaseC) horda).intercambiar(pos);
                        }


                    }
                    if (horda.getListaEnemigos().obtenerDato(pos).getEnemigoObjeto().getVida() == 0 || horda.getListaEnemigos().cantidad() == 1) {

                        posEnemigoEliminar = pos;
                        if (posEnemigoEliminar == 0)
                            posEnemigoEliminar = 111;
                        this.jugador.getEstadisticas().setPuntaje(this.jugador.getEstadisticas().getPuntaje() + 1);
                        Thread.sleep(100);
                        this.ventana.getChildren().removeAll(new Node[]{balas[i].getVista(), horda.getListaEnemigos().obtenerDato(pos).getEnemigoObjeto().getVista()});
                    }
                    break;
                }
                auxLista = auxLista.getSiguiente();
                pos++;
            }
        }//for

        /**
         *FUncion que quita o deja las balas que  han chocado o no
         */
        for (int i = 0; i < balas.length; i++) {
            if (balas[i].isVivo() == false) {
                balas[i].setPosicion(-100, -100);
            }
            balas[i].update();
        }
        /** Elimina al enemigo */

        if (posEnemigoEliminar == 111)
            horda.eliminarPosicion(0);
        else if (posEnemigoEliminar != 0) {

            horda.eliminarPosicion(posEnemigoEliminar);

        }
        /**Ordena a los enemigos hacia el centro*/
        if (horda.cantidadLista() != 0 && posEnemigoEliminar != 0) {
            if (posEnemigoEliminar == 111)
                horda.setCoordenadas(posEnemigoEliminar);
            else
                horda.setCoordenadas(posEnemigoEliminar);
        }
        /***/
        if (horda instanceof ClaseB || horda instanceof ClaseA || horda instanceof ClaseD) {
            horda.actualizarDatos(ventana);
        }
        this.jugador.getJugadorObjeto().update();//Actualiza constantemente la posicion del jugador al moverlo

        int num = 1;
        int posfinal = 0;
        int posinicial = 0;
        /**
         *Ejecutará solamente si es de claseBasic o las sus clases hijas
         */
        if (horda instanceof ClaseBasic) {//Solo clases que tienen listas dobles
            num = 1;
            posfinal = horda.cantidadLista();
            posinicial = 1;
        }
        if (horda instanceof ClaseC) {//Solo clases que tienen listas circulares
            num = 0;
            posfinal = horda.cantidadLista() - 1;
            posinicial = 0;
        }


        while (num <= horda.cantidadLista() && horda instanceof ClaseBasic || num < horda.cantidadLista() && horda instanceof ClaseC) {
            if (horda.getListaEnemigos().obtenerDato(posfinal).getEnemigoObjeto().getPosicion()[1] > 430 && estadoJuego == true) {

                String GameOver = "\n Perdiste: Nivel: " + this.jugador.getEstadisticas().getNivelAlcanzado() + "- Puntaje: " + this.jugador.getEstadisticas().getPuntaje();
                text2 = new Text(200, 200, GameOver);
                text2.setFill(Color.RED);
                text2.setFont(Font.font(java.awt.Font.SERIF, 25));
                ventana.getChildren().add(text2);
                /**
                 * Limpia los enemigos al perder
                 */
                int largoInterfaz = horda.getListaEnemigos().cantidad();
                if (horda instanceof ClaseBasic) {
                    for (int i = 1; i <= largoInterfaz; i++) {
                        ventana.getChildren().removeAll(horda.getListaEnemigos().getRaiz().getDato().getEnemigoObjeto().getVista());
                        horda.eliminarPosicion(1);
                    }
                } else {
                    for (int i = 0; i <= largoInterfaz; i++) {
                        ventana.getChildren().removeAll(horda.getListaEnemigos().getRaiz().getDato().getEnemigoObjeto().getVista());
                        horda.eliminarPosicion(0);
                    }
                }

                velocidad = 0;
                estadoJuego = false;
                break;
            }
            if (horda.getListaEnemigos().obtenerDato(posfinal).getEnemigoObjeto().getPosicion()[0] > 558 && cambio1 == true) {
                cambio1 = false;
                cambio2 = true;
                velocidad *= -1;
                num = 1;
                resetVelocidad();
            } else if (horda.getListaEnemigos().obtenerDato(posinicial).getEnemigoObjeto().getPosicion()[0] < 1
                    && cambio2 == true) {
                cambio1 = true;
                cambio2 = false;
                velocidad *= -1;
                num = 1;
                resetVelocidad();

            }


            horda.getListaEnemigos().obtenerDato(num).getEnemigoObjeto().setVelocidad(new Point2D(velocidad, 0));
            horda.getListaEnemigos().obtenerDato(num).getEnemigoObjeto().update();
            num++;
        }
    }

    /**
     * Hace que la velocidad sea cero, baje de fila de cada uno de los enemigos
     */


    public void resetVelocidad() {
        for (int ii = 1; ii <= horda.cantidadLista(); ii++) {
            horda.getListaEnemigos().obtenerDato(ii).getEnemigoObjeto().setVelocidad(new Point2D(0, 0));
            double[] pos = horda.getListaEnemigos().obtenerDato(ii).getEnemigoObjeto().getPosicion();
            horda.getListaEnemigos().obtenerDato(ii).getEnemigoObjeto().setPosicion(pos[0], pos[1] + 30);
        }
    }

    public void disparar() {
        JuegoObjeto bala = new Bala();
        /**Reutilzia los espacios del array balas, para
         ** imprimirlas en la pantalla*/
        if (posicionBala >= balas.length) {
            posicionBala = 0;
        }
        bala.setVelocidad(jugador.getJugadorObjeto().getVelocidad().normalize().add(0, -5));
        addBala(bala, posicionBala, jugador.getJugadorObjeto().getVista().getTranslateX() + 15, jugador.getJugadorObjeto().getVista().getTranslateY());
        posicionBala += 1;
    }

    public class myHilo extends Thread {
        public void run() {
            serveris.TCPServer();

        }
    }

    public void resetJuego() {
        ventana.getChildren().remove(text1);//Quita el texto definido en los atributos de esta clase
        ventana.getChildren().remove(text2);//Quita el texto definido en los atributos de esta clase


        //posHorda ubica el array de enemigos de nivelHorda
        velocidad = 1;
        cambio1 = true;//cambio al llegar al extremo derecho
        cambio2 = false;//Cambio al llegar al extremo izquiedo
        estadoJuego = true;
        reset = false;
        jugador.getEstadisticas().setPuntaje(0);

        avanzaNivel = 1;


        nivelHorda = new ControlNivel(1);
        posHorda = 0;
        nameHorda = nivelHorda.getNameHorda()[posHorda];
        nameHordaActual = nivelHorda.getNameHorda()[posHorda];
        datosJugador = "Nivel: " + 1 + "Puntaje: " + 0;
        text1 = new Text(25, 25, datosJugador);
        this.jugador.getEstadisticas().setPuntaje(0);
        this.jugador.getEstadisticas().setNivelAlcanzado(0);
    }

    @Override
    public void start(Stage stage) {

        myHilo hilo = new myHilo();
        hilo.start();
        stage.setTitle("InvadersCE Carlos Leahy 2018");
        if (estadoJuego == true)
            stage.setScene(new Scene(createContent()));


        stage.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) {
                if (jugador.getJugadorObjeto().getVista().getBoundsInParent().getMaxX() > 40.0)
                    jugador.getJugadorObjeto().movIzq();

            } else if (e.getCode() == KeyCode.RIGHT) {
                if (jugador.getJugadorObjeto().getVista().getBoundsInParent().getMaxX() < 600.0)
                    jugador.getJugadorObjeto().movDer();
            }

        });


        stage.getScene().setOnKeyReleased(e -> {


            if (estadoJuego == false && e.getCode() == KeyCode.ENTER) {
                ventana.getChildren().remove(text2);
                reset = true;


            } else if (e.getCode() == KeyCode.SPACE) {
                disparar();


            } else if (e.getCode() == KeyCode.S) {
                disparar();


            }
        });
        stage.show();

    }
}
