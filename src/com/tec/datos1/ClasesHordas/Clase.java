package com.tec.datos1.ClasesHordas;

import com.tec.datos1.ClasesHordas.ADT.Lista;
import javafx.scene.layout.Pane;

/**
 * Se crea esta interfaz, para no tener conflicto con las clases
 * que tienen diferente tipo de lista (doble o circular)
 */
public interface Clase {
    void agregarEnemigo(int posicion, int nivelVida);

    void eliminarPosicion(int posicion);

    Lista getListaEnemigos();

    void setCoordenadas(int posicion);

    void setCoordenas(double x, double y);

    void actualizarDatos(Pane ventana);

    int cantidadLista();

    int tipoLista();
}
