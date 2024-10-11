/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.model;

/**
 *
 * @author Carlos Cotom
 */
public class Publicacion {
    
    private String numeroPublicacion;
    private String pathPDF;

    public Publicacion(String numeroPublicacion, String pathPDF) {
        this.numeroPublicacion = numeroPublicacion;
        this.pathPDF = pathPDF;
    }

    public String getNumeroPublicacion() {
        return numeroPublicacion;
    }

    public void setNumeroPublicacion(String numeroPublicacion) {
        this.numeroPublicacion = numeroPublicacion;
    }

    public String getPathPDF() {
        return pathPDF;
    }

    public void setPathPDF(String pathPDF) {
        this.pathPDF = pathPDF;
    }
    
}
