/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.resources;

import com.mycompany.proyecto2_ss24.backend.controllers.ArchivosController;
import com.mycompany.proyecto2_ss24.backend.data.LogInUsuarioDB;
import com.mycompany.proyecto2_ss24.backend.mode.users.UsuarioAplicacion;
import com.mycompany.proyecto2_ss24.backend.mode.users.UsuarioAplicacionTS;
import com.mycompany.proyecto2_ss24.backend.utils.ConvertirJson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.util.Base64;

/**
 *
 * @author Carlos Cotom
 */
@Path("RegistroUsuario")
public class RegistroUsuarioResource {
 
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response createRegistro(HttpServletRequest request) {
        System.out.println("ESTAMOS EN EL SERVIDOR");
        ConvertirJson convertidor = new ConvertirJson();
        ArchivosController archivoController = new ArchivosController();
        LogInUsuarioDB dataUsuario = new LogInUsuarioDB();
        try {
            String textoEntrada = request.getParameter("UsuarioAplicacion");
            Part foto = request.getPart("Foto");
            UsuarioAplicacionTS usuario = (UsuarioAplicacionTS) convertidor.getObjeto(textoEntrada, UsuarioAplicacionTS.class);
            String pathFoto = archivoController.guardarArchivo(foto, "FOTO-" + usuario.getPerfil().getNombre(), ".png", request.getServletContext().getRealPath(""));
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
                dataUsuario.crearUsuario(userAplication);
                return Response.status(Response.Status.CREATED).build();
            }
        } catch (ServletException | IOException e) {
            System.out.println("ERROR AL REGISTRAR USUARIO: " + e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
    
}
