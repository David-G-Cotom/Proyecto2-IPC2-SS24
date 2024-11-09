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
public class ContenidoReporteRevistaComentada {
    
    private String nombreEditor;
    private String descripcion;
    private String nombreRevista;
    private int cantidadComentarios;
    private ArrayList<String> nombresSuscriptores;
    private ArrayList<String> comentarios;
    private ArrayList<String> fechasComentarios;

    public ContenidoReporteRevistaComentada() {
    }

    public ContenidoReporteRevistaComentada(String nombreEditor, String descripcion, String nombreRevista, int cantidadComentarios, ArrayList<String> nombresSuscriptores, ArrayList<String> comentarios, ArrayList<String> fechasComentarios) {
        this.nombreEditor = nombreEditor;
        this.descripcion = descripcion;
        this.nombreRevista = nombreRevista;
        this.cantidadComentarios = cantidadComentarios;
        this.nombresSuscriptores = nombresSuscriptores;
        this.comentarios = comentarios;
        this.fechasComentarios = fechasComentarios;
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

    public int getCantidadComentarios() {
        return cantidadComentarios;
    }

    public void setCantidadComentarios(int cantidadComentarios) {
        this.cantidadComentarios = cantidadComentarios;
    }

    public ArrayList<String> getNombresSuscriptores() {
        return nombresSuscriptores;
    }

    public void setNombresSuscriptores(ArrayList<String> nombresSuscriptores) {
        this.nombresSuscriptores = nombresSuscriptores;
    }

    public ArrayList<String> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<String> comentarios) {
        this.comentarios = comentarios;
    }

    public ArrayList<String> getFechasComentarios() {
        return fechasComentarios;
    }

    public void setFechasComentarios(ArrayList<String> fechasComentarios) {
        this.fechasComentarios = fechasComentarios;
    }
    
    public String toJSON() {
        return "{"
                + "\"nombreEditor\":\"" + nombreEditor + "\","
                + "\"descripcion\":\"" + descripcion + "\","
                + "\"nombreRevista\":\"" + nombreRevista + "\","
                + "\"cantidadComentarios\":" + cantidadComentarios + ","
                + "\"nombresSuscriptores\":" + nombresSuscriptores + ","
                + "\"comentarios\":" + comentarios + ","
                + "\"fechasComentarios\":" + fechasComentarios + "" + 
                "}";
    }
    
}
