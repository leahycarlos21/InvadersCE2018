package com.tec.datos1.ListaDoble;

public class ListaDoble {
    private NodoDoble raiz;

    public ListaDoble(){
        raiz=null;
    }

    /*retorna la cantidad de nodos de la lista**/
    public int cantidad() {
        int cant = 0;
        NodoDoble auxNodo = raiz;
        while (auxNodo != null) {
            auxNodo = auxNodo.getSiguiente();
            cant++;
        }
        return cant;
    }

    public void insertar(int posicion,  enemigo) {
        if (posicion <= cantidad() + 1) {
            NodoDoble nuevo = new NodoDoble(enemigo);
            if (posicion == 1) {
                nuevo.setSiguiente(raiz);
                if (raiz != null) {
                    raiz.setAnterior(nuevo);

                }
                raiz = nuevo;
            } else if (posicion == cantidad() + 1) {
                NodoDoble auxNodo = raiz;
                while (auxNodo.getSiguiente() != null) {
                    auxNodo = auxNodo.getSiguiente();
                }
                auxNodo.setSiguiente(nuevo);
                nuevo.setAnterior(auxNodo);
                nuevo.setSiguiente(null);
            } else {
                NodoDoble auxNodo = raiz;
                for (int i = 1; i <= posicion - 2; i++) {
                    auxNodo = auxNodo.getSiguiente();
                }
                NodoDoble siguiente = auxNodo.getSiguiente();
                auxNodo.setSiguiente(nuevo);
                nuevo.setAnterior(auxNodo);
                nuevo.setSiguiente(siguiente);
                siguiente.setAnterior(nuevo);
            }
        }
    }
    public void eliminar(int posicion) {
        if (posicion <= cantidad()) {
            if (posicion == 1) {
                raiz = raiz.getSiguiente();
                if (raiz != null) {
                    raiz.setAnterior(null);
                }
            } else {
                NodoDoble auxNodo = raiz;
                for (int i = 1; i <= posicion - 2; i++) {
                    auxNodo = auxNodo.getSiguiente();
                }
                NodoDoble proximo = auxNodo.getSiguiente();
                proximo = proximo.getSiguiente();
                auxNodo.setSiguiente(proximo);
                if (proximo != null) {
                    proximo.setAnterior(auxNodo);
                }
            }
        }
    }
    public void intercambiar(int posicion1, int posicion2) {
        if (posicion1 <= cantidad() && posicion2 <= cantidad()) {
            NodoDoble auxNodo1 = raiz;
            for (int i = 1; i < posicion1; i++)
                auxNodo1 = auxNodo1.getSiguiente();
            NodoDoble auxNodo2 = raiz;
            for (int i = 1; i < posicion2; i++)
                auxNodo2 = auxNodo2.getSiguiente();
            EnemigoForma datoAux = auxNodo1.getDato();
            auxNodo1.setDato(auxNodo2.getDato());
            auxNodo2.setDato(datoAux);
        }

    }

}
