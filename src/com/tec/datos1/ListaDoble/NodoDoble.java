package com.tec.datos1.ListaDoble;

import com.tec.datos1.Enemigos.Enemigos;

public class NodoDoble{
    private Enemigos dato;
    public NodoDoble  anterior, siguiente;

    public NodoDoble(Enemigos dato) {
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }

    /**
     *
     * Setters , getters
     */

    public Enemigos getDato() {
        return this.dato;
    }

    public void setDato(Enemigos dato) {
        this.dato = dato;
    }

    public NodoDoble getAnterior() {
       return this.anterior;
    }

    public void setAnterior(NodoDoble anterior){
        this.anterior=anterior;
    }

    public NodoDoble getSiguiente(){
        return this.siguiente;
    }
    public void setSiguiente(NodoDoble siguiente){
        this.anterior=siguiente;
    }

}
