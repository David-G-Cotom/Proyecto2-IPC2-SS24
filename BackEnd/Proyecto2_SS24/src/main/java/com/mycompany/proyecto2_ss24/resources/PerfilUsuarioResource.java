/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.resources;

import com.mycompany.proyecto2_ss24.backend.data.PerfilDB;
import com.mycompany.proyecto2_ss24.backend.mode.users.UsuarioAplicacion;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author Carlos Cotom
 */
@Path("PerfilUsuario")
public class PerfilUsuarioResource {
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePerfil(UsuarioAplicacion usuario) {
        PerfilDB dataPerfil = new PerfilDB();
        if (dataPerfil.actualizarPerfil(usuario)) {
            return Response.ok(usuario).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
    
}
