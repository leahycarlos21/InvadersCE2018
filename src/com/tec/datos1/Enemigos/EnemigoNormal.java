package com.tec.datos1.Enemigos;

import com.tec.datos1.JuegoObjeto;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EnemigoNormal extends Enemigos {

    public EnemigoNormal(int vida) {
        super(vida, new JuegoObjeto(new Rectangle(40, 40, Color.RED)));
    }
}
