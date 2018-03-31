package com.tec.datos1;

import com.tec.datos1.ClasesHordas.ClaseA;
import com.tec.datos1.ClasesHordas.ClaseBasic;
import com.tec.datos1.ListaDoble.ListaDoble;
import com.tec.datos1.ListaDoble.NodoDoble;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.concurrent.Task;
import javafx.scene.text.Font;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

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
    // ClaseBasic Prueba2 = new ClaseBasic(1);
    ClaseBasic Prueba1 = new ClaseA(1);
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


    private void addJuegoObjeto(ClaseBasic objecto) {
        NodoDoble auxNodo = objecto.getListaEnemigos().getRaiz();
        int num = 1;
        while (auxNodo != null) {
            ventana.getChildren().add(auxNodo.getDato().getEnemigoObjeto().getVista());
            auxNodo = auxNodo.siguiente;
            // ventana.getChildren().add(objecto.getVista());
        }
        System.out.println("Ya salio");
    }


    private static class Bala extends JuegoObjeto {
        Bala() {
            super(new Circle(5, 4, 5, Color.BLACK));
        }
    }

    ///https://stackoverflow.com/questions/18165662/insert-text-at-the-center-of-a-image
    private void onUpdate() throws InterruptedException {

        // final Group rootGroup = new Group();
        //datosJugador="";
        //datosJugador = "Nivel: "+this.jugador.getEstadisticas().getNivelAlcanzado()+"Puntaje: "+this.jugador.getEstadisticas().getPuntaje();
        /*final Text text1 = new Text(25, 25, datosJugador);
        ventana.getChildren().remove(text1);

        text1.setFill(Color.CHOCOLATE);
        text1.setFont(Font.font(java.awt.Font.SERIF, 25));
        //rootGroup.getChildren().add(text1);
        //ventana.getChildren().remove(rootGroup);
       // ventana.getChildren().add(text1);

*/
        ventana.getChildren().remove(text1);

        datosJugador = "Nivel: " + this.jugador.getEstadisticas().getNivelAlcanzado() + "- Puntaje: " + this.jugador.getEstadisticas().getPuntaje();
        text1 = new Text(25, 25, datosJugador);
        text1.setFill(Color.CHOCOLATE);
        text1.setFont(Font.font(java.awt.Font.SERIF, 25));
        ventana.getChildren().add(text1);
        if (this.jugador.getEstadisticas().getNivelAlcanzado() == 0) {
            //Prueba1 = new ClaseBasic(1);
            Prueba1 = new ClaseA(1);
            Prueba1.setCoordenas(5, 100);
            addJuegoObjeto(Prueba1);
            this.jugador.getEstadisticas().setNivelAlcanzado(this.jugador.getEstadisticas().getNivelAlcanzado() + 1);
        } else if (this.jugador.getEstadisticas().getNivelAlcanzado() == 100) {

            System.out.println("Ganaste prro");

        } else if (Prueba1.getListaEnemigos().cantidad() == 0) {
            this.jugador.getEstadisticas().setNivelAlcanzado(this.jugador.getEstadisticas().getNivelAlcanzado() + 1);
        }

        int posEnemigoEliminar = 0;
        for (int i = 0; i < balas.length; i++) {
            NodoDoble auxLista = Prueba1.getListaEnemigos().getRaiz();
            int pos = 0;
            while (auxLista != null) {
                if (balas[i].colision(auxLista.getDato().getEnemigoObjeto())) {

                    balas[i].setMuerto();
                    System.out.println("Vida es " + Prueba1.getListaEnemigos().obtenerDato(pos + 1).getEnemigoObjeto().getVida());
                    Prueba1.getListaEnemigos().obtenerDato(pos + 1).getEnemigoObjeto().restarVida();
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
                auxLista = auxLista.siguiente;
                pos++;
            }
        }
        for (int i = 0; i < balas.length; i++) {
            if (balas[i].isVivo() == false) {
                balas[i].setPosicion(-100, -100);
            }
            balas[i].update();
        }
        if (posEnemigoEliminar != 0) {
            Prueba1.eliminarPosicion(posEnemigoEliminar);


            Prueba1.getListaEnemigos().imprimir();
        }

        int num = 1;


        while (num <= Prueba1.getListaEnemigos().cantidad()) {

            if (Prueba1.getListaEnemigos().obtenerDato(Prueba1.getListaEnemigos().cantidad()).getEnemigoObjeto().getPosicion()[1] > 430) {
                System.out.println("Your dead");
                velocidad = 0;
                //resetVelocidad();
                estadoJuego = false;
            } else if (Prueba1.getListaEnemigos().obtenerDato(Prueba1.getListaEnemigos().cantidad()).getEnemigoObjeto().getPosicion()[0] > 558 && cambio1 == true) {
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
            if (estadoJuego == false) {
                System.out.print("------------------------");
                String GameOver = "\n Perdiste:" + "Nivel: " + this.jugador.getEstadisticas().getNivelAlcanzado() + "- Puntaje: " + this.jugador.getEstadisticas().getPuntaje();
                Text text2 = new Text(200, 200, GameOver);
                text2.setFill(Color.CHOCOLATE);
                text2.setFont(Font.font(java.awt.Font.SERIF, 25));
                ventana.getChildren().add(text2);

            }
            ;
            Prueba1.getListaEnemigos().obtenerDato(num).getEnemigoObjeto().setVelocidad(new Point2D(velocidad, 0));
            Prueba1.getListaEnemigos().obtenerDato(num).getEnemigoObjeto().update();
            num++;
        }
        if (Prueba1.getListaEnemigos().cantidad() != 0)
            Prueba1.setCoordenadas(posEnemigoEliminar);
        this.jugador.getJugadorObjeto().update();


        if (Prueba1 instanceof ClaseA)
            //System.out.println(Prueba1.getClass().getClassLoader());
            Prueba1.actualizarDatos(ventana);
    }

    /**
     * Hace que la velocidad sea cero, baje de fila de cada uno de los enemigos
     */
    public void resetVelocidad() {
        for (int ii = 1; ii <= Prueba1.getListaEnemigos().cantidad(); ii++) {
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
                 ** imprimilas en la pantalla*/
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
