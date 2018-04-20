package com.tec.datos1.Jugabilidad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private String posicion;
    private boolean disparo =false;
    private String datosJuego="";
    int cantidad=0;

    /**
     * Puerto
     */
    private final static int PORT = 5000;



    public void TCPServer() {

        try {
            //Socket de servidor para esperar peticiones de la red
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor> Servidor iniciado");
            System.out.println("Servidor> En espera de cliente...");
            //Socket de cliente
            Socket clientSocket;

            //se lee peticion del cliente
            while (true) {
                clientSocket = serverSocket.accept();
                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintStream output = new PrintStream(clientSocket.getOutputStream());

                String request = input.readLine();

                //se procesa la peticion y se espera resultado


                String strOutputi = movimiento(request);
                this.posicion=strOutputi;
                //se imprime en cliente
                output.flush();//vacia contenido
                output.println(datosJuego);
                //cierra conexion
               clientSocket.close();
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }
    /**
     * procesa la posicion del cliente y realiza la accion
     *
     * @param request peticion del cliente
     * @return String
     */
    public String movimiento(String request) {
        String result = "Centro";


        if (request != null) switch (request) {
            case "Izquierda":
                result = "Izquierda";
                break;
            case "Centro":
                result = "Centro";
                break;
            case "Derecha":
                result = "Derecha";
                break;
            case "Disparo":
                cantidad++;
                disparo=true;
                result = "Disparo";
                break;
            default:
                result = "Centro";
                break;
        }
        return result;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public boolean isDisparo() {
        return disparo;
    }

    public void setDisparo(boolean disparo) {
        this.disparo = disparo;
    }

    public String getDatosJuego() {
        return datosJuego;
    }

    public void setDatosJuego(String datosJuego) {
        this.datosJuego = datosJuego;
    }
}


