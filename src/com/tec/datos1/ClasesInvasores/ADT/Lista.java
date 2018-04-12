package com.tec.datos1.ClasesInvasores.ADT;

import com.tec.datos1.Enemigos.Enemigos;

public interface Lista {
    int cantidad();
    void insertar(int pos, Enemigos dato);
    void eliminar(int pos);
    void cambiarDato(int pos, double x, double y);
    Enemigos obtenerDato(int pos);
    void imprimir();
    void setId(int id);
    void agregarInicio(Enemigos dato);
    Nodo getRaiz();
    void intercambiar(int pos1,int pos2);

}
