package com.example.a2096129.alertfarm.entities;


import java.io.Serializable;

public class Temperature  implements Serializable {

    private int idParametro;
    private int Clientes_idClientes;
    private double valor;
    private double fecha;
    private int Arduino_idArduino;

    public int getClientes_idClientes() {
        return Clientes_idClientes;
    }

    public void setClientes_idClientes(int clientes_idClientes) {
        Clientes_idClientes = clientes_idClientes;
    }

    public int getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(int idParametro) {
        this.idParametro = idParametro;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getFecha() {
        return fecha;
    }

    public void setFecha(double fecha) {
        this.fecha = fecha;
    }

    public int getArduino_idArduino() {
        return Arduino_idArduino;
    }

    public void setArduino_idArduino(int arduino_idArduino) {
        Arduino_idArduino = arduino_idArduino;
    }

    public Temperature(){

    }

    public Temperature(int id,int idCliente, double valor, double fecha,int Arduino_idArduino){
        this.setIdParametro(id);
        this.setClientes_idClientes(idCliente);
        this.setValor(valor);
        this.setFecha(fecha);
        this.setArduino_idArduino(Arduino_idArduino);
    }

    @Override
    public String toString()
    {
        return "Temperature{" + "idParametro=" + idParametro + ", Clientes_idClientes=" + Clientes_idClientes  + ", valor='" + valor  + ", fecha="
                + fecha + ", Arduino_idArduino=" + Arduino_idArduino +  '}';
    }

}
