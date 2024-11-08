/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.model.reportes;

/**
 *
 * @author Carlos Cotom
 */
public class ReporteCompraAnuncio {
    
    private String fechaInicio;
    private String fechaFin;
    private String tipoAnuncio; //ES EL ID
    private String periodoTiempo;   //ES EL ID

    public ReporteCompraAnuncio() {
    }

    public ReporteCompraAnuncio(String fechaInicio, String fechaFin, String tipoAnuncio, String periodoTiempo) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipoAnuncio = tipoAnuncio;
        this.periodoTiempo = periodoTiempo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getTipoAnuncio() {
        return tipoAnuncio;
    }

    public void setTipoAnuncio(String tipoAnuncio) {
        this.tipoAnuncio = tipoAnuncio;
    }

    public String getPeriodoTiempo() {
        return periodoTiempo;
    }

    public void setPeriodoTiempo(String periodoTiempo) {
        this.periodoTiempo = periodoTiempo;
    }
    
}
