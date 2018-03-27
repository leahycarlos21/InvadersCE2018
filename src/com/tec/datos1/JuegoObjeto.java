package com.tec.datos1;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class JuegoObjeto {
    private Node vista;
    private Point2D velocidad = new Point2D(0, 0);
    private boolean vivo = true;

    public JuegoObjeto(Node vista) {
        this.vista = vista;
    }

    public JuegoObjeto() {
        this.vista = new Circle(5, 5, 5, Color.BLACK);
    }

    /**
     * actualiza la vista que va a tener el objeto en la interfaz
     * al estarse moviendo en eje x,y
     */
    public void update() {
        vista.setTranslateX(vista.getTranslateX() + velocidad.getX());
        vista.setTranslateY(vista.getTranslateY() + velocidad.getY());
    }

    /**
     * @param x  eje x
     * @param y  eje y
     * Metodo para establecer la posicion del objeto
     */

    public void setPosicion(double x, double y) {
        this.vista.setTranslateX(x);
        this.vista.setTranslateY(y);

    }

    /**
     * Retorna un array con las coordenadas actuales del objeto
     **/
    public double[] getPosicion() {
        double[] posiciones = new double[2];
        posiciones[0] = this.vista.getTranslateX();
        posiciones[1] = this.vista.getTranslateY();
        return posiciones;

    }

    public Node getVista() {
        return vista;
    }

    public void setVista(Node vista) {
        this.vista = vista;
    }

    public Point2D getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Point2D velocidad) {
        this.velocidad = velocidad;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setMuerto() {
        this.vivo = false;
    }

    /**
     * mueve el objeto a la derecha 5 puntos
     **/
    public void movDer() {
        vista.setTranslateX(vista.getTranslateX() + 5);
    }

    /**
     * mueve el ojeto a la derecha X puntos
     */
    public void movDer(int pasos) {
        vista.setTranslateX(vista.getTranslateX() + pasos);
    }

    /**
     * mueve el objeto a la izquierda 5 puntos
     **/
    public void movIzq() {
        vista.setTranslateX(vista.getTranslateX() - 5);
    }

    /**
     * mueve el objeto a la izquierda X puntos
     **/
    public void movIzq(int pasos) {
        vista.setTranslateX(vista.getTranslateX() + pasos);
    }

    /**
     * Si dos objectos se detectan juntos (chocan, devuelve falso)
     *
     * @param objeto2
     * @return
     */
    public boolean colision(JuegoObjeto objeto2) {
        if(getVista().getBoundsInParent().intersects(objeto2.getVista().getBoundsInParent())){
            this.setMuerto();
        }
        return this.vivo;
        //return getVista().getBoundsInParent().intersects(objeto2.getVista().getBoundsInParent());
    }


}
