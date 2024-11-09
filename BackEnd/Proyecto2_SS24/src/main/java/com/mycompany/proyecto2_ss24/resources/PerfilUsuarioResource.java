/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.resources;

import com.mycompany.proyecto2_ss24.backend.data.CarteraDB;
import com.mycompany.proyecto2_ss24.backend.data.EditorDB;
import com.mycompany.proyecto2_ss24.backend.data.PerfilDB;
import com.mycompany.proyecto2_ss24.backend.model.users.UsuarioAplicacion;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
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
@Path("PerfilUsuario")
public class PerfilUsuarioResource {
    
    @PUT
    @Path("v1") //Version en donde se actualiza la foto
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePerfil1(@FormDataParam("Foto") InputStream foto,
            @FormDataParam("Foto") FormDataContentDisposition detalleFoto,
            @FormDataParam("UserName") String userName, @FormDataParam("Password") String password,
            @FormDataParam("Nombre") String nombre,@FormDataParam("Hobbies") String hobbies,
            @FormDataParam("TemasInteres") String temasInteres,@FormDataParam("Desripcion") String descripcion,
            @FormDataParam("Gustos") String gustos, @FormDataParam("IDusuario") String idUsuairo,
            @FormDataParam("IDtipoUsuario") String idTipoUsuairo) {
        if (userName.equals("") || password.equals("")) {
            String mensaje = "LOS CAMPOS DE USERNAME Y PASSWORD NO PUEDEN ESTAR VACIOS";
            String JSONRespones = "{\"mensaje\":\"" + mensaje +"\"}";
            return Response.ok(JSONRespones).build();
        }
        String codificado = Base64.getEncoder().encodeToString(password.getBytes());
        UsuarioAplicacion nuevoUsuario = new UsuarioAplicacion();
        nuevoUsuario.setUserName(userName);
        nuevoUsuario.setPassword(codificado);
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setHobbies(hobbies);
        nuevoUsuario.setTemasInteres(temasInteres);
        nuevoUsuario.setDescripcion(descripcion);
        nuevoUsuario.setGustos(gustos);
        nuevoUsuario.setIdUsuario(Integer.parseInt(idUsuairo));
        nuevoUsuario.setIdTipoUsuario(Integer.parseInt(idTipoUsuairo));
        PerfilDB dataPerfil = new PerfilDB();
        if (dataPerfil.actualizarPerfil(nuevoUsuario, foto)) {
            String JSONResponse = "{\"mensaje\": \"exito\", \"usuario\":" + nuevoUsuario.toJSON() + "}";
            return Response.ok(JSONResponse).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
    
    @PUT
    @Path("v2") //Version en donde no se actuliza la foto
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePerfil2(UsuarioAplicacion usuario) {
        if (usuario.getUserName().equals("") || usuario.getPassword().equals("")) {
            String mensaje = "LOS CAMPOS DE USERNAME Y PASSWORD NO PUEDEN ESTAR VACIOS";
            String JSONRespones = "{\"mensaje\":\"" + mensaje +"\"}";
            return Response.ok(JSONRespones).build();
        }
        String codificado = Base64.getEncoder().encodeToString(usuario.getPassword().getBytes());
        usuario.setPassword(codificado);
        PerfilDB dataPerfil = new PerfilDB();
        if (dataPerfil.actualizarPerfil(usuario)) {
            String JSONResponse = "{\"mensaje\": \"exito\", \"usuario\":" + usuario.toJSON() + "}";
            return Response.ok(JSONResponse).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
    
    @GET
    @Path("{idEditor}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerfilEditor(@PathParam("idEditor") int idEditor) {
        EditorDB dataSuscriptor = new EditorDB();
        UsuarioAplicacion editor = dataSuscriptor.getEditor(idEditor);
        return Response.ok(editor).build();
    }
    
    @GET
    @Path("credito/{idUsuario}/{idTipoUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCreditor(@PathParam("idUsuario") int idUsuario, @PathParam("idTipoUsuario") int idTipoUsuario) {
        CarteraDB dataCartera = new CarteraDB();
        String tablaConsulta = "";
        if (idTipoUsuario == 1) {
            tablaConsulta = "editor";
        } else if (idTipoUsuario == 3) {
            tablaConsulta = "inversionista";
        }
        double credito = dataCartera.getCredito(idUsuario, tablaConsulta);
        return Response.ok(credito).build();
    }
    
}
