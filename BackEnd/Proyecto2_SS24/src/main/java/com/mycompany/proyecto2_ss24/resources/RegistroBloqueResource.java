/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.resources;

import com.mycompany.proyecto2_ss24.backend.controllers.RegistroBloqueoController;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author Carlos Cotom
 */
@Path("RegistroBloque")
public class RegistroBloqueResource {
    
    @POST
    @Path("{idRevista}/{cantidadDias}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSesion(@PathParam("idRevista") String idRevista,
            @PathParam("cantidadDias") String cantidadDias) {
        RegistroBloqueoController controller = new RegistroBloqueoController(idRevista, cantidadDias);
        String mensajeErrorDatos = controller.verificarDatosBloqueo();
        if (!mensajeErrorDatos.equals("")) {
            String JSONResponse = "{\"mensaje\":\"" + mensajeErrorDatos +"\"}";
            return Response.ok(JSONResponse).build();
        }
        String JSONResponse = controller.realizarBloqueo();
        return Response.ok(JSONResponse).build();
    }
    
}
