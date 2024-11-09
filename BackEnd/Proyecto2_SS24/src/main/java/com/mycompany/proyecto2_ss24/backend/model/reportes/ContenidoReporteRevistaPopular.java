/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.model.reportes;

import java.util.ArrayList;

/**
 *
 * @author Carlos Cotom
 */
public class ContenidoReporteRevistaPopular {
    
    private String nombreEditor;
    private String descripcion;
    private String nombreRevista;
    private int cantidadSuscripciones;
    private ArrayList<String> nombresSuscriptores;
    private ArrayList<String> fechasSuscripciones;

    public ContenidoReporteRevistaPopular() {
    }

    public ContenidoReporteRevistaPopular(String nombreEditor, String descripcion, String nombreRevista, int cantidadSuscripciones, ArrayList<String> nombresSuscriptores, ArrayList<String> fechasSuscripciones) {
        this.nombreEditor = nombreEditor;
        this.descripcion = descripcion;
        this.nombreRevista = nombreRevista;
        this.cantidadSuscripciones = cantidadSuscripciones;
        this.nombresSuscriptores = nombresSuscriptores;
        this.fechasSuscripciones = fechasSuscripciones;
    }

    public String getNombreEditor() {
        return nombreEditor;
    }

    public void setNombreEditor(String nombreEditor) {
        this.nombreEditor = nombreEditor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreRevista() {
        return nombreRevista;
    }

    public void setNombreRevista(String nombreRevista) {
        this.nombreRevista = nombreRevista;
    }

    public int getCantidadSuscripciones() {
        return cantidadSuscripciones;
    }

    public void setCantidadSuscripciones(int cantidadSuscripciones) {
        this.cantidadSuscripciones = cantidadSuscripciones;
    }

    public ArrayList<String> getNombresSuscriptores() {
        return nombresSuscriptores;
    }

    public void setNombresSuscriptores(ArrayList<String> nombresSuscriptores) {
        this.nombresSuscriptores = nombresSuscriptores;
    }

    public ArrayList<String> getFechasSuscripciones() {
        return fechasSuscripciones;
    }

    public void setFechasSuscripciones(ArrayList<String> fechasSuscripciones) {
        this.fechasSuscripciones = fechasSuscripciones;
    }
    
    public String toJSON() {
        return "{"
                + "\"nombreEditor\":\"" + nombreEditor + "\","
                + "\"descripcion\":\"" + descripcion + "\","
                + "\"nombreRevista\":\"" + nombreRevista + "\","
                + "\"cantidadSuscripciones\":" + cantidadSuscripciones + ","
                + "\"nombresSuscriptores\":" + nombresSuscriptores + ","
                + "\"fechasSuscripciones\":" + fechasSuscripciones + "" + 
                "}";
    }
    
}
