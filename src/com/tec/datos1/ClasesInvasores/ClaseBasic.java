package com.tec.datos1.ClasesInvasores;

import com.tec.datos1.ClasesInvasores.ADT.Lista;
import com.tec.datos1.ClasesInvasores.ADT.ListaCircular;
import com.tec.datos1.ClasesInvasores.ADT.ListaDoble;
import com.tec.datos1.Enemigos.Enemigos;
import com.tec.datos1.Enemigos.FactoryEnemigos;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ClaseBasic implements Clase {
    private Lista listaEnemigos = new ListaDoble();

    public ClaseBasic() {
        for (int i = 1; i <= 8; i++) {
            agregarEnemigo(i, 1);
        }
    }


    /**
     * @param posicion  va insertando la cantidad de enemigos a la listaEnemigos
     * @param nivelVida actualiza la vida de los enemigos antes de introducirlos
     *                  a la lista
     */
    @Override
    public void agregarEnemigo(int posicion, int nivelVida) {
        Enemigos generador = FactoryEnemigos.getEnemigo(nivelVida, " ", Color.RED);
        if (posicion % 2 == 0) {
            generador = FactoryEnemigos.getEnemigo(nivelVida, " ", Color.WHEAT);
        }
        this.listaEnemigos.insertar(posicion, generador);
        System.out.println(generador.getVida());

    }

    @Override
    public void eliminarPosicion(int posicion) {
        this.listaEnemigos.eliminar(posicion);
    }

    @Override
    public Lista getListaEnemigos() {////INSERTAR INTERFAZ LISTA
        return listaEnemigos;
    }

    @Override
    public void setCoordenadas(int posicion) {
        if (posicion <= this.listaEnemigos.cantidad() - 3) {
            double[] coordenada = this.listaEnemigos.obtenerDato(posicion).getEnemigoObjeto().getPosicion();

            int num = posicion;
            while (num >= 1) {
                //listaEnemigos.cambiarDato(num, coordenada[0], coordenada[1]);
                listaEnemigos.obtenerDato(num).getEnemigoObjeto().setPosicion(coordenada[0], coordenada[1]);
                listaEnemigos.obtenerDato(num).getEnemigoObjeto().update();
                coordenada[0] -= 50;
                num--;
            }

        } else {
            double[] coordenada = this.listaEnemigos.obtenerDato(1).getEnemigoObjeto().getPosicion();
            setCoordenas(coordenada[0], coordenada[1]);
        }


    }

    /**
     * Funcion auxiliar de la funcion anterior
     *
     * @param x
     * @param y
     */
    @Override
    public void setCoordenas(double x, double y) {
        int num = 1;
        while (num <= listaEnemigos.cantidad()) {
            listaEnemigos.cambiarDato(num, x, y);
            x += 50;
            num++;
            System.out.println("EL valor de X es " + x);
        }
    }

    @Override
    public void actualizarDatos(Pane ventana) {


    }

    @Override
    public int cantidadLista() {
        return this.listaEnemigos.cantidad();
    }

}
