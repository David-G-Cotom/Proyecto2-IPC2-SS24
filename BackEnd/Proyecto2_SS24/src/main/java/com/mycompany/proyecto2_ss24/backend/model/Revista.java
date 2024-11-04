/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.model;

import com.mycompany.proyecto2_ss24.backend.model.users.Editor;
import java.io.InputStream;
import java.util.ArrayList;

/**
 *
 * @author Carlos Cotom
 */
public class Revista {
    
    private boolean puedeComentarse;
    private boolean puedeTenerLikes;
    private boolean puedeSuscribirse;
    private String descripcion;
    private CategoriaEnum categoria;
    private ArrayList<EtiquetaEnum> etiquetas;
    private Editor autor;
    private int likes;
    private ArrayList<Comentario> comentarios;
    private double costo;
    private String fechaCreacion;
    private ArrayList<Suscripcion> suscripciones;
    private String nombreRevista;
    private int idRevista;
    private double costoGlobal;
    private double costoOcultacion;
    private boolean tieneOcultacionAnuncios;

    public Revista() {
    }
    
    public Revista(boolean puedeComentarse, boolean puedeTenerLikes, boolean puedeSuscribirse, String descripcion, CategoriaEnum categoria, ArrayList<EtiquetaEnum> etiquetas, Editor autor, int likes, ArrayList<Comentario> comentarios, double costo, String fechaCreacion, ArrayList<Suscripcion> suscripciones, String nombreRevista, InputStream archivoPDF, byte[] bytesArchivo) {
        this.puedeComentarse = puedeComentarse;
        this.puedeTenerLikes = puedeTenerLikes;
        this.puedeSuscribirse = puedeSuscribirse;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.etiquetas = etiquetas;
        this.autor = autor;
        this.likes = likes;
        this.comentarios = comentarios;
        this.costo = costo;
        this.fechaCreacion = fechaCreacion;
        this.suscripciones = suscripciones;
        this.nombreRevista = nombreRevista;
    }

    public Revista(String descripcion, CategoriaEnum categoria, ArrayList<EtiquetaEnum> etiquetas, int likes, String nombreRevista, int idRevista) {
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.etiquetas = etiquetas;
        this.likes = likes;
        this.nombreRevista = nombreRevista;
        this.idRevista = idRevista;
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

    public CategoriaEnum getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEnum categoria) {
        this.categoria = categoria;
    }

    public ArrayList<EtiquetaEnum> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(ArrayList<EtiquetaEnum> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public Editor getAutor() {
        return autor;
    }

    public void setAutor(Editor autor) {
        this.autor = autor;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
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

    public ArrayList<Suscripcion> getSuscripciones() {
        return suscripciones;
    }

    public void setSuscripciones(ArrayList<Suscripcion> suscripciones) {
        this.suscripciones = suscripciones;
    }

    public String getNombreRevista() {
        return nombreRevista;
    }

    public void setNombreRevista(String nombreRevista) {
        this.nombreRevista = nombreRevista;
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

    public double getCostoOcultacion() {
        return costoOcultacion;
    }

    public void setCostoOcultacion(double costoOcultacion) {
        this.costoOcultacion = costoOcultacion;
    }

    public boolean isTieneOcultacionAnuncios() {
        return tieneOcultacionAnuncios;
    }

    public void setTieneOcultacionAnuncios(boolean tieneOcultacionAnuncios) {
        this.tieneOcultacionAnuncios = tieneOcultacionAnuncios;
    }

    @Override
    public String toString() {
        return "Revista{ "
                + "puedeComentarse = " + puedeComentarse
                + "puedeTenerLikes = " + puedeTenerLikes
                + "puedeSuscribirse = " + puedeSuscribirse
                + "descripcion = " + descripcion
                + "categoria = " + categoria
                + "etiquetas = " + etiquetas
                + "fechaCreacion = " + fechaCreacion
                + "nombreRevista = " + nombreRevista + '}';
    }
    
}
