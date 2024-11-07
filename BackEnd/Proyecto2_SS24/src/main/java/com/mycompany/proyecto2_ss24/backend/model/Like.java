/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.model;

/**
 *
 * @author Carlos Cotom
 */
public class Like {
    
    private int idUsuario;
    private String fechaLike;
    private int idRevista;

    public Like() {
    }

    public Like(int idUsuario, String fechaLike, int idRevista) {
        this.idUsuario = idUsuario;
        this.fechaLike = fechaLike;
        this.idRevista = idRevista;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

    public String getFechaLike() {
        return fechaLike;
    }

    public void setFechaLike(String fechaLike) {
        this.fechaLike = fechaLike;
    }
    
}
