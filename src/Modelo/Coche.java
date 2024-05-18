/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Usuario
 */
public class Coche {
    
    private int num_serie;
    private String marca;
    private String modelo;

    public Coche() {
    }

    public Coche(int num_serie, String marca, String modelo) {
        this.num_serie = num_serie;
        this.marca = marca;
        this.modelo = modelo;
    }

    public int getNum_serie() {
        return num_serie;
    }

    public void setNum_serie(int num_serie) {
        this.num_serie = num_serie;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "Coche{" + "num_serie=" + num_serie + ", marca=" + marca + ", modelo=" + modelo + '}';
    }
    
    
    
    
    
}
