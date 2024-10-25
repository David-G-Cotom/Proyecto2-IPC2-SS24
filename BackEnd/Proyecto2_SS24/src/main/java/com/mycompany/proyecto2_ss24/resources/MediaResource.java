/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.resources;

import com.mycompany.proyecto2_ss24.backend.data.MediaAnunciosDB;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author Carlos Cotom
 */
@Path("Media")
public class MediaResource {
    
    @GET
    @Path("anuncio/{idAnuncioVideo}")
    @Produces("video/mp4")
    public Response getImagenAnuncio(@PathParam("idAnuncioVideo") int idAnuncioVideo) {
        MediaAnunciosDB dataPerfil = new MediaAnunciosDB();
        byte[] dataImage = dataPerfil.getVideoAnuncio(idAnuncioVideo);
        return Response.ok(dataImage).build();
    }
    
}
