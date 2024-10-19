/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.resources;

import com.mycompany.proyecto2_ss24.backend.data.RevistaDB;
import com.mycompany.proyecto2_ss24.backend.data.SuscriptorDB;
import com.mycompany.proyecto2_ss24.backend.model.Like;
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
@Path("RegistroLike")
public class LikeResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearRegistro(Like like) {
        SuscriptorDB dataSuscriptor = new SuscriptorDB();
        if (!dataSuscriptor.crearLike(like.getIdUsuario(), like.getIdRevista())) {
            String mensaje = "error";
            String JSONResponse = "{\"mensaje\":\"" + mensaje + "\"}";
            return Response.ok(JSONResponse).build();
        }
        RevistaDB dataRevista = new RevistaDB();
        dataRevista.actualizarLikes(like.getIdRevista());
        String mensaje = "exito";
        String JSONResponse = "{\"mensaje\":\"" + mensaje + "\"}";
        return Response.ok(JSONResponse).build();
    }

}
