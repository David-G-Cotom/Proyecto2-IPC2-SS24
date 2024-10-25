/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.resources;

import com.mycompany.proyecto2_ss24.backend.data.MediaAnunciosDB;
import com.mycompany.proyecto2_ss24.backend.data.PerfilDB;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author Carlos Cotom
 */
@Path("Imagen")
public class ImagenResource {
    
    @GET
    @Path("usuario/{idUsuario}")
    @Produces("image/png")
    public Response getFotoUsuario(@PathParam("idUsuario") int idUsuario) {
        PerfilDB dataPerfil = new PerfilDB();
        byte[] dataImage = dataPerfil.getImageUsuario(idUsuario);
        return Response.ok(dataImage).build();
    }
    
    @GET
    @Path("editor/{idEditor}")
    @Produces("image/png")
    public Response getFotoEditor(@PathParam("idEditor") int idEditor) {
        PerfilDB dataPerfil = new PerfilDB();
        int idUsuarioEditor = dataPerfil.getIdUsuarioEditor(idEditor);
        byte[] dataImage = dataPerfil.getImageUsuario(idUsuarioEditor);
        return Response.ok(dataImage).build();
    }
    
    @GET
    @Path("anuncio/{idAnuncioImagen}")
    @Produces("image/png")
    public Response getImagenAnuncio(@PathParam("idAnuncioImagen") int idAnuncioImagen) {
        MediaAnunciosDB dataPerfil = new MediaAnunciosDB();
        byte[] dataImage = dataPerfil.getImageAnuncio(idAnuncioImagen);
        return Response.ok(dataImage).build();
    }
    
}
