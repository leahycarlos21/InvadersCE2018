package com.tec.datos1.ClasesInvasores.ADT;

import com.tec.datos1.Enemigos.Enemigos;

public class NodoDoble implements Nodo {
    private Enemigos dato;
    public NodoDoble anterior, siguiente;

    public NodoDoble(Enemigos dato) {
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }

    /**
     * Setters , getters
     */
    @Override
    public Enemigos getDato() {
        return this.dato;
    }

    @Override
    public void setDato(Enemigos dato) {
        this.dato = dato;
    }

    public NodoDoble getAnterior() {
        return this.anterior;
    }

    public void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }

    @Override
    public NodoDoble getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(NodoDoble siguiente) {
        this.anterior = siguiente;
    }

}
