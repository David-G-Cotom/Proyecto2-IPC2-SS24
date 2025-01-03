/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.resources;

import com.mycompany.proyecto2_ss24.backend.controllers.RecargaCreditoController;
import com.mycompany.proyecto2_ss24.backend.model.Recarga;
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
@Path("RegistroRecarga")
public class RecargaCreditoResource {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearRegistro(Recarga recarga) {
        RecargaCreditoController controlRecarga = new RecargaCreditoController(recarga);
        String mensajeErrorDatos = controlRecarga.verificarDatosRecarga();
        if (!mensajeErrorDatos.equals("")) {
            String JSONResponse = "{\"mensaje\":\"" + mensajeErrorDatos +"\"}";
            return Response.ok(JSONResponse).build();
        }
        String JSONResponse = controlRecarga.crearRegistro();
        return Response.ok(JSONResponse).build();
    }
    
}
