package com.tec.datos1.ClasesHordas;

import com.tec.datos1.Enemigos.Enemigos;
import com.tec.datos1.FactoryEnemigos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Random;

public class ClaseA extends ClaseBasic {
    public ClaseA() {
        super();
        insertJefe();
        this.getListaEnemigos().imprimir();
    }

    /**
     * Esta funcion elimina al primer dato de la lista e inserta a un jefe, y luego se intercambia aleatoriamente
     * a alguna posicion de la lista
     */
    public void insertJefe() {
        this.getListaEnemigos().eliminar(1);
        Enemigos generador = FactoryEnemigos.getEnemigo(1, "boss", Color.DARKMAGENTA);
        this.getListaEnemigos().insertar(1, generador);
        Random aleatorio = new Random();
        //genera un numero entre 1 y 5 y lo guarda en la variable aleatorio
        int numeroAleatorio = 1 + aleatorio.nextInt(7);
        this.getListaEnemigos().intercambiar(1, numeroAleatorio);

    }

    /**
     * Revisa si el jefe sigue vivo, si retorna false, elimina todos los demás enemigos
     */
    @Override
    public void actualizarDatos(Pane ventana) {
        boolean jefeVivo = false;
        for (int i = 1; i <= this.getListaEnemigos().cantidad(); i++) {
            if (this.getListaEnemigos().obtenerDato(i).getTipo() == 1) {
                jefeVivo = true;
            }
        }
        if (jefeVivo == false) {
            eliminarNormales(ventana);
        }
    }


    /**
     * @param ventana La ventana, es el Pane de la interfaz, se debe quitar primero de la interfaz y
     *                luego de la lista
     */
    public void eliminarNormales(Pane ventana) {
        for (int i = 1; i <= this.getListaEnemigos().cantidad(); i++) {
            ventana.getChildren().removeAll(this.getListaEnemigos().obtenerDato(i).getEnemigoObjeto().getVista());
            this.getListaEnemigos().eliminar(i);


        }
    }
}

