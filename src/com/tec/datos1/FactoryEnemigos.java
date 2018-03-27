package com.tec.datos1;

import com.tec.datos1.Enemigos.EnemigoJefe;
import com.tec.datos1.Enemigos.EnemigoNormal;
import com.tec.datos1.Enemigos.Enemigos;

public class FactoryEnemigos {
    public static Enemigos getEnemigo(String tipo, int vida) {
        if (tipo.equals("jefe")) {
            return new EnemigoJefe(vida);
        } else {
            return new EnemigoNormal(vida);
        }
    }
}
