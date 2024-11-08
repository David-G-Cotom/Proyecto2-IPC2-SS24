/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.resources;

import com.mycompany.proyecto2_ss24.backend.controllers.ReporteBuyAdController;
import com.mycompany.proyecto2_ss24.backend.model.reportes.ContenidoReporteCompraAnuncio;
import com.mycompany.proyecto2_ss24.backend.model.reportes.ReporteCompraAnuncio;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;

/**
 *
 * @author Carlos Cotom
 */
@Path("ReporteAdmin")
public class ReporteAdminResource {
    
    @POST
    @Path("compra-anuncios")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReporteComentarios(ReporteCompraAnuncio filtro) {
        ReporteBuyAdController controller = new ReporteBuyAdController(filtro);
        String mensajeErrorFechas = controller.verificarFechas();
        if (!mensajeErrorFechas.equals("")) {
            String JSONResponse = "{\"mensaje\":\"" + mensajeErrorFechas +"\"}";
            return Response.ok(JSONResponse).build();
        }
        ArrayList<ContenidoReporteCompraAnuncio> contenido = controller.getContenidoReporte();
        String contenidoReporteJSON = "[";
        int indice = 1;
        for (ContenidoReporteCompraAnuncio reporteComentario : contenido) {
            contenidoReporteJSON += reporteComentario.toJSON();
            if (contenido.size() > 1 && indice < contenido.size()) {
                contenidoReporteJSON += ",";
            }
            indice++;
        }
        contenidoReporteJSON += "]";
        String JSONResponse = "{\"mensaje\":\"exito\", \"contenido\":" + contenidoReporteJSON + "}";
        return Response.ok(JSONResponse).build();
    }
    
}
