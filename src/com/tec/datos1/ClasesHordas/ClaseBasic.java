package com.tec.datos1.ClasesHordas;

import com.tec.datos1.Enemigos.EnemigoNormal;
import com.tec.datos1.Enemigos.Enemigos;
import com.tec.datos1.FactoryEnemigos;
import com.tec.datos1.JuegoObjeto;
import com.tec.datos1.ListaDoble.ListaDoble;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ClaseBasic {
    private ListaDoble listaEnemigos = new ListaDoble();

    public ClaseBasic(int nivelVida) {
        for (int i = 1; i <= 8; i++) {
            agregarEnemigos(i, nivelVida);
        }
        ///listaEnemigos.imprimir();
    }

    /**
     * @param posicion  va insertando la cantidad de enemigos a la listaEnemigos
     * @param nivelVida actualiza la vida de los enemigos antes de introducirlos
     *                  a la lista
     */
    public void agregarEnemigos(int posicion, int nivelVida) {
        Enemigos generador = FactoryEnemigos.getEnemigo(" ");
        generador.actualizarVida(posicion);
        this.listaEnemigos.insertar(posicion, generador);
    }

    public void eliminarPosicion(int posicion) {
        this.listaEnemigos.eliminar(posicion);
        System.out.println("pos es " + posicion);
        if (posicion <= this.listaEnemigos.cantidad() - 3) {
            double[] coordenada = this.listaEnemigos.obtenerDato(posicion).getPosicion();

            int num = posicion;
            System.out.println("daaaaaaaaaaaaaaan" + num);
            while (num >= 1) {
                listaEnemigos.cambiarDato(num, coordenada[0], coordenada[1]);
                coordenada[0] -= 50;
                num--;
            }

            //setCoordenas(coordenada[0], coordenada[1]);

        } else {
            double[] coordenada = this.listaEnemigos.obtenerDato(1).getPosicion();
            setCoordenas(coordenada[0], coordenada[1]);
        }

    }


    /**
     * adjunta los objetos de la lista al eliminar uno del centro
     */


    public ListaDoble getListaEnemigos() {
        return listaEnemigos;
    }

    public void setCoordenas(double x, double y) {
        int num = 1;
        System.out.println("Largo es de " + listaEnemigos.cantidad());
        while (num <= listaEnemigos.cantidad()) {
            listaEnemigos.cambiarDato(num, x, y);
            x += 50;
            num++;
            System.out.println("EL valor de X es " + x);
        }
        // this.listaEnemigos.getRaiz().getDato().actualizarPosicion(x,y);
    }
}
