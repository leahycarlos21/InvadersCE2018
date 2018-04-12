package com.tec.datos1.Enemigos;

public class EnemigoNormal extends Enemigos {

    public EnemigoNormal(int vida, JuegoObjeto EnemigoObjeto) {
        super(vida, EnemigoObjeto);
        this.setTipo(0);/**Identificador de que es un enemigo normal*/
    }
}
