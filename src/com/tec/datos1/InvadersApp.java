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
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.Font;

public class InvadersApp extends Application {

    private Pane ventana;
    // private JuegoObjeto jugador;
    private Jugador jugador = new Jugador();
    private JuegoObjeto[] balas = new JuegoObjeto[10];
    private int posicionBala = 0;
    // int //nivel = 1;
    double velocidad = 1;
    boolean cambio1 = true;
    boolean cambio2 = false;
    boolean estadoJuego = true;
    // ClaseBasic Prueba1 = new ClaseBasic(1);
    // ClaseBasic Prueba1 = new ClaseA(1);
   // Clase Prueba1 = new ClaseB(1);
     Clase Prueba1 = new ClaseB(1);
    private String datosJugador = "Nivel: " + 1 + "Puntaje: " + 0;
    Text text1 = new Text(25, 25, datosJugador);


    private Parent createContent() {
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

    private void addJuegoObjeto(JuegoObjeto objecto) {
        ventana.getChildren().add(objecto.getVista());
    }

    /**
     * Añade los enemigos que hay en toda una lista
     *
     * @param objecto
     */
    private void addJuegoObjeto(Clase objecto) {
        System.out.println("ID es  " + Prueba1.tipoLista());
        if (Prueba1 instanceof ClaseC) {//Los tipos ClaseC son Listas Circulares
            System.out.println("ID es  " + Prueba1.tipoLista());
            Nodo auxNodo = objecto.getListaEnemigos().getRaiz();
            for (int i = 1; i <= objecto.cantidadLista(); i++) {
                ventana.getChildren().add(auxNodo.getDato().getEnemigoObjeto().getVista());//este es el ultimo
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
            //Prueba1 = new ClaseBasic(1);

            Prueba1 = new ClaseB(1);////////------------------------------------------------CAMBIAR ESTO

            Prueba1.setCoordenas(5, 100);
            addJuegoObjeto(Prueba1);
            this.jugador.getEstadisticas().setNivelAlcanzado(this.jugador.getEstadisticas().getNivelAlcanzado() + 1);

            System.out.println("Salio");

        } else if (this.jugador.getEstadisticas().getNivelAlcanzado() == 100) {

            System.out.println("Ganaste prro");

        } else if (Prueba1.cantidadLista() == 0) {
            System.out.print(Prueba1.cantidadLista());
            this.jugador.getEstadisticas().setNivelAlcanzado(this.jugador.getEstadisticas().getNivelAlcanzado() + 1);
        }

        int posEnemigoEliminar = 0; //Al detectar a un enemigo destruido, se define su posicon

        int pos = 0;
        for (int i = 0; i < balas.length; i++) {
            Nodo auxLista = Prueba1.getListaEnemigos().getRaiz();

            if (Prueba1 instanceof ClaseC)
                pos = 0;
            else if (Prueba1 instanceof ClaseBasic)
                pos = 1;

            for (int b = 1; b <= Prueba1.cantidadLista(); b++) {
                if (balas[i].colision(auxLista.getDato().getEnemigoObjeto())) {
                    balas[i].setMuerto();
                    System.out.println("Vida es " + Prueba1.getListaEnemigos().obtenerDato(pos).getEnemigoObjeto().getVida());
                    Prueba1.getListaEnemigos().obtenerDato(pos).getEnemigoObjeto().restarVida();
                    Prueba1.getListaEnemigos().obtenerDato(pos).setVida(Prueba1.getListaEnemigos().obtenerDato(pos).getEnemigoObjeto().getVida());///VER esto-------------------------

                    if (Prueba1.getListaEnemigos().obtenerDato(pos).getEnemigoObjeto().getVida() == 0) {
                        posEnemigoEliminar = pos;
                        this.jugador.getEstadisticas().setPuntaje(this.jugador.getEstadisticas().getPuntaje() + 1);
                        Thread.sleep(100);
                        this.ventana.getChildren().removeAll(new Node[]{balas[i].getVista(), Prueba1.getListaEnemigos().obtenerDato(pos).getEnemigoObjeto().getVista()});
                    }
                    break;
                }
                auxLista = auxLista.getSiguiente();
                pos++;
            }
        }//for
        //     }
/*
        int posEnemigoEliminar = 0;
        for (int i = 0; i < balas.length; i++) {
            Nodo auxLista = Prueba1.getListaEnemigos().getRaiz();
            int pos = 0;
            while (auxLista != null) {
                if (balas[i].colision(auxLista.getDato().getEnemigoObjeto())) {

                    balas[i].setMuerto();
                    System.out.println("Vida es " + Prueba1.getListaEnemigos().obtenerDato(pos + 1).getEnemigoObjeto().getVida());
                    Prueba1.getListaEnemigos().obtenerDato(pos + 1).getEnemigoObjeto().restarVida();
                    Prueba1.getListaEnemigos().obtenerDato(pos + 1).setVida(Prueba1.getListaEnemigos().obtenerDato(pos + 1).getEnemigoObjeto().getVida());///VER esto-------------------------
                    System.out.println("Vida es " + Prueba1.getListaEnemigos().obtenerDato(pos + 1).getEnemigoObjeto().getVida());
                    if (Prueba1.getListaEnemigos().obtenerDato(pos + 1).getEnemigoObjeto().getVida() == 0) {
                        // Prueba1.getListaEnemigos().obtenerDato(pos + 1).setMuerto();

                        posEnemigoEliminar = pos + 1;
                        //ver esta madrePrueba1.getListaEnemigos().obtenerDato(posEnemigoEliminar).cambioColor();
                        this.jugador.getEstadisticas().setPuntaje(this.jugador.getEstadisticas().getPuntaje() + 1);

                        Thread.sleep(100);

                        this.ventana.getChildren().removeAll(new Node[]{balas[i].getVista(), Prueba1.getListaEnemigos().obtenerDato(pos + 1).getEnemigoObjeto().getVista()});


                    }

                    break;
                }
                auxLista = auxLista.getSiguiente();
                pos++;
            }
        }
        */
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
        if (posEnemigoEliminar != 0) {
            Prueba1.eliminarPosicion(posEnemigoEliminar);
            System.out.println(Prueba1.cantidadLista());

        }
        /**Ordena a los enemigos hacia el centro*/
        if (Prueba1.cantidadLista() != 0 && posEnemigoEliminar != 0) {
            Prueba1.setCoordenadas(posEnemigoEliminar);
        }
        /***/
        if (Prueba1 instanceof ClaseB || Prueba1 instanceof ClaseA) {
            Prueba1.actualizarDatos(ventana);

            Prueba1.getListaEnemigos().imprimir();
        }

        this.jugador.getJugadorObjeto().update();//Actualiza constantemente la posicion del jugador al moverlo

        int num = 1;
        /**
         *Ejecutará solamente si es de claseBasic o las sus clases hijas
         */
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
        }

    }

    /**
     * Hace que la velocidad sea cero, baje de fila de cada uno de los enemigos
     */

    public void resetVelocidad() {
        for (int ii = 1; ii <= Prueba1.cantidadLista(); ii++) {
            Prueba1.getListaEnemigos().obtenerDato(ii).getEnemigoObjeto().setVelocidad(new Point2D(0, 0));
            double[] pos = Prueba1.getListaEnemigos().obtenerDato(ii).getEnemigoObjeto().getPosicion();
            Prueba1.getListaEnemigos().obtenerDato(ii).getEnemigoObjeto().setPosicion(pos[0], pos[1] + 30);
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
