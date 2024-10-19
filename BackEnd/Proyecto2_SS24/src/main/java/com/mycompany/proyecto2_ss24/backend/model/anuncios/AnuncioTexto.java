/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.model.anuncios;


/**
 *
 * @author Carlos Cotom
 */
public class AnuncioTexto extends Anuncio {
    
    private String contenido;
    private int idAnuncioTexto;

    public AnuncioTexto() {
    }

    public AnuncioTexto(double precio, int vigenciaDias, boolean isActivo, int idInversionista, int idPeriodoTiempo, int idTipoAnuncio) {
        super(precio, vigenciaDias, isActivo, idInversionista, idPeriodoTiempo, idTipoAnuncio);
    }

    public AnuncioTexto(String contenido, double precio, int vigenciaDias, boolean isActivo, int idInversionista, int idPeriodoTiempo, int idTipoAnuncio) {
        super(precio, vigenciaDias, isActivo, idInversionista, idPeriodoTiempo, idTipoAnuncio);
        this.contenido = contenido;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }        

    public int getIdAnuncioTexto() {
        return idAnuncioTexto;
    }

    public void setIdAnuncioTexto(int idAnuncioTexto) {
        this.idAnuncioTexto = idAnuncioTexto;
    }
    
}
