package com.tec.datos1;

import com.tec.datos1.Enemigos.EnemigoJefe;
import com.tec.datos1.Enemigos.EnemigoNormal;
import com.tec.datos1.Enemigos.Enemigos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FactoryEnemigos {
    public static Enemigos getEnemigo(String tipo) {
        if (tipo.equals("boss")) {
            return new EnemigoJefe(1,new JuegoObjeto(new Rectangle(40, 40, Color.CORAL)));
        } else {
            return new EnemigoNormal(1,new JuegoObjeto(new Rectangle(40, 40, Color.RED)));
        }
    }
}
