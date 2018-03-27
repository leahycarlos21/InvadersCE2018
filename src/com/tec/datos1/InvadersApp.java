package com.tec.datos1;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
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
    private JuegoObjeto[] balas = new JuegoObjeto[5];
    private int posicionBala=0;

    int nivel=0;
    JuegoObjeto Prueba1;


    private Parent createContent() {
        /* Definiendo las dimensiones de la ventana principal */
        ventana = new Pane();
        ventana.setPrefSize(600, 600);


        jugador = new JuegoObjeto(new Rectangle(40, 20, Color.GRAY));


        addGameObject(jugador, 300, 500);
        JuegoObjeto bala =  new Bala();

        balas[0] = new Bala();
        balas[1] =  new Bala();
        balas[2] =  new Bala();
        balas[3] =  new Bala();
        balas[4] =  new Bala();

        // balas[4] = new GameObject(new Circle(5, 5, 5, Color.BLACK));

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }

        };
        timer.start();

        return ventana;
    }

    private void addBala(JuegoObjeto bala, int pos, double x, double y) {
        balas[pos] = bala;
        addGameObject(bala, x, y);
    }


    private void addGameObject(JuegoObjeto object, double x, double y) {
        object.getVista().setTranslateX(x);
        object.getVista().setTranslateY(y);
        ventana.getChildren().add(object.getVista());
    }


    private static class Bala extends JuegoObjeto{
        Bala(){
            super(new Circle(5, 5, 5, Color.BLACK));
        }
    }


    private void onUpdate() {
       /* for (GameObject bala : balas) {
            System.out.println(bala.getVista());

        }



       for (int i = 0;i!=balas.length;i++){
           if(balas[i]!=null){

           }
           balas[i].update();
       }*/
        for (JuegoObjeto bala : balas) {
            bala.update();
        }
        if(nivel==0){
            Prueba1 = new JuegoObjeto(1);
            Prueba1.getListaDEnemigos().cambiarCoordenadas(1,500,64);
            nivel++;
            addGameObject(Prueba1.getListaDEnemigos().getRaiz().getEnemigo().getEnemigoObject(),500,64);
            Prueba1.getListaDEnemigos().imprimir();


        }
        //Prueba1.getListaDEnemigos().cambiarCoordenadas(1,nivel+=1,nivel+=20);




    }
    private void controlPosicion(ListaDEnemigo listaEnemigos){



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
                JuegoObjeto bala =  new Bala();
                /*Reutilzia los espacios del array balas, para
                 * imprimilas en la pantalla*/
                if(posicionBala>=balas.length){
                    posicionBala=0;
                }
                bala.setVelocidad(jugador.getVelocidad().normalize().add(0, -5));
                addBala(bala, posicionBala, jugador.getVista().getTranslateX() + 15, jugador.getVista().getTranslateY());
                posicionBala+=1;

                //Prueba1 = new ClaseBasic(1);
                //Prueba1.agregarEnemigos();
//                Prueba1.getListaDEnemigos().cantidad();

            }
        });
        stage.show();
    }
}
