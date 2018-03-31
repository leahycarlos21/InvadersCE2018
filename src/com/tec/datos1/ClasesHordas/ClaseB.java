package com.tec.datos1.ClasesHordas;

import javafx.scene.layout.Pane;

import java.util.Random;

public class ClaseB extends ClaseA {
    public ClaseB(int nivelVida) {
        super(nivelVida);
    }

    @Override
    public void actualizarDatos(Pane ventana) {
        if (getListaEnemigos().cantidad() > 1) {
            int posicionJefe;
            int posicionCambio = 3;
            Random aleatorio = new Random();
            //genera un numero entre 1 y 5 y lo guarda en la variable aleatorio
            int numRandom = 1 + aleatorio.nextInt(100);

            if (numRandom % 97 == 0) {
                System.out.println("numRandom da------------------- " + numRandom);

                posicionJefe = buscarJefe();
                // this.getListaEnemigos().intercambiar(posicionJefe,3);
                double[] coordenas2 = this.getListaEnemigos().obtenerDato(posicionJefe).getEnemigoObjeto().getPosicion();

                double[] coordenas = this.getListaEnemigos().obtenerDato(posicionCambio).getEnemigoObjeto().getPosicion();
                this.getListaEnemigos().cambiarDato(posicionJefe, coordenas[0], coordenas[1]);
                this.getListaEnemigos().cambiarDato(posicionCambio, coordenas2[0], coordenas2[1]);

                this.getListaEnemigos().imprimir();


            }
        }
       /* for (int i = 1; i <= this.getListaEnemigos().cantidad(); i++) {
            if (this.getListaEnemigos().obtenerDato(i).getTipo() == 1) {
                System.out.println("Entro");

                    posicionJefe = i;
                    Random aleatorio = new Random();
                    //genera un numero entre 1 y 5 y lo guarda en la variable aleatorio
                    int numeroAleatorio = 1 + aleatorio.nextInt(this.getListaEnemigos().cantidad());
                    this.getListaEnemigos().intercambiar(posicionJefe, numeroAleatorio-1);
                    this.getListaEnemigos().imprimir();
                    break;



            }
           /// getListaEnemigos().obtenerDato(i).getEnemigoObjeto().update();
           */


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