package com.tec.datos1.ClasesHordas;

import com.tec.datos1.Enemigos.EnemigoNormal;
import com.tec.datos1.Enemigos.Enemigos;
import com.tec.datos1.FactoryEnemigos;
import com.tec.datos1.JuegoObjeto;
import com.tec.datos1.ListaDoble.ListaDoble;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Timer;
import java.util.TimerTask;

public class ClaseBasic {
    private ListaDoble listaEnemigos = new ListaDoble();

    public ClaseBasic(int nivelVida) {
        for (int i = 1; i <= 8; i++) {

            agregarEnemigo(i, nivelVida);

        }
    }

    /**
     * @param posicion  va insertando la cantidad de enemigos a la listaEnemigos
     * @param nivelVida actualiza la vida de los enemigos antes de introducirlos
     *                  a la lista
     */
    public void agregarEnemigo(int posicion, int nivelVida) {
        Enemigos generador = FactoryEnemigos.getEnemigo(nivelVida," ",Color.RED);
        if(posicion%2==0){
             generador = FactoryEnemigos.getEnemigo(nivelVida," ",Color.WHEAT);
        }
        this.listaEnemigos.insertar(posicion, generador);
        System.out.println(generador.getVida());

    }

    public void eliminarPosicion(int posicion){
        this.listaEnemigos.eliminar(posicion);


        //this.listaEnemigos.obtenerDato(posicion).getVista().getScene().fillProperty().setValue(Color.MAGENTA);

    }


    /**
     * adjunta los objetos de la lista al eliminar uno del centro
     */


    public ListaDoble getListaEnemigos() {
        return listaEnemigos;
    }

    public  void setCoordenadas(int posicion) throws InterruptedException {
      //  Thread.sleep(500);



            if (posicion <= this.listaEnemigos.cantidad() - 3) {
            double[] coordenada = this.listaEnemigos.obtenerDato(posicion).getEnemigoObjeto().getPosicion();

            int num = posicion;
            System.out.println("daaaaaaaaaaaaaaan" + num);
            while (num >= 1) {
                listaEnemigos.cambiarDato(num, coordenada[0], coordenada[1]);
                coordenada[0] -= 50;
                num--;
            }

        } else {
            double[] coordenada = this.listaEnemigos.obtenerDato(1).getEnemigoObjeto().getPosicion();
            setCoordenas(coordenada[0], coordenada[1]);
        }

    }
    public void setCoordenas(double x, double y) {
        int num = 1;
        System.out.println("Largo es de " + listaEnemigos.cantidad());
        while (num <= listaEnemigos.cantidad()) {
            listaEnemigos.cambiarDato(num, x, y);
            x += 50;
            num++;
            System.out.println("EL valor de X es " + x);
        }
    }

    public void actualizarDatos(){

    }
    public void actualizarDatos(Pane ventana){

    }
}