package com.tec.datos1;

import com.tec.datos1.ClasesHordas.ClaseBasic;
import com.tec.datos1.ListaDoble.ListaDoble;
import com.tec.datos1.ListaDoble.NodoDoble;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class InvadersApp extends Application {

    private Pane ventana;
    private JuegoObjeto jugador;
    private JuegoObjeto[] balas = new JuegoObjeto[10];
    private int posicionBala = 0;
    int nivel = 1;
    ClaseBasic Prueba1 = new ClaseBasic(1);

    private Parent createContent() {
        /* Definiendo las dimensiones de la ventana principal */
        ventana = new Pane();
        ventana.setPrefSize(600, 600);
        jugador = new JuegoObjeto(new Rectangle(40, 20, Color.GRAY));

        addJuegoObjeto(jugador, 300, 500);

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


    private void onUpdate() throws InterruptedException {

        if (nivel == 1) {
            Prueba1 = new ClaseBasic(1);
            Prueba1.setCoordenas(5, 100);
            addJuegoObjeto(Prueba1);
            nivel++;
        } else if (nivel == 100) {

            System.out.println("Ganaste prro");

        } else if (Prueba1.getListaEnemigos().cantidad() == 0) {
            nivel = 100;
        }

        int posEnemigoEliminar = 0;
        for (int i = 0; i < balas.length; i++) {
            NodoDoble auxLista = Prueba1.getListaEnemigos().getRaiz();
            int pos = 0;
            while (auxLista != null) {
                if (balas[i].colision(auxLista.getDato().getEnemigoObjeto())) {

                    balas[i].setMuerto();
                    Prueba1.getListaEnemigos().obtenerDato(pos + 1).setMuerto();

                    posEnemigoEliminar = pos + 1;
                    //ver esta madrePrueba1.getListaEnemigos().obtenerDato(posEnemigoEliminar).cambioColor();

                    Thread.sleep(100);

                    this.ventana.getChildren().removeAll(new Node[]{balas[i].getVista(), Prueba1.getListaEnemigos().obtenerDato(pos + 1).getVista()});

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
            Prueba1.setCoordenadas(posEnemigoEliminar);
            Prueba1.getListaEnemigos().imprimir();
        }

        int num = 1;
        double velocidad = 0.1;
        boolean cambio1=false;

        while (num <= Prueba1.getListaEnemigos().cantidad()) {
            //   if (Prueba1.getListaEnemigos().obtenerDato(Prueba1.getListaEnemigos().cantidad()).getVista().getBoundsInParent().getMaxX() > 40.0){

            /** if (Prueba1.getListaEnemigos().obtenerDato(Prueba1.getListaEnemigos().cantidad()).getVista().getTranslateX() > 575) {
             velocidad *= -1;

             }*/
            if (Prueba1.getListaEnemigos().obtenerDato(Prueba1.getListaEnemigos().cantidad()).getPosicion()[0] > 558&& cambio1==false) {
                cambio1=true;
                velocidad = -0.1;
                System.out.println("ENTRO");
                num = 1;
                while(num <= Prueba1.getListaEnemigos().cantidad()){
                    Prueba1.getListaEnemigos().obtenerDato(num).setVelocidad(new Point2D(0, 0));
                    double[] pos= Prueba1.getListaEnemigos().obtenerDato(num).getPosicion();
                    Prueba1.getListaEnemigos().obtenerDato(num).setPosicion(pos[0],pos[1]+3);
                num++;
                }
                num=1;

                //Prueba1.getListaEnemigos().obtenerDato(Prueba1.getListaEnemigos().cantidad()).setVelocidad(new Point2D(0, 0));
                //Prueba1.getListaEnemigos().obtenerDato(Prueba1.getListaEnemigos().cantidad()).setVelocidad(Prueba1.getListaEnemigos().obtenerDato(Prueba1.getListaEnemigos().cantidad()).getVelocidad().normalize().add(velocidad, 0));
                //Prueba1.getListaEnemigos().obtenerDato(Prueba1.getListaEnemigos().cantidad()).setVelocidad(Prueba1.getListaEnemigos().obtenerDato(Prueba1.getListaEnemigos().cantidad()).getVelocidad().normalize().add(velocidad, 0));
                //Prueba1.getListaEnemigos().obtenerDato(num).setVelocidad(Prueba1.getListaEnemigos().obtenerDato(num).getVelocidad().normalize().add(velocidad, 1));
                //Prueba1.getListaEnemigos().obtenerDato(num).setVelocidad(Prueba1.getListaEnemigos().obtenerDato(num).getVelocidad().normalize().add(-velocidad, 0));
            }
            System.out.println("Velocidad es " + Prueba1.getListaEnemigos().obtenerDato(Prueba1.getListaEnemigos().cantidad()).getVelocidad().getX());
            System.out.println("Valor num es " + num);

            System.out.println(Prueba1.getListaEnemigos().obtenerDato(Prueba1.getListaEnemigos().cantidad()).getPosicion()[0]);
            double[] coordenadas = Prueba1.getListaEnemigos().obtenerDato(num).getPosicion();
            Prueba1.getListaEnemigos().obtenerDato(num).setVelocidad(Prueba1.getListaEnemigos().obtenerDato(num).getVelocidad().normalize().add(velocidad, 0));
            Prueba1.getListaEnemigos().obtenerDato(num).update();
            num++;
        }
        this.jugador.update();

    }

    public void resetVelocidad() {

    }


    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) {
                if (jugador.getVista().getBoundsInParent().getMaxX() > 40.0)
                    jugador.movIzq();

            } else if (e.getCode() == KeyCode.RIGHT) {
                if (jugador.getVista().getBoundsInParent().getMaxX() < 600.0)
                    jugador.movDer();


            } else if (e.getCode() == KeyCode.SPACE) {
                JuegoObjeto bala = new Bala();
                /**Reutilzia los espacios del array balas, para
                 ** imprimilas en la pantalla*/
                if (posicionBala >= balas.length) {
                    posicionBala = 0;
                }
                bala.setVelocidad(jugador.getVelocidad().normalize().add(0, -5));
                addBala(bala, posicionBala, jugador.getVista().getTranslateX() + 15, jugador.getVista().getTranslateY());
                posicionBala += 1;


            }
        });
        stage.show();
    }
}
