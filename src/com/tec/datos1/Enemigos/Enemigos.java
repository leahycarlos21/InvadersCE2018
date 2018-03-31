package com.tec.datos1.Enemigos;

import com.tec.datos1.JuegoObjeto;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Enemigos {
    private int tipo;/**Si el valor es 0 es de tipo normal, y si es 1 es de tipo jefe*/
    private int vida;
    private boolean vivo = true;
    private double posEjeX;
    private double posEjeY;
    private JuegoObjeto EnemigoObjeto;

    public Enemigos(int vida, JuegoObjeto EnemigoObjeto) {
        this.vida = vida;
        this.EnemigoObjeto = EnemigoObjeto;
        this.EnemigoObjeto.setVida(vida);
    }

    public void actualizarPosicion(double x, double y) {
        this.posEjeX = x;
        this.posEjeY = y;
        this.EnemigoObjeto.setPosicion(x, y);
    }

    public void actualizarVida(int vida) {
        this.vida = vida;
    }


    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public JuegoObjeto getEnemigoObjeto() {
        return EnemigoObjeto;
    }

    public void setEnemigoObjeto(JuegoObjeto enemigoObjeto) {
        EnemigoObjeto = enemigoObjeto;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
