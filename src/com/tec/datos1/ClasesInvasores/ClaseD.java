package com.tec.datos1.ClasesInvasores;

import com.tec.datos1.Enemigos.JuegoObjeto;
import javafx.scene.layout.Pane;

import java.util.Random;

public class ClaseD extends ClaseC {
    public ClaseD() {
        super();
        cambiarVida();
    }

    /**
     * cambia las vidas aleatoriamente de cada enemigo para luego aplicarle el bubblesort
     */
    public void cambiarVida() {
        for (int i = 0; i < listaEnemigos.cantidad(); i++) {
            Random aleatorio = new Random();
            int vidaRandom = 1 + aleatorio.nextInt(6);
            if (listaEnemigos.obtenerDato(i).getTipo() == 1) {
                this.listaEnemigos.obtenerDato(i).getEnemigoObjeto().setVida(super.jefeVida);
            } else
                this.listaEnemigos.obtenerDato(i).getEnemigoObjeto().setVida(vidaRandom);
        }
    }

    /**
     * En este Override, lo que hace es, aplicar bubbble sort a cada uno de los enemigos
     * Y se van a estar actualizando en tiempo real
     * @param ventana
     */
    @Override
    public void actualizarDatos(Pane ventana) {
        int restante = listaEnemigos.cantidad() - 1;
        for (int i = 0; i < (listaEnemigos.cantidad() - 1); i++) {
            for (int b = 0; b < (restante); b++) {
                JuegoObjeto temp;
                //Si el objeto en b es mayor, se intercambian
                if (this.listaEnemigos.obtenerDato(b).getEnemigoObjeto().getVida() >
                        this.listaEnemigos.obtenerDato(b + 1).getEnemigoObjeto().getVida()) {
                    intercambiar(b, b + 1);
                }
            }
            restante--;
        }
    }

    public void intercambiar(int posicion, int posicion2) {

        //genera un numero entre 1 y 5 y lo guarda en la variable aleatorio
        this.listaEnemigos.intercambiar(posicion, posicion2);
        double[] coordenas = this.getListaEnemigos().obtenerDato(posicion2).getEnemigoObjeto().getPosicion();
        double[] coordenas2 = this.getListaEnemigos().obtenerDato(posicion).getEnemigoObjeto().getPosicion();
        this.getListaEnemigos().obtenerDato(posicion).getEnemigoObjeto().setPosicion(coordenas[0], coordenas[1]);
        this.getListaEnemigos().obtenerDato(posicion).getEnemigoObjeto().update();
        this.getListaEnemigos().obtenerDato(posicion2).getEnemigoObjeto().setPosicion(coordenas2[0], coordenas2[1]);
        this.getListaEnemigos().obtenerDato(posicion2).getEnemigoObjeto().update();
        System.out.println("---Vida de pos1--" + this.getListaEnemigos().obtenerDato(posicion).getEnemigoObjeto().getVida());

    }
}
