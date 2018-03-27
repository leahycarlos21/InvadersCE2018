package com.tec.datos1.Enemigos;

import com.tec.datos1.JuegoObjeto;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EnemigoJefe extends Enemigos {
    public EnemigoJefe(int vida) {
        super(vida, new JuegoObjeto(new Rectangle(40, 40, Color.BLANCHEDALMOND)));
    }
}
