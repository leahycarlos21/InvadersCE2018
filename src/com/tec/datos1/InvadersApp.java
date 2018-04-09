package com.tec.datos1;

import com.tec.datos1.ClasesHordas.*;
import com.tec.datos1.ClasesHordas.ADT.Nodo;
import com.tec.datos1.ClasesHordas.ADT.NodoDoble;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.util.Optional;
import java.util.Random;

public class InvadersApp extends Application {

    private Pane ventana;
    private Jugador jugador = new Jugador();
    private JuegoObjeto[] balas = new JuegoObjeto[10];
    private int posicionBala = 0;
    double velocidad = 1;
    boolean cambio1 = true;//cambio al llegar al extremo derecho
    boolean cambio2 = false;//Cambio al llegar al extremo izquiedo
    boolean estadoJuego = true;
    // ClaseBasic Prueba1 = new ClaseBasic(1);
    // ClaseBasic Prueba1 = new ClaseA(1);
    //  Clase Prueba1 = new ClaseD();
    private int avanzaNivel = 1;

    private ControlNivel nivelHorda = new ControlNivel(avanzaNivel);
    private int posHorda = 0;
    Clase horda;//= nivelHorda.getHordaEnemigos()[posHorda];

    String nameHorda = nivelHorda.getNameHorda()[posHorda];
    private String datosJugador = "Nivel: " + 1 + "Puntaje: " + 0;
    Text text1 = new Text(25, 25, datosJugador);


