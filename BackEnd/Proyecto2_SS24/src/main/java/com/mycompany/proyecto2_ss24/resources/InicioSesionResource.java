/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.resources;

import com.mycompany.proyecto2_ss24.backend.utils.TokenService;
import com.mycompany.proyecto2_ss24.backend.data.LogInUsuarioDB;
import com.mycompany.proyecto2_ss24.backend.model.users.UsuarioAplicacion;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Base64;

/**
 *
 * @author Carlos Cotom
 */
@Path("InicioSesion")
public class InicioSesionResource {

    @GET
    @Path("{user}/{pas}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSesion(@PathParam("user") String userName, @PathParam("pas") String password) {
        if (userName.equals("") && password.equals("")) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        String codificado = Base64.getEncoder().encodeToString(password.getBytes());
        LogInUsuarioDB dataUsuario = new LogInUsuarioDB();
        UsuarioAplicacion usuario = dataUsuario.getUsuario(userName, codificado);
        if (usuario == null) {
            System.out.println("NO HAY USUARIO COINCIDENTE");
            String JSONRespones = "{\"mensaje\":\"No hay usuario\"}";
            return Response.ok(JSONRespones).build();
        }
        System.out.println("USUARIO ENCONTRADO");
        TokenService tokenService = new TokenService();
        String token = tokenService.generarToken(usuario);
        String JSONRespones = "{\"mensaje\":\"Si hay usuario\", \"token\":\"" + token + "\", \"usuario\":" + usuario.toJSON() + "}";
        return Response.ok(JSONRespones).build();
    }

}
