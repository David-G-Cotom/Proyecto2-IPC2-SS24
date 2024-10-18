/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.resources;

import com.mycompany.proyecto2_ss24.backend.data.SuscripcionDB;
import com.mycompany.proyecto2_ss24.backend.model.Revista;
import com.mycompany.proyecto2_ss24.backend.model.RevistaTS;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;

/**
 *
 * @author Carlos Cotom
 */
@Path("RevistaNoSuscrita")
public class RevistaNoSuscritaResource {
    
    @GET
    @Path("{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRevistasEditor(@PathParam("idUsuario") int idUsuario) {
        SuscripcionDB dataRevistas = new SuscripcionDB();
        ArrayList<Revista> revistasJava = dataRevistas.getRevistasNoSuscritas(idUsuario);
        ArrayList<RevistaTS> revistas = new ArrayList<>();
        for (Revista revistaJ : revistasJava) {
            RevistaTS revistaTS = new RevistaTS();
            revistaTS.setPuedeComentarse(revistaJ.isPuedeComentarse());
            revistaTS.setPuedeSuscribirse(revistaJ.isPuedeSuscribirse());
            revistaTS.setPuedeTenerLikes(revistaJ.isPuedeTenerLikes());
            revistaTS.setDescripcion(revistaJ.getDescripcion());
            revistaTS.setCategoria(revistaJ.getCategoria().toString());
            String[] etiquetas = new String[revistaJ.getEtiquetas().size()];
            for (int i = 0; i < etiquetas.length; i++) {
                etiquetas[i] = revistaJ.getEtiquetas().get(i).toString();
            }
            revistaTS.setEtiquetas(etiquetas);
            revistaTS.setIdUsuario(idUsuario);
            revistaTS.setLikes(revistaJ.getLikes());
            revistaTS.setComentarios(new String[0]);
            revistaTS.setCosto(0);
            revistaTS.setFechaCreacion("");
            revistaTS.setSuscripciones(new String[0]);
            revistaTS.setNombre(revistaJ.getNombreRevista());
            revistaTS.setIdRevista(revistaJ.getIdRevista());
            revistaTS.setCostoGlobal(0);
            revistaTS.setNombreEditor(revistaJ.getAutor().getNombre());
            revistas.add(revistaTS);
        }
        return Response.ok(revistas).build();
    }
    
}
