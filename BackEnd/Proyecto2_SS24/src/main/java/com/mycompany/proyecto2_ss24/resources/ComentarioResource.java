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
        if (comentario.getFechaComentario().equals("")) {
            String mensajeErrorDatos = "DEBE SELECCIONAR UNA FECHA";
            String JSONResponse = "{\"mensaje\":\"" + mensajeErrorDatos + "\"}";
            return Response.ok(JSONResponse).build();
        }
        if (!comentario.getFechaComentario().contains("-")) {
            String mensajeErrorDatos = "FORMATO DE FECHA INCORRECTO";
            String JSONResponse = "{\"mensaje\":\"" + mensajeErrorDatos + "\"}";
            return Response.ok(JSONResponse).build();
        }
        String[] datos = comentario.getFechaComentario().split("-");
        if (datos.length != 3) {
            String mensajeErrorDatos = "FORMATO DE FECHA INCORRECTO";
            String JSONResponse = "{\"mensaje\":\"" + mensajeErrorDatos + "\"}";
            return Response.ok(JSONResponse).build();
        }
        String yyyy = datos[0];
        String mm = datos[1];
        String dd = datos[2];
        if (!isIntegerPositive(yyyy) || !isIntegerPositive(mm) || !isIntegerPositive(dd)) {
            String mensajeErrorDatos = "FORMATO DE FECHA INCORRECTO";
            String JSONResponse = "{\"mensaje\":\"" + mensajeErrorDatos + "\"}";
            return Response.ok(JSONResponse).build();
        }
        if (Integer.parseInt(mm) > 12 && Integer.parseInt(dd) > 31 && Integer.parseInt(yyyy) > 9999) {
            String mensajeErrorDatos = "FORMATO DE FECHA INCORRECTO";
            String JSONResponse = "{\"mensaje\":\"" + mensajeErrorDatos + "\"}";
            return Response.ok(JSONResponse).build();
        }
        SuscriptorDB dataSuscripcion = new SuscriptorDB();
        dataSuscripcion.crearComentario(comentario, comentario.getComentarista());
        String mensaje = "exito";
        String JSONResponse = "{\"mensaje\":\"" + mensaje + "\"}";
        return Response.ok(JSONResponse).build();
    }
    
    private boolean isIntegerPositive(String texto) {
        try {
            int numero = Integer.parseInt(texto);
            return numero > 0;
        } catch (NumberFormatException e) {
            System.out.println("Texto ingresado NO puede ser Numero Entero");
            return false;
        }
    }
    
}