    private Parent createContent() {
        System.out.print(nameHorda);
        /* Definiendo las dimensiones de la ventana principal */
        ventana = new Pane();
        ventana.setPrefSize(600, 600);
        //jugador = new JuegoObjeto(new Rectangle(40, 20, Color.GRAY));

        addJuegoObjeto(jugador.getJugadorObjeto(), 300, 500);

        balas[0] = new Bala();
        balas[1] = new Bala();
        balas[2] = new Bala();
        balas[3] = new Bala();
        balas[4] = new Bala();
        balas[5] = new Bala();
        balas[6] = new Bala();
        balas[7] = new Bala();
        balas[8] = new Bala();
        balas[9] = new Bala();
        final Group rootGroup = new Group();
        //String datosJugador=datosJugador = "Nivel: "+this.jugador.getEstadisticas().getNivelAlcanzado()+"Puntaje: "+this.jugador.getEstadisticas().getPuntaje();
        //final Text text1 = new Text(25, 25, datosJugador);
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
     * @param bala añade una vale que sale desde la posicion del jugador
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
        System.out.println("ID es  " + horda.tipoLista());
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
                //ventana.getChildren().add(objecto.getVista());
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
        /**Texto de interfaz*/
        ventana.getChildren().remove(text1);//Quita el texto definido en los atributos de esta clase
        datosJugador = "Nivel: " + this.jugador.getEstadisticas().getNivelAlcanzado() + "- Puntaje: " + this.jugador.getEstadisticas().getPuntaje();
        text1 = new Text(25, 25, datosJugador);
        text1.setFill(Color.CHOCOLATE);
        text1.setFont(Font.font(java.awt.Font.SERIF, 25));
        ventana.getChildren().add(text1);
        /**Fin de texto de interfaz*/

        if (this.jugador.getEstadisticas().getNivelAlcanzado() == 0) {
            horda = nivelHorda.getHordaEnemigos()[posHorda]; ////////------------------------------------------------CAMBIAR ESTO
            horda.setCoordenas(5, 100);
            addJuegoObjeto(horda);
            this.jugador.getEstadisticas().setNivelAlcanzado(nivelHorda.nivel);
            System.out.println("Salio");


        }
        else if(posHorda == 2 && horda.cantidadLista()==0 && avanzaNivel==3){
            System.out.print("Ganaste");
            String GameOver = "\n GG Prrooo  Ganaste:" + "Nivel: " + this.jugador.getEstadisticas().getNivelAlcanzado() + "- Puntaje: " + this.jugador.getEstadisticas().getPuntaje();
            Text text2 = new Text(200, 200, GameOver);
            text2.setFill(Color.CHOCOLATE);
            text2.setFont(Font.font(java.awt.Font.SERIF, 25));
            ventana.getChildren().add(text2);

        }

        else if (horda.cantidadLista() == 0) {

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
            }
             else {
                horda = nivelHorda.getHordaEnemigos()[posHorda]; ////////------------------------------------------------CAMBIAR ESTO
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
                    System.out.println("------PosEnemigo1----" + posEnemigoEliminar);
                    System.out.println("Enemigo>>> " + horda.getListaEnemigos().obtenerDato(0).getVida());


                    System.out.println("Vida es " + horda.getListaEnemigos().obtenerDato(pos).getEnemigoObjeto().getVida());
                    horda.getListaEnemigos().obtenerDato(pos).getEnemigoObjeto().restarVida();
                    horda.getListaEnemigos().obtenerDato(pos).setVida(horda.getListaEnemigos().obtenerDato(pos).getEnemigoObjeto().getVida());///VER esto-------------------------

                    if (horda.getListaEnemigos().obtenerDato(pos).getTipo() == 1 && horda instanceof ClaseC
                            && horda.getListaEnemigos().obtenerDato(pos).getEnemigoObjeto().getVida() == 0) {

                        ((ClaseC) horda).intercambiar(pos);
                        //Hacer que cambien los maes
                        System.out.println("Vida del mae----------" + horda.getListaEnemigos().obtenerDato(pos).getEnemigoObjeto().getVida());


                    }
                    if (horda.getListaEnemigos().obtenerDato(pos).getEnemigoObjeto().getVida() == 0 || horda.getListaEnemigos().cantidad() == 1) {

                        posEnemigoEliminar = pos;
                        if (posEnemigoEliminar == 0)
                            posEnemigoEliminar = 111;
                        System.out.println("----A eneminarrr---------" + posEnemigoEliminar);
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
        //System.out.println("PosicionEliminada-------------" + posEnemigoEliminar);


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
            if (horda.getListaEnemigos().obtenerDato(posfinal).getEnemigoObjeto().getPosicion()[1] > 430) {
                System.out.println("Your dead");
                velocidad = 0;
                //resetVelocidad();
                estadoJuego = false;
            }
            if (horda.getListaEnemigos().obtenerDato(posfinal).getEnemigoObjeto().getPosicion()[0] > 558 && cambio1 == true) {
                cambio1 = false;
                cambio2 = true;
                velocidad *= -1;
                num = 1;
                resetVelocidad();
                System.out.println("");
            } else if (horda.getListaEnemigos().obtenerDato(posinicial).getEnemigoObjeto().getPosicion()[0] < 1
                    && cambio2 == true) {
                cambio1 = true;
                cambio2 = false;
                velocidad *= -1;
                num = 1;
                resetVelocidad();

            }

            if (estadoJuego == false) {
                String GameOver = "\n Perdiste:" + "Nivel: " + this.jugador.getEstadisticas().getNivelAlcanzado() + "- Puntaje: " + this.jugador.getEstadisticas().getPuntaje();
                Text text2 = new Text(200, 200, GameOver);
                text2.setFill(Color.CHOCOLATE);
                text2.setFont(Font.font(java.awt.Font.SERIF, 25));
                ventana.getChildren().add(text2);

            }

            horda.getListaEnemigos().obtenerDato(num).getEnemigoObjeto().setVelocidad(new Point2D(velocidad, 0));
            horda.getListaEnemigos().obtenerDato(num).getEnemigoObjeto().update();
            num++;

        }/*
        if (Prueba1 instanceof ClaseBasic) {
            while (num <= Prueba1.cantidadLista()) {
                if (Prueba1.getListaEnemigos().obtenerDato(Prueba1.cantidadLista()).getEnemigoObjeto().getPosicion()[1] > 430) {
                    System.out.println("Your dead");
                    velocidad = 0;
                    //resetVelocidad();
                    estadoJuego = false;
                } else if (Prueba1.getListaEnemigos().obtenerDato(Prueba1.cantidadLista()).getEnemigoObjeto().getPosicion()[0] > 558 && cambio1 == true) {
                    cambio1 = false;
                    cambio2 = true;
                    velocidad *= -1;
                    num = 1;
                    resetVelocidad();

                } else if (Prueba1.getListaEnemigos().obtenerDato(1).getEnemigoObjeto().getPosicion()[0] < 1
                        && cambio2 == true) {
                    cambio1 = true;
                    cambio2 = false;
                    velocidad *= -1;
                    System.out.println("ENTRO2222");
                    num = 1;
                    resetVelocidad();

                }

                // * Si la fila llega hasta el final, jugador pierde

                if (estadoJuego == false) {
                    System.out.print("------------------------");
                    String GameOver = "\n Perdiste:" + "Nivel: " + this.jugador.getEstadisticas().getNivelAlcanzado() + "- Puntaje: " + this.jugador.getEstadisticas().getPuntaje();
                    Text text2 = new Text(200, 200, GameOver);
                    text2.setFill(Color.CHOCOLATE);
                    text2.setFont(Font.font(java.awt.Font.SERIF, 25));
                    ventana.getChildren().add(text2);

                }


                Prueba1.getListaEnemigos().obtenerDato(num).getEnemigoObjeto().setVelocidad(new Point2D(velocidad, 0));
                Prueba1.getListaEnemigos().obtenerDato(num).getEnemigoObjeto().update();
                num++;
            }
        }*/

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


    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("InvadersCE Carlos Leahy 2018");


        stage.setScene(new Scene(createContent()));


        stage.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) {
                if (jugador.getJugadorObjeto().getVista().getBoundsInParent().getMaxX() > 40.0)
                    jugador.getJugadorObjeto().movIzq();

            } else if (e.getCode() == KeyCode.RIGHT) {
                if (jugador.getJugadorObjeto().getVista().getBoundsInParent().getMaxX() < 600.0)
                    jugador.getJugadorObjeto().movDer();


            } else if (e.getCode() == KeyCode.SPACE) {
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
        });
        stage.show();
    }
}
