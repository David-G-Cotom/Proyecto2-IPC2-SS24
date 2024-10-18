/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.model;

import java.util.Arrays;

/**
 *
 * @author Carlos Cotom
 */
public class RevistaTS {
    
    private boolean puedeComentarse;
    private boolean puedeTenerLikes;
    private boolean puedeSuscribirse;
    private String descripcion;
    private String categoria;
    private String[] etiquetas;
    private int idUsuario;
    private int likes;
    private String[] comentarios;
    private double costo;
    private String fechaCreacion;
    private String[] suscripciones;
    private String nombre;
    private int idRevista;
    private double costoGlobal;
    private String nombreEditor;

    public RevistaTS() {
    }

    public RevistaTS(boolean puedeComentarse, boolean puedeTenerLikes, boolean puedeSuscribirse,
            String descripcion, String categoria, String[] etiquetas, int idUsuario, int likes,
            String[] comentarios, double costo, String fechaCreacion, String[] suscripciones,
            String nombre, int idRevista, double costoGlobal, String nombreEditor) {
        this.puedeComentarse = puedeComentarse;
        this.puedeTenerLikes = puedeTenerLikes;
        this.puedeSuscribirse = puedeSuscribirse;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.etiquetas = etiquetas;
        this.idUsuario = idUsuario;
        this.likes = likes;
        this.comentarios = comentarios;
        this.costo = costo;
        this.fechaCreacion = fechaCreacion;
        this.suscripciones = suscripciones;
        this.nombre = nombre;
        this.idRevista = idRevista;
        this.costoGlobal = costoGlobal;
        this.nombreEditor = nombreEditor;
    }

    public boolean isPuedeComentarse() {
        return puedeComentarse;
    }

    public void setPuedeComentarse(boolean puedeComentarse) {
        this.puedeComentarse = puedeComentarse;
    }

    public boolean isPuedeTenerLikes() {
        return puedeTenerLikes;
    }

    public void setPuedeTenerLikes(boolean puedeTenerLikes) {
        this.puedeTenerLikes = puedeTenerLikes;
    }

    public boolean isPuedeSuscribirse() {
        return puedeSuscribirse;
    }

    public void setPuedeSuscribirse(boolean puedeSuscribirse) {
        this.puedeSuscribirse = puedeSuscribirse;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String[] getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(String[] etiquetas) {
        this.etiquetas = etiquetas;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String[] getComentarios() {
        return comentarios;
    }

    public void setComentarios(String[] comentarios) {
        this.comentarios = comentarios;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String[] getSuscripciones() {
        return suscripciones;
    }

    public void setSuscripciones(String[] suscripciones) {
        this.suscripciones = suscripciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

    public double getCostoGlobal() {
        return costoGlobal;
    }

    public void setCostoGlobal(double costoGlobal) {
        this.costoGlobal = costoGlobal;
    }

    public String getNombreEditor() {
        return nombreEditor;
    }

    public void setNombreEditor(String nombreEditor) {
        this.nombreEditor = nombreEditor;
    }

    @Override
    public String toString() {
        return "RevistaTS{" + "puedeComentarse = " + puedeComentarse + ", puedeTenerLikes = " + puedeTenerLikes
                + ", puedeSuscribirse = " + puedeSuscribirse + ", descripcion = " + descripcion
                + ", categoria = " + categoria + ", etiquetas = " + Arrays.toString(etiquetas)
                + ", idEditor = " + idUsuario + ", likes = " + likes + ", comentarios = " + Arrays.toString(comentarios)
                + ", costo = " + costo + ", fechaCreacion = " + fechaCreacion
                + ", suscripciones = " + Arrays.toString(suscripciones) + ", nombre = " + nombre
                + ", idRevista = " + idRevista + ", costoGlobal = " + costoGlobal + '}';
    }
    
}
