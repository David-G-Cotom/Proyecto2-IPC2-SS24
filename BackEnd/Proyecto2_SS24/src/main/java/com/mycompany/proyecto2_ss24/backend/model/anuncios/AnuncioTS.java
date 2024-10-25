/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.model.anuncios;

/**
 *
 * @author Carlos Cotom
 */
public class AnuncioTS {
    
    private double precio;
    private int vigenciaDias;
    private boolean isActivo;
    private int idInversionista;
    private int idPeriodoTiempo;
    private int idTipoAnuncio;
    private int idAnuncio;
    private String titulo;
    private String contenido;
    private int idAnuncioEspecifico;
    private String fechaCompra;

    public AnuncioTS() {
    }

    public AnuncioTS(double precio, int vigenciaDias, boolean isActivo,
            int idInversionista, int idPeriodoTiempo, int idTipoAnuncio,
            int idAnuncio, String titulo, String contenido, int idAnuncioEspecifico,
            String fechaCompra) {
        this.precio = precio;
        this.vigenciaDias = vigenciaDias;
        this.isActivo = isActivo;
        this.idInversionista = idInversionista;
        this.idPeriodoTiempo = idPeriodoTiempo;
        this.idTipoAnuncio = idTipoAnuncio;
        this.idAnuncio = idAnuncio;
        this.titulo = titulo;
        this.contenido = contenido;
        this.idAnuncioEspecifico = idAnuncioEspecifico;
        this.fechaCompra = fechaCompra;
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

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getIdAnuncioEspecifico() {
        return idAnuncioEspecifico;
    }

    public void setIdAnuncioEspecifico(int idAnuncioEspecifico) {
        this.idAnuncioEspecifico = idAnuncioEspecifico;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    
}
