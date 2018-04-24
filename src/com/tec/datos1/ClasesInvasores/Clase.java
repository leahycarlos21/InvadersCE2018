package com.tec.datos1.ClasesInvasores;

import com.tec.datos1.ClasesInvasores.ADT.Lista;
import javafx.scene.layout.Pane;

/**
 * Se crea esta interfaz, para no tener conflicto con las clases
 * que tienen diferente tipo de lista (doble o circular)
 */
public interface Clase {
    int getCantidadEliminada();
    void setCantidadEliminada(int cantidadElimnada);
    void agregarEnemigo(int posicion, int nivelVida);

    void eliminarPosicion(int posicion);

    Lista getListaEnemigos();

    void setCoordenadas(int posicion);

    void setCoordenas(double x, double y);

    void actualizarDatos(Pane ventana);

    int cantidadLista();

}
