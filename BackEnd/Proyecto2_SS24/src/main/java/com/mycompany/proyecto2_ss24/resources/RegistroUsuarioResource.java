/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.resources;

import com.mycompany.proyecto2_ss24.backend.controllers.ArchivosController;
import com.mycompany.proyecto2_ss24.backend.controllers.RegistroUsuarioController;
import com.mycompany.proyecto2_ss24.backend.model.users.PerfilTS;
import com.mycompany.proyecto2_ss24.backend.model.users.UsuarioAplicacion;
import com.mycompany.proyecto2_ss24.backend.model.users.UsuarioAplicacionTS;
import com.mycompany.proyecto2_ss24.backend.model.users.UsuarioTS;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.InputStream;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author Carlos Cotom
 */
@Path("RegistroUsuario")
public class RegistroUsuarioResource {

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRegistro(@FormDataParam("Foto") InputStream foto,
            @FormDataParam("Foto") FormDataContentDisposition detalleFoto,
            @FormDataParam("UserName") String userName, @FormDataParam("Password") String password,
            @FormDataParam("TipoUsuario") String tipoUsuario, @FormDataParam("Nombre") String nombre) {
        ArchivosController archivoController = new ArchivosController();
        String pathFoto = archivoController.guardarArchivo(foto, "FOTO-" + nombre + detalleFoto.getFileName());
        UsuarioAplicacionTS usuario = new UsuarioAplicacionTS(new UsuarioTS(tipoUsuario, userName, password), new PerfilTS(pathFoto, nombre));
        RegistroUsuarioController controlRegistro = new RegistroUsuarioController(usuario);
        String mensajeErrorDatos = controlRegistro.verificarDatosUsuario();
        if (!mensajeErrorDatos.equals("")) {
            String JSONRespones = "{\"mensaje\":\"" + mensajeErrorDatos +"\"}";
            return Response.ok(JSONRespones).build();
        }
        UsuarioAplicacion existeUsuario = controlRegistro.verificarUsuarioExistente();
        if (existeUsuario == null) {
            mensajeErrorDatos = "Error en los campos de 'usuario' y 'password'. Intente con otros datos";
            String JSONRespones = "{\"mensaje\":\"" + mensajeErrorDatos +"\"}";
            return Response.ok(JSONRespones).build();
        }
        String JSONResponse = controlRegistro.crearUsuario(existeUsuario);
        return Response.ok(JSONResponse).build();
    }
    
}
