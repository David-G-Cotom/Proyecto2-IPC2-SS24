/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.resources;

import com.mycompany.proyecto2_ss24.backend.data.MediaAnunciosDB;
import com.mycompany.proyecto2_ss24.backend.data.RevistaDB;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.InflaterInputStream;

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
    
    @GET
    @Path("publicacion/{idPublicacion}")
    @Produces("application/pdf")
    public Response getPdfPublicacion(@PathParam("idPublicacion") int idPublicacion) {
        RevistaDB dataRevista = new RevistaDB();
        InputStream data = dataRevista.getPdfPublicacion(idPublicacion);
        return Response.ok(data).build();
        /*StreamingOutput fileStream = (java.io.OutputStream output) -> {
            try {
                
                output.write(data);
                output.flush();
                System.out.println(data.length);
            } catch (IOException e) {
                throw new WebApplicationException("File Not Found !!");
            }
        };
        return Response.ok(fileStream, MediaType.APPLICATION_OCTET_STREAM)
                .header("content-disposition", "attachment; filename = archivo.pdf")
                .build();*/
    }
    
}
