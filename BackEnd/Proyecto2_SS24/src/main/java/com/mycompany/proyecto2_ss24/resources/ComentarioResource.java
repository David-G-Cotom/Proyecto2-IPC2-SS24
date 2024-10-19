/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.resources;

import com.mycompany.proyecto2_ss24.backend.data.SuscriptorDB;
import com.mycompany.proyecto2_ss24.backend.model.Comentario;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author Carlos Cotom
 */
@Path("RegistroComentario")
public class ComentarioResource {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearRegistro(Comentario comentario) {
        SuscriptorDB dataSuscripcion = new SuscriptorDB();
        dataSuscripcion.crearComentario(comentario, comentario.getComentarista());
        String mensaje = "exito";
        String JSONResponse = "{\"mensaje\":\"" + mensaje + "\"}";
        return Response.ok(JSONResponse).build();
    }
    
}
