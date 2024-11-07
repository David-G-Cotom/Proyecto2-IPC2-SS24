/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.resources;

import com.mycompany.proyecto2_ss24.backend.controllers.ReporteComentariosController;
import com.mycompany.proyecto2_ss24.backend.controllers.ReporteRevistasTopController;
import com.mycompany.proyecto2_ss24.backend.controllers.ReporteSuscripcionesController;
import com.mycompany.proyecto2_ss24.backend.model.reportes.ContenidoReporteComentarios;
import com.mycompany.proyecto2_ss24.backend.model.reportes.ContenidoReporteRevistasTop;
import com.mycompany.proyecto2_ss24.backend.model.reportes.ContenidoReporteSuscripcion;
import com.mycompany.proyecto2_ss24.backend.model.reportes.ReporteComentarios;
import com.mycompany.proyecto2_ss24.backend.model.reportes.ReporteRevistasTop;
import com.mycompany.proyecto2_ss24.backend.model.reportes.ReporteSuscripcion;
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
@Path("ReporteEditor")
public class ReporteEditorResource {
    
    @POST
    @Path("comentarios")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReporteComentarios(ReporteComentarios filtro) {
        ReporteComentariosController controller = new ReporteComentariosController(filtro);
        String mensajeErrorFechas = controller.verificarFechas();
        if (!mensajeErrorFechas.equals("")) {
            String JSONResponse = "{\"mensaje\":\"" + mensajeErrorFechas +"\"}";
            return Response.ok(JSONResponse).build();
        }
        ArrayList<ContenidoReporteComentarios> contenido = controller.getContenidoReporte();
        String contenidoReporteJSON = "[";
        int indice = 1;
        for (ContenidoReporteComentarios reporteComentario : contenido) {
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
    
    @POST
    @Path("suscripciones")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReporteSuscripciones(ReporteSuscripcion filtro) {
        ReporteSuscripcionesController controller = new ReporteSuscripcionesController(filtro);
        String mensajeErrorFechas = controller.verificarFechas();
        if (!mensajeErrorFechas.equals("")) {
            String JSONResponse = "{\"mensaje\":\"" + mensajeErrorFechas +"\"}";
            return Response.ok(JSONResponse).build();
        }
        ArrayList<ContenidoReporteSuscripcion> contenido = controller.getContenidoReporte();
        String contenidoReporteJSON = "[";
        int indice = 1;
        for (ContenidoReporteSuscripcion reporteComentario : contenido) {
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
    
    @POST
    @Path("revistas-top")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReporteRevistasTop(ReporteRevistasTop filtro) {
        ReporteRevistasTopController controller = new ReporteRevistasTopController(filtro);
        String mensajeErrorFechas = controller.verificarFechas();
        if (!mensajeErrorFechas.equals("")) {
            String JSONResponse = "{\"mensaje\":\"" + mensajeErrorFechas +"\"}";
            return Response.ok(JSONResponse).build();
        }
        ArrayList<ContenidoReporteRevistasTop> contenido = controller.getContenidoReporte();
        String contenidoReporteJSON = "[";
        int indice = 1;
        for (ContenidoReporteRevistasTop reporteComentario : contenido) {
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
