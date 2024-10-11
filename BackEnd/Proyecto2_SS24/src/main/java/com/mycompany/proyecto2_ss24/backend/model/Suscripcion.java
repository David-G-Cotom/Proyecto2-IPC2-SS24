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
public class Suscripcion {
    
    private int suscriptor;
    private LocalDate fechaSuscripcion;
    private int revista;

    public Suscripcion() {
    }
    
    public Suscripcion(int suscriptor, LocalDate fechaSuscripcion) {
        this.suscriptor = suscriptor;
        this.fechaSuscripcion = fechaSuscripcion;
    }

    public int getSuscriptor() {
        return suscriptor;
    }

    public void setSuscriptor(int suscriptor) {
        this.suscriptor = suscriptor;
    }

    public LocalDate getFechaSuscripcion() {
        return fechaSuscripcion;
    }

    public void setFechaSuscripcion(LocalDate fechaSuscripcion) {
        this.fechaSuscripcion = fechaSuscripcion;
    }      

    public int getRevista() {
        return revista;
    }

    public void setRevista(int revista) {
        this.revista = revista;
    }
    
}
