package com.tec.datos1.ClasesInvasores.ADT;

import com.tec.datos1.Enemigos.Enemigos;

public interface Nodo {
    Enemigos getDato();
    void setDato(Enemigos dato);
    Nodo getSiguiente();
}
