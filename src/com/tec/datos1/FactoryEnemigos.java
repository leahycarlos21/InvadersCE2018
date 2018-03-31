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
            //genera un numero entre 1 y 5 y lo guarda en la variable aleatorio
            int numeroAleatorio = 2 + aleatorio.nextInt(5);
            System.out.println("La vida del mop es " + numeroAleatorio);
            return new EnemigoJefe(numeroAleatorio, new JuegoObjeto(new Rectangle(40, 40, color)));
        } else {
            return new EnemigoNormal(vida, new JuegoObjeto(new Rectangle(40, 40, color)));
        }
    }
}
