package com.tec.datos1;

import com.tec.datos1.Enemigos.EnemigoJefe;
import com.tec.datos1.Enemigos.EnemigoNormal;
import com.tec.datos1.Enemigos.Enemigos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class FactoryEnemigos {
    public static Enemigos getEnemigo(int vida, String tipo, Color color) {
        if (tipo.equals("boss")) {
            Random aleatorio = new Random();
            //genera un numero entre 3 y 5 y lo guarda en la variable aleatorio
            int numeroAleatorio = 3 + aleatorio.nextInt(5);
            while(numeroAleatorio>6){
                numeroAleatorio = 3 + aleatorio.nextInt(5);
            }
            return new EnemigoJefe(numeroAleatorio, new JuegoObjeto(new Rectangle(40, 40, color)));
        } else {
            return new EnemigoNormal(vida, new JuegoObjeto(new Rectangle(40, 40, color)));
        }
    }
}
