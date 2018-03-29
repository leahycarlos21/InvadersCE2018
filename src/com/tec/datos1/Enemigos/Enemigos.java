package com.tec.datos1.Enemigos;

import com.tec.datos1.JuegoObjeto;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Enemigos {
    private int vida;
    private boolean vivo = true;
    private double posEjeX;
    private double posEjeY;
    private JuegoObjeto EnemigoObjeto;

    public Enemigos(int vida, JuegoObjeto EnemigoObjeto) {
        this.vida=vida;
        this.EnemigoObjeto = EnemigoObjeto;
    }

    public void actualizarPosicion(double x, double y) {
        this.posEjeX = x;
        this.posEjeY = y;
        this.EnemigoObjeto.setPosicion(x, y);
    }

    public void actualizarVida(int vida) {
        this.vida = vida;
    }
    public void restarVida(){
        if(this.vida ==0){
            this.vivo=false;
            this.EnemigoObjeto.setMuerto();
        }
        this.vida-=1;
    }
    public int getVida(){
        return vida;
    }



    public JuegoObjeto getEnemigoObjeto() {
        return EnemigoObjeto;
    }
}
