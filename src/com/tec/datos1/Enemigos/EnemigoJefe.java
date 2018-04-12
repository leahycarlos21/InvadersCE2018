package com.tec.datos1.Enemigos;

public class EnemigoJefe extends Enemigos {
    public EnemigoJefe(int vida, JuegoObjeto EnemigoObjeto) {
        super(vida, EnemigoObjeto);
        this.setTipo(1);/**Identificador de que es un jefe*/
    }

}
