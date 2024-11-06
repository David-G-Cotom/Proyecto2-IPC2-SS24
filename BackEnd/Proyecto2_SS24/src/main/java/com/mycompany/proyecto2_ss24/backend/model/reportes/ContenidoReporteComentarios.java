/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.model.reportes;

/**
 *
 * @author Carlos Cotom
 */
public class ContenidoReporteComentarios {
    
    private String numeroRevista;   //NO ES EL ID
    private String nombreRevista;
    private String fechaComentario;
    private String contenidoComentario;
    private String nombreComentarista;

    public ContenidoReporteComentarios() {
    }

    public ContenidoReporteComentarios(String numeroRevista, String nombreRevista,
            String fechaComentario, String contenidoComentario, String nombreComentarista) {
        this.numeroRevista = numeroRevista;
        this.nombreRevista = nombreRevista;
        this.fechaComentario = fechaComentario;
        this.contenidoComentario = contenidoComentario;
        this.nombreComentarista = nombreComentarista;
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

    public String getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(String fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public String getContenidoComentario() {
        return contenidoComentario;
    }

    public void setContenidoComentario(String contenidoComentario) {
        this.contenidoComentario = contenidoComentario;
    }

    public String getNombreComentarista() {
        return nombreComentarista;
    }

    public void setNombreComentarista(String nombreComentarista) {
        this.nombreComentarista = nombreComentarista;
    }
    
    public String toJSON() {
        return "{"
                + "\"numeroRevista\":\"" + numeroRevista + "\","
                + "\"nombreRevista\":\"" + nombreRevista + "\","
                + "\"fechaComentario\":\"" + fechaComentario + "\","
                + "\"contenidoComentario\":\"" + contenidoComentario + "\","
                + "\"nombreComentarista\":\"" + nombreComentarista + "\"" + 
                "}";
    }
    
}
