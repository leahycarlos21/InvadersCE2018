package com.tec.datos1.launcher;

import com.tec.datos1.Enemigos.JuegoObjeto;
import com.tec.datos1.Jugabilidad.Estadisticas;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Jugador {
    private static Jugador instance;

    /**
     * estadisticas, va a concentrar los datos del jugador (nivel y puntaje)
     */
    private Estadisticas estadisticas;
    private JuegoObjeto jugadorObjeto;

    private Jugador() {

    }

    public static Jugador getInstance() {
        if (instance == null) {
            instance = new Jugador();
            instance.setEstadisticas(new Estadisticas());
            instance.setJugadorObjeto(new JuegoObjeto(new Rectangle(40, 20, Color.GRAY)));

        }
        return instance;
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
