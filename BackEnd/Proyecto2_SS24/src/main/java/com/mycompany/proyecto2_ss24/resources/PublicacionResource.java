/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.resources;

import com.mycompany.proyecto2_ss24.backend.controllers.ArchivosController;
import com.mycompany.proyecto2_ss24.backend.data.RevistaDB;
import com.mycompany.proyecto2_ss24.backend.model.Publicacion;
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
public class PublicacionResource {
    
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearRegistro(@FormDataParam("PDF") InputStream pdf,
            @FormDataParam("PDF") FormDataContentDisposition detallerPDF,
            @FormDataParam("numeroPublicacion") String numeroPublicacion) {
        ArchivosController archivoController = new ArchivosController();
        RevistaDB dataRevista = new RevistaDB();
        String pathPDF = archivoController.guardarArchivo(pdf, "PDF-" + detallerPDF.getFileName());
        Publicacion publicacion = new Publicacion(numeroPublicacion, pathPDF);
        if (dataRevista.crearPublicacion(publicacion)) {
            return Response.ok(publicacion).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
    
}
