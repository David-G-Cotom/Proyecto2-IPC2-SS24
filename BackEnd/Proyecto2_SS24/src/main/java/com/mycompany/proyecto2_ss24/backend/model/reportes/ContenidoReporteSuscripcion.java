/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.model.reportes;

/**
 *
 * @author Carlos Cotom
 */
public class ContenidoReporteSuscripcion {
    
    private String numeroRevista;
    private String nombreRevista;
    private String fechaSuscripcion;
    private String nombreSuscriptor;

    public ContenidoReporteSuscripcion() {
    }

    public ContenidoReporteSuscripcion(String numeroRevista, String nombreRevista, String fechaSuscripcion, String nombreSuscriptor) {
        this.numeroRevista = numeroRevista;
        this.nombreRevista = nombreRevista;
        this.fechaSuscripcion = fechaSuscripcion;
        this.nombreSuscriptor = nombreSuscriptor;
    }

    public String getNumeroRevista() {
        return numeroRevista;
    }

    public void setNumeroRevista(String numeroRevista) {
        this.numeroRevista = numeroRevista;
    }

    public String getNombreRevista() {
        return nombreRevista;
    }

    public void setNombreRevista(String nombreRevista) {
        this.nombreRevista = nombreRevista;
    }

    public String getFechaSuscripcion() {
        return fechaSuscripcion;
    }

    public void setFechaSuscripcion(String fechaSuscripcion) {
        this.fechaSuscripcion = fechaSuscripcion;
    }

    public String getNombreSuscriptor() {
        return nombreSuscriptor;
    }

    public void setNombreSuscriptor(String nombreSuscriptor) {
        this.nombreSuscriptor = nombreSuscriptor;
    }
    
    public String toJSON() {
        return "{"
                + "\"numeroRevista\":\"" + numeroRevista + "\","
                + "\"nombreRevista\":\"" + nombreRevista + "\","
                + "\"fechaSuscripcion\":\"" + fechaSuscripcion + "\","
                + "\"nombreSuscriptor\":\"" + nombreSuscriptor + "\"" + 
                "}";
    }
    
}
