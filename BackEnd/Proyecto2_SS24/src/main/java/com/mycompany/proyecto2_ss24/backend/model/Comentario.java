/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.model;


/**
 *
 * @author Carlos Cotom
 */
public class Comentario {
    
    private String contenido;
    private int comentarista;
    private int revista;
    private String fechaComentario;

    public Comentario() {
    }
    
    public Comentario(String contenido, int comentarista) {
        this.contenido = contenido;
        this.comentarista = comentarista;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getComentarista() {
        return comentarista;
    }

    public void setComentarista(int comentarista) {
        this.comentarista = comentarista;
    }  

    public int getRevista() {
        return revista;
    }

    public void setRevista(int revista) {
        this.revista = revista;
    }

    public String getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(String fechaComentario) {
        this.fechaComentario = fechaComentario;
    }
    
}
