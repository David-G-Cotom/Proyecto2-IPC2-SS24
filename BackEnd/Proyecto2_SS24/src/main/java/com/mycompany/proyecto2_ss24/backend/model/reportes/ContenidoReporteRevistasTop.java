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
public class ContenidoReporteRevistasTop {
    
    private String numeroRevista;
    private String nombreRevista;
    private int cantidadlikes;
    private ArrayList<String> nombresSuscriptores;
    private ArrayList<String> fechasLikes;

    public ContenidoReporteRevistasTop() {
    }

    public ContenidoReporteRevistasTop(String numeroRevista, String nombreRevista,
            int cantidadlikes, ArrayList<String> nombreSuscriptores, ArrayList<String> fechasLikes) {
        this.numeroRevista = numeroRevista;
        this.nombreRevista = nombreRevista;
        this.cantidadlikes = cantidadlikes;
        this.nombresSuscriptores = nombreSuscriptores;
        this.fechasLikes = fechasLikes;
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

    public int getCantidadlikes() {
        return cantidadlikes;
    }

    public void setCantidadlikes(int cantidadlikes) {
        this.cantidadlikes = cantidadlikes;
    }

    public ArrayList<String> getNombresSuscriptores() {
        return nombresSuscriptores;
    }

    public void setNombresSuscriptores(ArrayList<String> nombresSuscriptores) {
        this.nombresSuscriptores = nombresSuscriptores;
    }

    public ArrayList<String> getFechasLikes() {
        return fechasLikes;
    }

    public void setFechasLikes(ArrayList<String> fechasLikes) {
        this.fechasLikes = fechasLikes;
    }
    
    public String toJSON() {
        return "{"
                + "\"numeroRevista\":\"" + numeroRevista + "\","
                + "\"nombreRevista\":\"" + nombreRevista + "\","
                + "\"cantidadLikes\":" + cantidadlikes + ","
                + "\"nombresSuscriptores\":" + nombresSuscriptores + ","
                + "\"fechasLikes\":" + fechasLikes + "" + 
                "}";
    }
    
}
