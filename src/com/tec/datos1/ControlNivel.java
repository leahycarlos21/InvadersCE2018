package com.tec.datos1;

import com.tec.datos1.ClasesHordas.*;

public class ControlNivel {
    private Clase[] HordaEnemigos = new Clase[3];
    private String[] nameHorda = new String[3];
    int nivel;

    /**
     * Contructor que define  las tres primeras filas de enemigos
     *
     * @param nivel
     */
    public ControlNivel(int nivel) {
        addHorda(nivel);

    }

    public void addHorda(int nivel) {
        this.nivel = nivel;
        switch (nivel) {
            case 1:
                HordaEnemigos[0] = new ClaseBasic();
                nameHorda[0] = "ClaseBasic";
                HordaEnemigos[1] = new ClaseA();
                nameHorda[1] = "ClaseA";
                HordaEnemigos[2] = new ClaseB();
                nameHorda[2] = "ClaseB";
                break;
            case 2:
                HordaEnemigos[0] = new ClaseA();
                nameHorda[0] = "ClaseA";
                HordaEnemigos[1] = new ClaseB();
                nameHorda[1] = "ClaseB";
                HordaEnemigos[2] = new ClaseC();
                nameHorda[2] = "ClaseC";
                break;
            case 3:
                HordaEnemigos[0] = new ClaseB();
                nameHorda[0] = "ClaseB";
                HordaEnemigos[1] = new ClaseC();
                nameHorda[1] = "ClaseC";
                HordaEnemigos[2] = new ClaseD();
                nameHorda[2] = "ClaseD";
                break;
            default:
                break;
        }
    }
    public void insertarHorda(){

    }

    public Clase[] getHordaEnemigos() {
        return HordaEnemigos;
    }

    public void setHordaEnemigos(Clase[] hordaEnemigos) {
        HordaEnemigos = hordaEnemigos;
    }

    public String[] getNameHorda() {
        return nameHorda;
    }

    public void setNameHorda(String[] nameHorda) {
        this.nameHorda = nameHorda;
    }
}
