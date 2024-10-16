/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.model;

/**
 *
 * @author Carlos Cotom
 */
public class Recarga {
    
    private String cantidad;
    private String fechaRecarga;
    private int idUsuario;
    private int idTipoUsuario;

    public Recarga() {
    }

    public Recarga(String cantidadPaog, String fechaPago, int idUsuario, int idTipoUsuario) {
        this.cantidad = cantidadPaog;
        this.fechaRecarga = fechaPago;
        this.idUsuario = idUsuario;
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getFechaRecarga() {
        return fechaRecarga;
    }

    public void setFechaRecarga(String fechaRecarga) {
        this.fechaRecarga = fechaRecarga;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }
    
}
