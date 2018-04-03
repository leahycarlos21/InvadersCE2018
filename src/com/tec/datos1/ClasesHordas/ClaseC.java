package com.tec.datos1.ClasesHordas;

import com.tec.datos1.ClasesHordas.ADT.Lista;
import com.tec.datos1.ClasesHordas.ADT.ListaCircular;
import com.tec.datos1.Enemigos.Enemigos;
import com.tec.datos1.FactoryEnemigos;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ClaseC implements Clase {
    public Lista listaEnemigos = new ListaCircular();

    public ClaseC() {
        this.listaEnemigos.setId(2);
        for (int i = 1; i <= 8; i++) {
            agregarEnemigo(i, 1);


        }
        System.out.println("----------------------"+this.listaEnemigos.cantidad());


    }

    @Override
    public void agregarEnemigo(int posicion, int nivelVida) {
        Enemigos generador = FactoryEnemigos.getEnemigo(nivelVida, " ", Color.NAVY);
        if (posicion % 2 == 0) {
            generador = FactoryEnemigos.getEnemigo(nivelVida, " ", Color.SEAGREEN);
        }
        this.listaEnemigos.agregarInicio(generador);
        System.out.println(generador.getVida());
        //listaEnemigos.listar();
    }
    @Override
    public void eliminarPosicion(int posicion) {
        this.listaEnemigos.eliminar(posicion);
    }
    @Override
    public void setCoordenadas(int posicion) {



    }

    @Override
    public void setCoordenas(double x, double y){
        int num = 1;
        while(num<= listaEnemigos.cantidad()){
                this.listaEnemigos.cambiarDato(num,x,y);
                x+=50;
                num++;
            System.out.println("EL valor de X es " + x);

        }
    }

    @Override
    public void actualizarDatos(Pane ventana) {

    }

    @Override
    public int cantidadLista(){
        return this.listaEnemigos.cantidad();

    }
    @Override
    public int tipoLista(){
        return this.listaEnemigos.getId();
    }

    @Override
    public Lista getListaEnemigos() {

        return this.listaEnemigos;
    }
}
