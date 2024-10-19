/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.model.anuncios;


/**
 *
 * @author Carlos Cotom
 */
public class Anuncio {
    
    private double precio;
    private int vigenciaDias;
    private boolean isActivo;
    private int idInversionista;
    private int idPeriodoTiempo;
    private int idTipoAnuncio;
    private int idAnuncio;
    private String titulo;
    
    public Anuncio() {
    }

    public Anuncio(double precio, int vigenciaDias, boolean isActivo, int idInversionista, int idPeriodoTiempo, int idTipoAnuncio) {
        this.precio = precio;
        this.vigenciaDias = vigenciaDias;
        this.isActivo = isActivo;
        this.idInversionista = idInversionista;
        this.idPeriodoTiempo = idPeriodoTiempo;
        this.idTipoAnuncio = idTipoAnuncio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getVigenciaDias() {
        return vigenciaDias;
    }

    public void setVigenciaDias(int vigenciaDias) {
        this.vigenciaDias = vigenciaDias;
    }

    public boolean isIsActivo() {
        return isActivo;
    }

    public void setIsActivo(boolean isActivo) {
        this.isActivo = isActivo;
    }

    public int getIdInversionista() {
        return idInversionista;
    }

    public void setIdInversionista(int idInversionista) {
        this.idInversionista = idInversionista;
    }

    public int getIdPeriodoTiempo() {
        return idPeriodoTiempo;
    }

    public void setIdPeriodoTiempo(int idPeriodoTiempo) {
        this.idPeriodoTiempo = idPeriodoTiempo;
    }

    public int getIdTipoAnuncio() {
        return idTipoAnuncio;
    }

    public void setIdTipoAnuncio(int idTipoAnuncio) {
        this.idTipoAnuncio = idTipoAnuncio;
    }

    public int getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(int idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        String tipoAnuncio = "";
        switch (idTipoAnuncio) {
            case 1:
                tipoAnuncio = "Anuncio de Texto";
                break;
            case 2:
                tipoAnuncio = "Anuncio de Texto e Imagen";
                break;
            case 3:
                tipoAnuncio = "Anuncio de Video";
                break;
        }
        return "Anuncio{ "
                + "<br>precio = " + precio
                + "<br>vigenciaDias = " + vigenciaDias
                + "<br>isActivo = " + isActivo
                + "<br>tipoDeAnuncio = " + tipoAnuncio + '}';
    }
    
}
