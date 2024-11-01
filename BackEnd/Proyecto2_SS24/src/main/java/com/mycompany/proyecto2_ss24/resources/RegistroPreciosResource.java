/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.resources;

import com.mycompany.proyecto2_ss24.backend.controllers.RegistroPreciosController;
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
@Path("RegistroPrecio")
public class RegistroPreciosResource {
    
    @POST
    @Path("tipoAnuncio")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarPreciosTipoAnuncio(String[] precios) {
        RegistroPreciosController controlRecarga = new RegistroPreciosController(precios);
        String mensajeErrorDatos = controlRecarga.verificarDatosRecarga();
        if (!mensajeErrorDatos.equals("")) {
            String JSONResponse = "{\"mensaje\":\"" + mensajeErrorDatos +"\"}";
            return Response.ok(JSONResponse).build();
        }
        String JSONResponse = controlRecarga.actualizar("TipoAnuncio");
        return Response.ok(JSONResponse).build();
    }
    
    @POST
    @Path("tiempoAnuncio")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarPreciosTiempoAnuncio(String[] precios) {
        RegistroPreciosController controlRecarga = new RegistroPreciosController(precios);
        String mensajeErrorDatos = controlRecarga.verificarDatosRecarga();
        if (!mensajeErrorDatos.equals("")) {
            String JSONResponse = "{\"mensaje\":\"" + mensajeErrorDatos +"\"}";
            return Response.ok(JSONResponse).build();
        }
        String JSONResponse = controlRecarga.actualizar("PeriodoTiempoAnuncio");
        return Response.ok(JSONResponse).build();
    }
    
}
