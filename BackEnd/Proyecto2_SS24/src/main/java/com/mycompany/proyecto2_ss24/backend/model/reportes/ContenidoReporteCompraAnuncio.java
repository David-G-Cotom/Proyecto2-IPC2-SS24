/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.model.reportes;

/**
 *
 * @author Carlos Cotom
 */
public class ContenidoReporteCompraAnuncio {
    
    private double costo;
    private String tipoAnuncio;
    private String nombreAnunciante;
    private int periodoTiempo;
    private String titulo;
    private String fechaCompra;
    private boolean isVigente;

    public ContenidoReporteCompraAnuncio() {
    }

    public ContenidoReporteCompraAnuncio(double costo, String tipoAnuncio, String nombreAnunciante,
            int periodoTiempo, String titulo, String fechaCompra, boolean isVigente) {
        this.costo = costo;
        this.tipoAnuncio = tipoAnuncio;
        this.nombreAnunciante = nombreAnunciante;
        this.periodoTiempo = periodoTiempo;
        this.titulo = titulo;
        this.fechaCompra = fechaCompra;
        this.isVigente = isVigente;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getTipoAnuncio() {
        return tipoAnuncio;
    }

    public void setTipoAnuncio(String tipoAnuncio) {
        this.tipoAnuncio = tipoAnuncio;
    }

    public String getNombreAnunciante() {
        return nombreAnunciante;
    }

    public void setNombreAnunciante(String nombreAnunciante) {
        this.nombreAnunciante = nombreAnunciante;
    }

    public int getPeriodoTiempo() {
        return periodoTiempo;
    }

    public void setPeriodoTiempo(int periodoTiempo) {
        this.periodoTiempo = periodoTiempo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public boolean isIsVigente() {
        return isVigente;
    }

    public void setIsVigente(boolean isVigente) {
        this.isVigente = isVigente;
    }
    
    public String toJSON() {
        return "{"
                + "\"costo\":" + costo + ","
                + "\"tipoAnuncio\":\"" + tipoAnuncio + "\","
                + "\"nombreAnunciante\":\"" + nombreAnunciante + "\","
                + "\"periodoTiempo\":" + periodoTiempo + ","
                + "\"titulo\":\"" + titulo + "\","
                + "\"fechaCompra\":\"" + fechaCompra + "\","
                + "\"isVigente\":" + isVigente + "" + 
                "}";
    }
    
}
