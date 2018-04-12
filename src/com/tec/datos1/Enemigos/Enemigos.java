package com.tec.datos1.Enemigos;

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



    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public JuegoObjeto getEnemigoObjeto() {
        return EnemigoObjeto;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
