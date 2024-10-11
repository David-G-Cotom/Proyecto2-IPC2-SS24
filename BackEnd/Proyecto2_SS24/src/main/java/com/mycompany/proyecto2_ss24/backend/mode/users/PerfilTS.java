/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.mode.users;

/**
 *
 * @author Carlos Cotom
 */
public class PerfilTS {
    
    private String pathFoto;
    private String nombre;

    public PerfilTS(String pathFoto, String nombre) {
        this.pathFoto = pathFoto;
        this.nombre = nombre;
    }

    public String getPathFoto() {
        return pathFoto;
    }

    public void setPathFoto(String pathFoto) {
        this.pathFoto = pathFoto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
