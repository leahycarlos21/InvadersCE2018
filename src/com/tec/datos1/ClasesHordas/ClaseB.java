package com.tec.datos1.ClasesHordas;

import javafx.scene.layout.Pane;

import java.util.Random;

public class ClaseB extends ClaseA {
    public ClaseB() {
        super();
    }

    @Override
    public void actualizarDatos(Pane ventana) {
        if (getListaEnemigos().cantidad() > 1) {
            int posicionJefe;
            Random aleatorio = new Random();
            //genera un numero entre 1 y 5 y lo guarda en la variable aleatorio
            int posicionCambio = 1+ aleatorio.nextInt(this.getListaEnemigos().cantidad());
            int numRandom = 1 + aleatorio.nextInt(100);

            if (numRandom % 97 == 0) {/**Al cumplir la condicion, se realiza un cambio de la posicion del jefe con otra */
                posicionJefe = buscarJefe();
                this.getListaEnemigos().intercambiar(posicionJefe,posicionCambio);
                double[] coordenas = this.getListaEnemigos().obtenerDato(posicionCambio).getEnemigoObjeto().getPosicion();
                double[] coordenas2 = this.getListaEnemigos().obtenerDato(posicionJefe).getEnemigoObjeto().getPosicion();
                this.getListaEnemigos().obtenerDato(posicionJefe).getEnemigoObjeto().setPosicion(coordenas[0], coordenas[1]);
                this.getListaEnemigos().obtenerDato(posicionJefe).getEnemigoObjeto().update();
                this.getListaEnemigos().obtenerDato(posicionCambio).getEnemigoObjeto().setPosicion(coordenas2[0], coordenas2[1]);
                this.getListaEnemigos().obtenerDato(posicionCambio).getEnemigoObjeto().update();

                this.getListaEnemigos().imprimir();
            }
        }

        super.actualizarDatos(ventana);
    }

    private int buscarJefe() {
        int pos = 1;
        for (int i = 1; i <= this.getListaEnemigos().cantidad(); i++) {
            if (this.getListaEnemigos().obtenerDato(i).getTipo() == 1) {
                pos = i;
                break;
            }
        }
        return pos;
    }
}