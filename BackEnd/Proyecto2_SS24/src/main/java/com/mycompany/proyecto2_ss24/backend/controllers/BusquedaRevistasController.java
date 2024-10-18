/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.controllers;

import com.mycompany.proyecto2_ss24.backend.data.SuscriptorDB;
import com.mycompany.proyecto2_ss24.backend.model.CategoriaEnum;
import com.mycompany.proyecto2_ss24.backend.model.EtiquetaEnum;
import com.mycompany.proyecto2_ss24.backend.model.FiltroBusquedaRevista;
import com.mycompany.proyecto2_ss24.backend.model.Revista;
import com.mycompany.proyecto2_ss24.backend.model.RevistaTS;
import java.util.ArrayList;

/**
 *
 * @author Carlos Cotom
 */
public class BusquedaRevistasController {
    
    private final SuscriptorDB dataSuscriptor = new SuscriptorDB();
    private final FiltroBusquedaRevista filtro;
    private ArrayList<String> etiquetasFiltradas;
    private ArrayList<String> categoriasFiltradas;

    public BusquedaRevistasController(FiltroBusquedaRevista filtro) {
        this.filtro = filtro;
    }
    
    private void verificarEtiquetas() {
        etiquetasFiltradas = new ArrayList<>();
        for (String etiqueta : this.filtro.getEtiquetas()) {
            for (EtiquetaEnum value : EtiquetaEnum.values()) {
                if (EtiquetaEnum.valueOf(etiqueta) == value) {
                    etiquetasFiltradas.add(etiqueta);
                    break;
                }
            }
        }
    }
    
    private void verificarCategorias() {
        this.categoriasFiltradas = new ArrayList<>();
        for (String categoria : this.filtro.getCategorias()) {
            for (CategoriaEnum value : CategoriaEnum.values()) {
                if (CategoriaEnum.valueOf(categoria) == value) {
                    categoriasFiltradas.add(categoria);
                    break;
                }
            }
        }
    }
    
    public ArrayList<RevistaTS> getRevistas() {
        this.verificarEtiquetas();
        this.verificarCategorias();
        ArrayList<Revista> revistasJava;
        ArrayList<RevistaTS> revistas;
        int idSuscriptor = this.dataSuscriptor.getIdSuscriptor(this.filtro.getIdUsuario());
        if (this.etiquetasFiltradas.isEmpty() && this.categoriasFiltradas.isEmpty()) {
            revistasJava = this.dataSuscriptor.getAllRevistasSuscriptor(idSuscriptor);
            revistas = this.convertirRevistas(revistasJava);
            return revistas;
        }
        revistasJava = this.dataSuscriptor.getRevistasSuscritasFiltro(idSuscriptor, etiquetasFiltradas, categoriasFiltradas);
        revistas = this.convertirRevistas(revistasJava);
        return revistas;
    }
    
    private ArrayList<RevistaTS> convertirRevistas(ArrayList<Revista> revistasJava) {
        ArrayList<RevistaTS> revistas = new ArrayList<>();
        for (Revista revistaJava : revistasJava) {
            RevistaTS revistaTS = new RevistaTS();
            revistaTS.setPuedeComentarse(revistaJava.isPuedeComentarse());
            revistaTS.setPuedeSuscribirse(revistaJava.isPuedeSuscribirse());
            revistaTS.setPuedeTenerLikes(revistaJava.isPuedeTenerLikes());
            revistaTS.setDescripcion(revistaJava.getDescripcion());
            revistaTS.setCategoria(revistaJava.getCategoria().toString());
            String[] etiquetas = new String[revistaJava.getEtiquetas().size()];
            for (int i = 0; i < etiquetas.length; i++) {
                etiquetas[i] = revistaJava.getEtiquetas().get(i).toString();
            }
            revistaTS.setEtiquetas(etiquetas);
            revistaTS.setIdUsuario(this.filtro.getIdUsuario());
            revistaTS.setLikes(revistaJava.getLikes());
            revistaTS.setComentarios(new String[0]);
            revistaTS.setCosto(0);
            revistaTS.setFechaCreacion("");
            revistaTS.setSuscripciones(new String[0]);
            revistaTS.setNombre(revistaJava.getNombreRevista());
            revistaTS.setIdRevista(revistaJava.getIdRevista());
            revistaTS.setCostoGlobal(0);
            revistas.add(revistaTS);
        }
        return revistas;
    }
    
}
