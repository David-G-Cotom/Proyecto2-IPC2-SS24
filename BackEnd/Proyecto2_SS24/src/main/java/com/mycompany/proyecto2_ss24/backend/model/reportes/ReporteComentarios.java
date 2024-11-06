/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.model.reportes;

/**
 *
 * @author Carlos Cotom
 */
public class ReporteComentarios {
    
    private String fechaInicio;
    private String fechaFin;
    private String idRevista;
    private int idUsuario;

    public ReporteComentarios() {
    }

    public ReporteComentarios(String fechaInicio, String fechaFin, String idRevista,
            int idUsuario) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idRevista = idRevista;
        this.idUsuario = idUsuario;
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

    public String getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(String idRevista) {
        this.idRevista = idRevista;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
