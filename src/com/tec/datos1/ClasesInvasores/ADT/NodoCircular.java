package com.tec.datos1.ClasesInvasores.ADT;

import com.tec.datos1.Enemigos.Enemigos;

public class NodoCircular implements Nodo {
    private Enemigos dato;
    private NodoCircular siguiente;

    public NodoCircular(Enemigos dato) {
        //super();
        this.dato = dato;
        siguiente = null;
    }

    @Override
    public Enemigos getDato() {
        return this.dato;
    }

    @Override
    public void setDato(Enemigos dato) {
        this.dato = dato;
    }

    @Override
    public NodoCircular getSiguiente() {
        return siguiente;
    }

      public void setSiguiente(NodoCircular siguiente) {
        this.siguiente = siguiente;
    }
}
