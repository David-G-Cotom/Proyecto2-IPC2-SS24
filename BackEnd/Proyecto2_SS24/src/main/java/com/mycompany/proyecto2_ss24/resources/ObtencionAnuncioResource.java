/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.resources;

import com.mycompany.proyecto2_ss24.backend.data.AnuncioDB;
import com.mycompany.proyecto2_ss24.backend.model.anuncios.Anuncio;
import com.mycompany.proyecto2_ss24.backend.model.anuncios.AnuncioTS;
import com.mycompany.proyecto2_ss24.backend.model.anuncios.AnuncioTexto;
import com.mycompany.proyecto2_ss24.backend.model.anuncios.AnuncioTextoImagen;
import com.mycompany.proyecto2_ss24.backend.model.anuncios.AnuncioVideo;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;

/**
 *
 * @author Carlos Cotom
 */
@Path("ObtenerAnuncio")
public class ObtencionAnuncioResource {
    
    @GET
    @Path("{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAnunciosAnunciante(@PathParam("idUsuario") int idUsuario) {
        AnuncioDB dataAnuncios = new AnuncioDB();
        int idAnunciante = dataAnuncios.getIdInversionista(idUsuario);
        ArrayList<Anuncio> anunciosJava = dataAnuncios.getAnunciosAnunciante(idAnunciante);
        ArrayList<AnuncioTS> anuncios = new ArrayList<>();
        for (Anuncio anuncio : anunciosJava) {
            AnuncioTS anuncioTS = new AnuncioTS();
            anuncioTS.setPrecio(anuncio.getPrecio());
            anuncioTS.setVigenciaDias(anuncio.getVigenciaDias());
            anuncioTS.setIsActivo(anuncio.isIsActivo());
            anuncioTS.setIdInversionista(idAnunciante);
            anuncioTS.setIdPeriodoTiempo(anuncio.getIdPeriodoTiempo());
            anuncioTS.setIdTipoAnuncio(anuncio.getIdTipoAnuncio());
            anuncioTS.setIdAnuncio(anuncio.getIdAnuncio());
            anuncioTS.setTitulo(anuncio.getTitulo());
            switch (anuncio) {
                case AnuncioTexto anuncioTexto -> {
                    anuncioTS.setContenido(anuncioTexto.getContenido());
                    anuncioTS.setIdAnuncioEspecifico(anuncioTexto.getIdAnuncioTexto());
                }
                case AnuncioTextoImagen anuncioTextoImagen -> {
                    anuncioTS.setContenido(anuncioTextoImagen.getContenido());
                    anuncioTS.setIdAnuncioEspecifico(anuncioTextoImagen.getIdAdTextoImagen());
                }
                case AnuncioVideo anuncioVideo -> {
                    anuncioTS.setContenido("");
                    anuncioTS.setIdAnuncioEspecifico(anuncioVideo.getIdAnuncioVideo());
                }
                default -> {
                }
            }
            anuncioTS.setFechaCompra("");
            anuncios.add(anuncioTS);
        }
        return Response.ok(anuncios).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAnuncios() {
        AnuncioDB dataAnuncios = new AnuncioDB();
        ArrayList<Anuncio> anunciosJava = dataAnuncios.getAllAnuncios();
        ArrayList<AnuncioTS> anuncios = new ArrayList<>();
        for (Anuncio anuncio : anunciosJava) {
            AnuncioTS anuncioTS = new AnuncioTS();
            anuncioTS.setPrecio(anuncio.getPrecio());
            anuncioTS.setVigenciaDias(anuncio.getVigenciaDias());
            anuncioTS.setIsActivo(anuncio.isIsActivo());
            anuncioTS.setIdInversionista(0);
            anuncioTS.setIdPeriodoTiempo(anuncio.getIdPeriodoTiempo());
            anuncioTS.setIdTipoAnuncio(anuncio.getIdTipoAnuncio());
            anuncioTS.setIdAnuncio(anuncio.getIdAnuncio());
            anuncioTS.setTitulo(anuncio.getTitulo());
            switch (anuncio) {
                case AnuncioTexto anuncioTexto -> {
                    anuncioTS.setContenido(anuncioTexto.getContenido());
                    anuncioTS.setIdAnuncioEspecifico(anuncioTexto.getIdAnuncioTexto());
                }
                case AnuncioTextoImagen anuncioTextoImagen -> {
                    anuncioTS.setContenido(anuncioTextoImagen.getContenido());
                    anuncioTS.setIdAnuncioEspecifico(anuncioTextoImagen.getIdAdTextoImagen());
                }
                case AnuncioVideo anuncioVideo -> {
                    anuncioTS.setContenido("");
                    anuncioTS.setIdAnuncioEspecifico(anuncioVideo.getIdAnuncioVideo());
                }
                default -> {
                }
            }
            anuncioTS.setFechaCompra("");
            anuncios.add(anuncioTS);
        }
        return Response.ok(anuncios).build();
    }
    
}
