/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.resources;

import com.mycompany.proyecto2_ss24.backend.data.AnuncioDB;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author Carlos Cotom
 */
@Path("EliminarAnuncio")
public class EliminarAnuncioResource {
    
    @DELETE
    @Path("{idAnuncio}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAnuncio(@PathParam("idAnuncio") int idAnuncio) {
        AnuncioDB dataAnuncio = new AnuncioDB();
        if (dataAnuncio.deleteAnuncio(idAnuncio)) {
            String mensaje = "exito";
            String JSONResponse = "{\"mensaje\":\"" + mensaje + "\"}";
            return Response.ok(JSONResponse).build();
        }
        String mensaje = "error";
        String JSONResponse = "{\"mensaje\":\"" + mensaje + "\"}";
        return Response.ok(JSONResponse).build();
    }
    
}
