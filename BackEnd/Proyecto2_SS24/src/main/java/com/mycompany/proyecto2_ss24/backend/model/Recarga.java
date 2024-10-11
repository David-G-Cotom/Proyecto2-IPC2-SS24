/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.model;

import java.time.LocalDate;

/**
 *
 * @author Carlos Cotom
 */
public class Recarga {
    
    private double cantidadPaog;
    private LocalDate fechaPago;

    public Recarga() {
    }

    public Recarga(double cantidadPaog, LocalDate fechaPago) {
        this.cantidadPaog = cantidadPaog;
        this.fechaPago = fechaPago;
    }

    public double getCantidadPaog() {
        return cantidadPaog;
    }

    public void setCantidadPaog(double cantidadPaog) {
        this.cantidadPaog = cantidadPaog;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }
    
}
