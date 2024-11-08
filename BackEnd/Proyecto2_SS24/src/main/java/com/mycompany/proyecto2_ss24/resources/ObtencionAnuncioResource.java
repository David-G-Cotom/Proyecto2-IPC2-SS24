/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.resources;

import com.mycompany.proyecto2_ss24.backend.controllers.AnunciosPublicidadController;
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
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Carlos Cotom
 */
@Path("ObtenerAnuncio")
public class ObtencionAnuncioResource {
    
    private final AnuncioDB dataAnuncios = new AnuncioDB();
    
    @GET
    @Path("{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAnunciosAnunciante(@PathParam("idUsuario") int idUsuario) {
        int idAnunciante = dataAnuncios.getIdInversionista(idUsuario);
        ArrayList<Anuncio> anunciosJava = dataAnuncios.getAnunciosAnunciante(idAnunciante);
        this.evaluarVigenciaAnuncios(anunciosJava);
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
            anuncioTS.setIsVigente(anuncio.isIsVigente());
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
        ArrayList<Anuncio> anunciosJava = dataAnuncios.getAllAnuncios();
        this.evaluarVigenciaAnuncios(anunciosJava);
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
            anuncioTS.setIsVigente(anuncio.isIsVigente());
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
    @Path("publicidad")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAnunciosActivos() {
        AnunciosPublicidadController controller = new AnunciosPublicidadController();
        ArrayList<Anuncio> anunciosJavaIzquierdo = controller.getAnunciosIzquierda();
        ArrayList<Anuncio> anunciosJavaDerocho = controller.getAnunciosDerecha();
        ArrayList<AnuncioTS> anunciosIzquierdo = new ArrayList<>();
        ArrayList<AnuncioTS> anunciosDerecho = new ArrayList<>();
        this.convertirAnuncios(anunciosJavaDerocho, anunciosDerecho);
        this.convertirAnuncios(anunciosJavaIzquierdo, anunciosIzquierdo);
        String contenidoIzquierdoJSON = "[";
        int indice = 1;
        for (AnuncioTS anuncioIzquierdoTS : anunciosIzquierdo) {
            contenidoIzquierdoJSON += anuncioIzquierdoTS.toJSON();
            if (anunciosIzquierdo.size() > 1 && indice < anunciosIzquierdo.size()) {
                contenidoIzquierdoJSON += ",";
            }
            indice++;
        }
        contenidoIzquierdoJSON += "]";
        String contenidoDerechoJSON = "[";
        indice = 1;
        for (AnuncioTS anuncioDerechoTS : anunciosDerecho) {
            contenidoDerechoJSON += anuncioDerechoTS.toJSON();
            if (anunciosDerecho.size() > 1 && indice < anunciosDerecho.size()) {
                contenidoDerechoJSON += ",";
            }
            indice++;
        }
        contenidoDerechoJSON += "]";
        String JSONResponse = "{\"mensaje\":\"exito\", \"contenidoIzquierdo\":" + contenidoIzquierdoJSON + ", \"contenidoDerecho\":" + contenidoDerechoJSON + "}";
        return Response.ok(JSONResponse).build();
    }
    
    private void evaluarVigenciaAnuncios(ArrayList<Anuncio> anuncios) {
        for (Anuncio anuncio : anuncios) {
            System.out.println(anuncio.isIsVigente());
            if (anuncio.isIsVigente()) {
                LocalDate fechaCompra = Date.valueOf(anuncio.getFechaCompra()).toLocalDate();
                LocalDate fechaActual = LocalDate.now();
                long diasDiferencia = ChronoUnit.DAYS.between(fechaCompra, fechaActual);
                System.out.println("fechaCompra: " + fechaCompra);
                System.out.println("fechaActual: " + fechaActual);
                System.out.println("diasDiferencia: " + diasDiferencia);
                System.out.println("vigenciaDias: " + anuncio.getVigenciaDias());
                int tiempoRestante = anuncio.getVigenciaDias() - (int) diasDiferencia;
                System.out.println("tiempoRestante: " + tiempoRestante);
                if (tiempoRestante <= 0) {
                    anuncio.setVigenciaDias(0);
                    System.out.println("Anuncio: " + anuncio.getIdAnuncio() + " YA NO ES VIGENTE");
                    anuncio.setIsVigente(false);
                    anuncio.setIsActivo(false);
                    dataAnuncios.actualizarEstadoVigencia(false, anuncio.getIdAnuncio());
                    dataAnuncios.editarAnuncio(anuncio.getIdAnuncio(), false);
                } else {
                    anuncio.setVigenciaDias(tiempoRestante);
                }
            } else {
                anuncio.setVigenciaDias(0);
            }
        }
    }
    
    private void convertirAnuncios(ArrayList<Anuncio> anunciosJava, ArrayList<AnuncioTS> anuncios) {
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
            anuncioTS.setIsVigente(anuncio.isIsVigente());
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
    }
    
}
