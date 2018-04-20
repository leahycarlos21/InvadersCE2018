package com.tec.datos1.ClasesInvasores;

import com.tec.datos1.ClasesInvasores.ADT.Lista;
import com.tec.datos1.ClasesInvasores.ADT.ListaCircular;
import com.tec.datos1.Enemigos.Enemigos;
import com.tec.datos1.Enemigos.FactoryEnemigos;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Random;

public class ClaseC implements Clase {
    public Lista listaEnemigos = new ListaCircular();
    protected int jefeVida = 3;

    public ClaseC() {
        this.listaEnemigos.setId(2);
        for (int i = 1; i <= 7; i++) {
            agregarEnemigo(i, 1);

        }
        insertarJefe();
    }

    @Override
    public void agregarEnemigo(int posicion, int nivelVida) {
        Enemigos generador = FactoryEnemigos.getEnemigo(nivelVida, " ", Color.NAVY);
        if (posicion % 2 == 0) {
            generador = FactoryEnemigos.getEnemigo(nivelVida, " ", Color.SEAGREEN);
        }
        this.listaEnemigos.agregarInicio(generador);

    }

    /**
     * Esta funcion elimina al primer dato de la lista e inserta a un jefe, y luego se intercambia aleatoriamente
     * a alguna posicion de la lista
     */
    public void insertarJefe() {

        Random aleatorio = new Random();
        //genera un numero entre 1 y 5 y lo guarda en la variable aleatorio
        int numeroAleatorio = 0 + aleatorio.nextInt(listaEnemigos.cantidad() - 1);
        this.getListaEnemigos().eliminar(0);
        Enemigos generador = FactoryEnemigos.getEnemigo(1, "boss", Color.RED);
        this.getListaEnemigos().insertar(numeroAleatorio, generador);

    }

    @Override
    public void eliminarPosicion(int posicion) {
        this.listaEnemigos.eliminar(posicion);
    }


    public void intercambiar(int posicion) {

        //genera un numero entre 1 y 5 y lo guarda en la variable aleatorio
        int posicionCambio = posicion;
        while (posicionCambio == posicion) {
            Random aleatorio = new Random();
            posicionCambio = 0 + aleatorio.nextInt(this.getListaEnemigos().cantidad() );
        }
        //Cambia la vida del jefe por la que está almacenada por jefeVida, y le quita la vida al enemigo que fue sustituido.
        this.listaEnemigos.obtenerDato(posicion).getEnemigoObjeto().setVida(jefeVida);
        this.listaEnemigos.obtenerDato(posicionCambio).getEnemigoObjeto().setVida(0);
        this.listaEnemigos.intercambiar(posicion, posicionCambio);

        //Almacena la posición de los dos objetos, y luego las establece
        double[] coordenas = this.getListaEnemigos().obtenerDato(posicionCambio).getEnemigoObjeto().getPosicion();
        double[] coordenas2 = this.getListaEnemigos().obtenerDato(posicion).getEnemigoObjeto().getPosicion();
        this.getListaEnemigos().obtenerDato(posicion).getEnemigoObjeto().setPosicion(coordenas[0], coordenas[1]);

        this.getListaEnemigos().obtenerDato(posicion).getEnemigoObjeto().update();
        this.getListaEnemigos().obtenerDato(posicionCambio).getEnemigoObjeto().setPosicion(coordenas2[0], coordenas2[1]);
        this.getListaEnemigos().obtenerDato(posicionCambio).getEnemigoObjeto().update();

    }


    @Override
    public void setCoordenadas(int posicion) {
        if (posicion <= this.getListaEnemigos().cantidad() - 3) {
            double[] coordenada = this.listaEnemigos.obtenerDato(posicion).getEnemigoObjeto().getPosicion();
            int num = posicion;
            while (num >= 0) {
                listaEnemigos.obtenerDato(num).getEnemigoObjeto().setPosicion(coordenada[0], coordenada[1]);
                listaEnemigos.obtenerDato(num).getEnemigoObjeto().update();
                coordenada[0] -= 50;
                num--;
            }
        } else {
            double[] coordenada = this.listaEnemigos.obtenerDato(0).getEnemigoObjeto().getPosicion();
            setCoordenas(coordenada[0], coordenada[1]);
        }
    }


    @Override
    public void setCoordenas(double x, double y) {
        int num = 0;
        while (num <= listaEnemigos.cantidad() - 1) {
            this.listaEnemigos.cambiarDato(num, x, y);
            x += 50;
            num++;
        }
    }

    @Override
    public void actualizarDatos(Pane ventana) {

    }


    @Override
    public int cantidadLista() {
        return this.listaEnemigos.cantidad();

    }

    @Override
    public Lista getListaEnemigos() {

        return this.listaEnemigos;
    }
}
