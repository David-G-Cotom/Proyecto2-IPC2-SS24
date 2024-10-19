/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.model.anuncios;

import java.io.InputStream;

/**
 *
 * @author Carlos Cotom
 */
public class AnuncioTextoImagen extends Anuncio {
    
    private String contenido;
    private InputStream imagen;
    private int idAdTextoImagen;

    public AnuncioTextoImagen() {
    }

    public AnuncioTextoImagen(double precio, int vigenciaDias, boolean isActivo, int idInversionista, int idPeriodoTiempo, int idTipoAnuncio) {
        super(precio, vigenciaDias, isActivo, idInversionista, idPeriodoTiempo, idTipoAnuncio);
    }

    public AnuncioTextoImagen(String contenido, InputStream imagen, double precio, int vigenciaDias, boolean isActivo, int idInversionista, int idPeriodoTiempo, int idTipoAnuncio) {
        super(precio, vigenciaDias, isActivo, idInversionista, idPeriodoTiempo, idTipoAnuncio);
        this.contenido = contenido;
        this.imagen = imagen;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public InputStream getImagen() {
        return imagen;
    }

    public void setImagen(InputStream imagen) {
        this.imagen = imagen;
    }    

    public int getIdAdTextoImagen() {
        return idAdTextoImagen;
    }

    public void setIdAdTextoImagen(int idAdTextoImagen) {
        this.idAdTextoImagen = idAdTextoImagen;
    }
    
}
