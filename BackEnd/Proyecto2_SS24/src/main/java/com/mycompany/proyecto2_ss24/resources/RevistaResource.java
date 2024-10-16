/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.resources;

import com.mycompany.proyecto2_ss24.backend.controllers.RegistroRevistaController;
import com.mycompany.proyecto2_ss24.backend.data.RevistaDB;
import com.mycompany.proyecto2_ss24.backend.model.RevistaTS;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author Carlos Cotom
 */
@Path("RegistroRevista")
public class RevistaResource {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearRegistro(RevistaTS revista) {
        System.out.println(revista.toString());
        RegistroRevistaController controlRegistro = new RegistroRevistaController(revista);
        String mensajeErrorDatos = controlRegistro.verificarDatosRevista();
        if (!mensajeErrorDatos.equals("")) {
            String JSONResponse = "{\"mensaje\":\"" + mensajeErrorDatos +"\"}";
            return Response.ok(JSONResponse).build();
        }
        String JSONResponse = controlRegistro.crearRevista();
        return Response.ok(JSONResponse).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePerfil(RevistaTS revista) {
        RevistaDB dataRevista = new RevistaDB();
        if (dataRevista.actualizarRevista(revista)) {
            return Response.ok(revista).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
    
}
