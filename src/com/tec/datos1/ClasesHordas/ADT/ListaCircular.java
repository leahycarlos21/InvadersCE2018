package com.tec.datos1.ClasesHordas.ADT;

import com.tec.datos1.Enemigos.Enemigos;

public class ListaCircular implements Lista {
    private int id = 2;//2 significa que es circular
    private NodoCircular inicio, ultimo;
    private int cantidad;

    public ListaCircular() {
        inicio = null;
        ultimo = null;
        cantidad = 0;
    }

    public boolean esVacia() {
        return this.inicio == null;
    }

    @Override
    public int cantidad() {
        return cantidad;
    }

    public void agregarInicio(Enemigos dato) {
        NodoCircular nuevo = new NodoCircular(dato);
        // nuevo.setDato(dato);

        if (esVacia()) {
            this.inicio = nuevo;
            this.ultimo = nuevo;
            ultimo.setSiguiente(inicio);
        } else {
            nuevo.setSiguiente(inicio);
            this.inicio = nuevo;
            ultimo.setSiguiente(inicio);
        }
        cantidad++;
    }

    @Override
    public void insertar(int posicion, Enemigos dato) {
        if (esVacia()) {
            agregarInicio(dato);
        } else if (posicion >= 0 && posicion <= cantidad) {
            NodoCircular nuevo = new NodoCircular(dato);
            //nuevo.setDato(dato);
            if (posicion == 0) {
                nuevo.setSiguiente(inicio);
                inicio = nuevo;
                ultimo.setSiguiente(inicio);
            } else {
                if (posicion == cantidad) {
                    ultimo.setSiguiente(nuevo);
                    nuevo.setSiguiente(inicio);
                    ultimo = nuevo;
                } else {
                    NodoCircular aux = inicio;
                    for (int i = 0; i < (posicion - 1); i++) {
                        aux = aux.getSiguiente();
                    }
                    NodoCircular siguiente = aux.getSiguiente();
                    aux.setSiguiente(nuevo);
                    nuevo.setSiguiente(siguiente);
                }
            }
            cantidad++;
        }
    }

    @Override
    public Enemigos obtenerDato(int posicion) {
        if (posicion <= cantidad()) {
            if (posicion == 0)
                return inicio.getDato();
            else {
                NodoCircular aux = inicio;
                for (int i = 0; i < posicion; i++) {
                    aux = aux.getSiguiente();
                }
                return aux.getDato();
            }
        }
        return null;
    }

    public void editarPosicion(int posicion, Enemigos dato) {
        if (posicion >= 0 && posicion < cantidad) {
            if (posicion == 0) {
                inicio.setDato(dato);
            } else {
                NodoCircular aux = inicio;
                // Recorre la lista hasta lleger al nodo anterior al eliminar.
                for (int i = 0; i < posicion; i++) {
                    aux = aux.getSiguiente();
                }

                aux.setDato(dato);
            }
        }
    }

    @Override
    public void cambiarDato(int pos, double x, double y) {
        if (pos <= this.cantidad()) {
            NodoCircular auxNodo = inicio;
            for (int i = 0; i < pos; i++)
                auxNodo = auxNodo.getSiguiente();
            auxNodo.getDato().actualizarPosicion(x, y);
        }

    }

    @Override
    public void eliminar(int posicion) {
        if (posicion >= 0 && posicion < cantidad) {
            if (posicion == 0) {
                inicio = inicio.getSiguiente();
                ultimo.setSiguiente(inicio);
            }
            else {
                NodoCircular aux = inicio;
                for (int i = 0; i < posicion - 1; i++) {
                    aux = aux.getSiguiente();
                }
                if (aux.getSiguiente() == ultimo) {
                    aux.setSiguiente(inicio);
                    ultimo = aux;
                } else {
                    NodoCircular siguiente = aux.getSiguiente();
                    aux.setSiguiente(siguiente.getSiguiente());
                }
            }
            cantidad--;
        }
    }


    @Override
    public void imprimir() {
        if (!esVacia()) {
            NodoCircular auxNodo = inicio;
            int num = 1;
            while (auxNodo != null) {
                System.out.println(num + "->" + auxNodo.getDato().getTipo());
                num++;
                auxNodo = auxNodo.getSiguiente();
            }
        }
    }


    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public NodoCircular getRaiz() {
        return this.inicio;
    }

    @Override
    public void intercambiar(int pos1, int pos2) {

    }

}


