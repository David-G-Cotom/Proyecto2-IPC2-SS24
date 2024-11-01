/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.resources;

import com.mycompany.proyecto2_ss24.backend.data.AnuncioDB;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author Carlos Cotom
 */
@Path("ObtenerPrecio")
public class ObtencionPreciosResource {
    
    @GET
    @Path("tipoAnuncio")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPreciosTipoAnuncio() {
        AnuncioDB dataAnuncio = new AnuncioDB();
        double[] preciosTipoAnuncio = dataAnuncio.getPreciosTipoAnuncios();
        return Response.ok(preciosTipoAnuncio).build();
    }
    
    @GET
    @Path("tiempoAnuncio")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPreciosTiempoAnuncio() {
        AnuncioDB dataAnuncio = new AnuncioDB();
        double[] preciosTiempoAnuncio = dataAnuncio.getPreciosPeriodoTiempo();
        return Response.ok(preciosTiempoAnuncio).build();
    }
    
}
