/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.resources;

import com.mycompany.proyecto2_ss24.backend.controllers.RegistroAnuncioController;
import com.mycompany.proyecto2_ss24.backend.data.AnuncioDB;
import com.mycompany.proyecto2_ss24.backend.model.anuncios.AnuncioTS;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.InputStream;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author Carlos Cotom
 */
@Path("RegistroAd")
public class AnuncioResource {
    
    @POST
    @Path("text")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearRegistroText(AnuncioTS anuncio) {
        RegistroAnuncioController controlRegistro = new RegistroAnuncioController(anuncio);
        String mensajeErrorDatos = controlRegistro.verificarDatosAdText();
        if (!mensajeErrorDatos.equals("")) {
            String JSONResponse = "{\"mensaje\":\"" + mensajeErrorDatos + "\"}";
            return Response.ok(JSONResponse).build();
        }
        String JSONResponse = controlRegistro.crearAnuncioText();
        return Response.ok(JSONResponse).build();
    }
    
    @POST
    @Path("image")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearRegistroImage(@FormDataParam("Imagen") InputStream imagen,
            @FormDataParam("Imagen") FormDataContentDisposition detalleImagen,
            @FormDataParam("Fecha") String fecha, @FormDataParam("Duracion") String duracion,
            @FormDataParam("Titulo") String titulo, @FormDataParam("Contenido") String contenido,
            @FormDataParam("IdUsuario") String idUsuarioAnunciante) {
        AnuncioTS anuncio = new AnuncioTS();
        anuncio.setFechaCompra(fecha);
        anuncio.setVigenciaDias(Integer.parseInt(duracion));
        anuncio.setTitulo(titulo);
        anuncio.setContenido(contenido);
        anuncio.setIdInversionista(Integer.parseInt(idUsuarioAnunciante));
        anuncio.setIdTipoAnuncio(2);
        RegistroAnuncioController controlRegistro = new RegistroAnuncioController(anuncio);
        String mensajeErrorDatos = controlRegistro.verificarDatosAdImage(detalleImagen.getFileName());
        if (!mensajeErrorDatos.equals("")) {
            String JSONResponse = "{\"mensaje\":\"" + mensajeErrorDatos + "\"}";
            return Response.ok(JSONResponse).build();
        }
        String JSONResponse = controlRegistro.crearAnuncioImage(imagen);
        return Response.ok(JSONResponse).build();
    }
    
    @POST
    @Path("video")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearRegistroVideo(@FormDataParam("Video") InputStream video,
            @FormDataParam("Video") FormDataContentDisposition detalleVideo,
            @FormDataParam("Fecha") String fecha, @FormDataParam("Duracion") String duracion,
            @FormDataParam("Titulo") String titulo, @FormDataParam("IdUsuario") String idUsuarioAnunciante) {
        AnuncioTS anuncio = new AnuncioTS();
        anuncio.setFechaCompra(fecha);
        anuncio.setVigenciaDias(Integer.parseInt(duracion));
        anuncio.setTitulo(titulo);
        anuncio.setIdInversionista(Integer.parseInt(idUsuarioAnunciante));
        anuncio.setIdTipoAnuncio(3);
        RegistroAnuncioController controlRegistro = new RegistroAnuncioController(anuncio);
        String mensajeErrorDatos = controlRegistro.verificarDatosAdVideo(detalleVideo.getFileName());
        if (!mensajeErrorDatos.equals("")) {
            String JSONResponse = "{\"mensaje\":\"" + mensajeErrorDatos + "\"}";
            return Response.ok(JSONResponse).build();
        }
        String JSONResponse = controlRegistro.crearAnuncioVideo(video);
        return Response.ok(JSONResponse).build();
    }
    
    @PUT
    @Path("anunciante")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAnuncioAnunciante(AnuncioTS anuncio) {
        if (anuncio.getTitulo().equals("")) {
            String mensajeErrorDatos = "ERROR EN LA ACTUALIZACION DE TITULO, debe de rellenar el campo";
            String JSONResponse = "{\"mensaje\":\"" + mensajeErrorDatos + "\"}";
            return Response.ok(JSONResponse).build();
        }
        AnuncioDB dataRevista = new AnuncioDB();
        if (dataRevista.editarAnuncio(anuncio.getIdAnuncio(), anuncio.isIsActivo(), anuncio.getTitulo())) {
            String mensaje = "exito";
            String JSONResponse = "{\"mensaje\":\"" + mensaje + "\"}";
            return Response.ok(JSONResponse).build();
        }
        String mensaje = "error";
        String JSONResponse = "{\"mensaje\":\"" + mensaje + "\"}";
        return Response.ok(JSONResponse).build();
    }
    
    @PUT
    @Path("administrador")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAnuncioAdministrador(AnuncioTS anuncio) {
        AnuncioDB dataRevista = new AnuncioDB();
        if (dataRevista.editarAnuncio(anuncio.getIdAnuncio(), anuncio.isIsActivo(), anuncio.getTitulo())) {
            String mensaje = "exito";
            String JSONResponse = "{\"mensaje\":\"" + mensaje + "\"}";
            return Response.ok(JSONResponse).build();
        }
        String mensaje = "error";
        String JSONResponse = "{\"mensaje\":\"" + mensaje + "\"}";
        return Response.ok(JSONResponse).build();
    }
    
}
