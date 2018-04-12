package com.tec.datos1.ClasesInvasores.ADT;

import com.tec.datos1.Enemigos.Enemigos;

public class ListaDoble implements Lista {
    private NodoDoble raiz;
    private int id=1;//Significa que es doble

    public ListaDoble() {
        raiz = null;
    }

    /**retorna la cantidad de nodos de la lista**/
    @Override
    public int cantidad() {
        int cant = 0;
        NodoDoble auxNodo = raiz;
        while (auxNodo != null) {
            auxNodo = auxNodo.siguiente;
            cant++;
        }
        return cant;
    }
    @Override
    public void insertar(int pos, Enemigos dato) {
        if (pos <= cantidad() + 1) {
            NodoDoble nuevo = new NodoDoble(dato);
            if (pos == 1) {
                nuevo.siguiente = raiz;
                if (raiz != null)
                    raiz.anterior = nuevo;

                raiz = nuevo;
            } else if (pos == cantidad() + 1) {
                NodoDoble auxNodo = raiz;
                while (auxNodo.siguiente != null) {
                    auxNodo = auxNodo.siguiente;
                }
                auxNodo.siguiente = nuevo;
                nuevo.anterior = auxNodo;
                nuevo.siguiente = null;
            } else {
                NodoDoble auxNodo = raiz;
                for (int i = 1; i <= pos - 2; i++)
                    auxNodo = auxNodo.siguiente;
                NodoDoble siguiente = auxNodo.siguiente;
                auxNodo.siguiente = nuevo;
                nuevo.anterior = auxNodo;
                nuevo.siguiente = siguiente;
                siguiente.anterior = nuevo;
            }

        }
    }

    @Override
    public void eliminar(int pos) {
        if (pos <= cantidad()) {
            if (pos == 1) {
                raiz = raiz.siguiente;
                if (raiz != null)
                    raiz.anterior = null;
            } else {
                NodoDoble auxNodo = raiz;

                for (int i = 1; i <= pos - 2; i++)
                    auxNodo = auxNodo.siguiente;
                NodoDoble prox = auxNodo.siguiente;
                prox = prox.siguiente;
                auxNodo.siguiente = prox;
                if (prox != null)
                    prox.anterior = auxNodo;
            }
        }
    }

    public void intercambiar(int pos1, int pos2) {
        if (pos1 <= cantidad() && pos2 <= cantidad()) {
            NodoDoble auxNodo1 = raiz;

            for (int i = 1; i < pos1; i++) {
                auxNodo1 = auxNodo1.siguiente;
                auxNodo1.getDato();
            }
            NodoDoble auxNodo2 = raiz;
            for (int i = 1; i < pos2; i++)
                auxNodo2 = auxNodo2.siguiente;
            Enemigos datoAux = auxNodo1.getDato();
            auxNodo1.setDato(auxNodo2.getDato());
            auxNodo2.setDato(datoAux);
        }
    }
    @Override
    public void cambiarDato(int pos, double x, double y) {
        if (pos <= cantidad()) {
            NodoDoble auxNodo = raiz;
            for (int i = 1; i < pos; i++)
                auxNodo = auxNodo.siguiente;
            auxNodo.getDato().actualizarPosicion(x, y);

        }
    }
    @Override
    public Enemigos obtenerDato(int pos) {
        if (pos <= cantidad()) {
            NodoDoble auxNodo = raiz;
            for (int i = 1; i < pos; i++)
                auxNodo = auxNodo.siguiente;

            return auxNodo.getDato();
        }
        return null;
    }


    public NodoDoble getRaiz() {
        return raiz;
    }


    @Override
    public void imprimir() {
        NodoDoble auxNodo = raiz;
        int num = 1;
        while (auxNodo != null) {
            System.out.println(num + " -> " + auxNodo.getDato().getVida());
            num++;
            auxNodo = auxNodo.siguiente;
        }
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void agregarInicio(Enemigos dato) {

    }


}
