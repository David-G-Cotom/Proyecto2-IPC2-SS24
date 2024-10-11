/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.resources;

import com.mycompany.proyecto2_ss24.backend.controllers.ArchivosController;
import com.mycompany.proyecto2_ss24.backend.data.LogInUsuarioDB;
import com.mycompany.proyecto2_ss24.backend.mode.users.PerfilTS;
import com.mycompany.proyecto2_ss24.backend.mode.users.UsuarioAplicacion;
import com.mycompany.proyecto2_ss24.backend.mode.users.UsuarioAplicacionTS;
import com.mycompany.proyecto2_ss24.backend.mode.users.UsuarioTS;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.InputStream;
import java.util.Base64;
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
            @FormDataParam("UserName") String userName,
            @FormDataParam("Password") String password,
            @FormDataParam("TipoUsuario") String tipoUsuario,
            @FormDataParam("Nombre") String nombre) {
        System.out.println(userName);
        System.out.println(password);
        System.out.println(tipoUsuario);
        System.out.println(nombre);
        System.out.println("FOTO: " + detalleFoto.getFileName());
        ArchivosController archivoController = new ArchivosController();
        LogInUsuarioDB dataUsuario = new LogInUsuarioDB();
        UsuarioAplicacionTS usuario = new UsuarioAplicacionTS(new UsuarioTS(tipoUsuario, userName, password), new PerfilTS("", nombre));
        String pathFoto = archivoController.guardarArchivo(foto, "FOTO-" + usuario.getPerfil().getNombre() + detalleFoto.getFileName());
        usuario.getPerfil().setPathFoto(pathFoto);
        UsuarioAplicacion userAplication = new UsuarioAplicacion();
        userAplication.setUserName(usuario.getUsuario().getUserName());
        String codificado = Base64.getEncoder().encodeToString(usuario.getUsuario().getPassword().getBytes());
        userAplication.setPassword(codificado);
        userAplication.setNombre(usuario.getPerfil().getNombre());
        userAplication.setFoto(usuario.getPerfil().getPathFoto());
        int idTipoUsuario = dataUsuario.getIdTipoUsuario(usuario.getUsuario().getTipoUsuario());
        userAplication.setIdTipoUsuario(idTipoUsuario);
        UsuarioAplicacion existeUsuario = dataUsuario.getUsuario(userAplication.getUserName(), userAplication.getPassword());
        if (existeUsuario != null) {
            System.out.println("YA EXISTE EL USUARIO");
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            System.out.println("CREANDO EL NUEVO USUARIO");
            dataUsuario.crearUsuario(userAplication);
            return Response.ok(userAplication).build();
        }
    }
    
}
