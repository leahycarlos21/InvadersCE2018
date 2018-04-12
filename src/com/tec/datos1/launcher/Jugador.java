package com.tec.datos1.launcher;

import com.tec.datos1.Enemigos.JuegoObjeto;
import com.tec.datos1.Jugabilidad.Estadisticas;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Jugador {
    /**
     * estadisticas, va a concentrar los datos del jugador (nivel y puntaje)
     */
    private Estadisticas estadisticas;
    private JuegoObjeto jugadorObjeto;
    public Jugador(){
        this.estadisticas= new Estadisticas();
        this.jugadorObjeto = new JuegoObjeto(new Rectangle(40, 20, Color.GRAY));

    }

    public Estadisticas getEstadisticas() {
        return estadisticas;
    }

    public void setEstadisticas(Estadisticas estadisticas) {
        this.estadisticas = estadisticas;
    }

    public JuegoObjeto getJugadorObjeto() {
        return jugadorObjeto;
    }

    public void setJugadorObjeto(JuegoObjeto jugadorObjeto) {
        this.jugadorObjeto = jugadorObjeto;
    }
}
