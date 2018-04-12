package com.tec.datos1.Jugabilidad;

public class Estadisticas {
    private int puntaje;
    private int nivelAlcanzado;

    public Estadisticas() {
        this.puntaje = 0;
        this.nivelAlcanzado = 0;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getNivelAlcanzado() {
        return nivelAlcanzado;
    }

    public void setNivelAlcanzado(int nivelAlcanzado) {
        this.nivelAlcanzado = nivelAlcanzado;
    }
}
