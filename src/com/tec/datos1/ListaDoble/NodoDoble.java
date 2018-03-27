package com.tec.datos1.ListaDoble;

public class NodoDoble<T> {
    private T dato;
    private NodoDoble<T> anterior, siguiente;

    public NodoDoble(T dato) {
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }

    /**
     *
     * Setters , getters
     */

    public T getDato() {
        return this.dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoDoble<T> getAnterior() {
       return this.anterior;
    }

    public void setAnterior(NodoDoble<T> anterior){
        this.anterior=anterior;
    }

    public NodoDoble<T> getSiguiente(){
        return this.siguiente;
    }
    public void setSiguiente(NodoDoble<T> siguiente){
        this.anterior=siguiente;
    }

}
