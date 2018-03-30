package com.tec.datos1;

import com.tec.datos1.Enemigos.EnemigoJefe;
import com.tec.datos1.Enemigos.EnemigoNormal;
import com.tec.datos1.Enemigos.Enemigos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FactoryEnemigos {
    public static Enemigos getEnemigo(int vida, String tipo,Color color) {
        if (tipo.equals("boss")) {
            return new EnemigoJefe(vida,new JuegoObjeto(new Rectangle(40, 40, color)));
        } else {
            return new EnemigoNormal(vida,new JuegoObjeto(new Rectangle(40, 40, color)));
        }
    }
}
