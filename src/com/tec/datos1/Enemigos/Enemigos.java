package com.tec.datos1.Enemigos;

import com.tec.datos1.JuegoObjeto;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Enemigos {
    private int vida;
    private boolean vivo =true;
    private double posEjeX;
    private double posEjeY;

    private JuegoObjeto EnemigoObjeto;

    public Enemigos(){
        this.vida = 1;
        this.EnemigoObject = new GameObject(new Rectangle(40, 40, Color.RED));

    }
}
