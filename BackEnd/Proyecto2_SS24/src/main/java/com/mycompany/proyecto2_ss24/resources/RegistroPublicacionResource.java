/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.resources;

import com.mycompany.proyecto2_ss24.backend.data.RevistaDB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
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
@Path("RegistroPublicacion")
public class RegistroPublicacionResource {
    
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearRegistroImage(@FormDataParam("PDF") InputStream archivo,
            @FormDataParam("PDF") FormDataContentDisposition detalleArchivo,
            @FormDataParam("numeroPublicacion") String numeroPublicacion,
            @FormDataParam("idRevista") String idRevista) {
        String mensajeErrorDatos;
        if (detalleArchivo.getFileName().equals("")) {
            mensajeErrorDatos = "DEBE SELECCIONAR UN ARCHIVO PDF PARA LA PUBLICACION";
            String JSONResponse = "{\"mensaje\":\"" + mensajeErrorDatos + "\"}";
            return Response.ok(JSONResponse).build();
        }
        if (numeroPublicacion.equals("")) {
            mensajeErrorDatos = "DEBE RELLECAR EL CAMPO PARA EL NUMERO DE PUBLICACION";
            String JSONResponse = "{\"mensaje\":\"" + mensajeErrorDatos + "\"}";
            return Response.ok(JSONResponse).build();
        }
        if (!detalleArchivo.getFileName().toLowerCase().endsWith(".pdf")) {
            mensajeErrorDatos = "DEBE SELECCIONAR UN ARCHIVO PDF PARA LA PUBLICACION";
            String JSONResponse = "{\"mensaje\":\"" + mensajeErrorDatos + "\"}";
            return Response.ok(JSONResponse).build();
        }
        RevistaDB dataRevista = new RevistaDB();
        if (dataRevista.crearPublicacion(numeroPublicacion, archivo, Integer.parseInt(idRevista))) {
            String mensaje = "exito";
            String JSONResponse = "{\"mensaje\":\"" + mensaje + "\"}";
            return Response.ok(JSONResponse).build();
        }
        String mensaje = "error";
        String JSONResponse = "{\"mensaje\":\"" + mensaje + "\"}";
        return Response.ok(JSONResponse).build();
    }
    
}
