package com.tec.datos1.ClasesHordas.ADT;

import com.tec.datos1.Enemigos.Enemigos;

public interface Lista {
    int cantidad();
    void insertar(int pos, Enemigos dato);
    void eliminar(int pos);
    void cambiarDato(int pos, double x, double y);
    Enemigos obtenerDato(int pos);
    void imprimir();
    int getId();
    void setId(int id);
    void agregarInicio(Enemigos dato);
    Nodo getRaiz();

    //NodoCircular getRaiz();
    void intercambiar(int pos1,int pos2);

}
