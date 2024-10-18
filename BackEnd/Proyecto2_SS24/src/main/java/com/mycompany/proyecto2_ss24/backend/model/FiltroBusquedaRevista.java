/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.model;

/**
 *
 * @author Carlos Cotom
 */
public class FiltroBusquedaRevista {
    
    private String[] etiquetas;
    private String[] categorias;
    private int idUsuario;

    public FiltroBusquedaRevista() {
    }

    public FiltroBusquedaRevista(String[] etiquetas, String[] categorias, int idUsuario) {
        this.etiquetas = etiquetas;
        this.categorias = categorias;
        this.idUsuario = idUsuario;
    }

    public String[] getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(String[] etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String[] getCategorias() {
        return categorias;
    }

    public void setCategorias(String[] categorias) {
        this.categorias = categorias;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
