/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.controllers;

import com.mycompany.proyecto2_ss24.backend.data.LogInUsuarioDB;
import com.mycompany.proyecto2_ss24.backend.model.users.UsuarioAplicacion;
import com.mycompany.proyecto2_ss24.backend.model.users.UsuarioAplicacionTS;
import com.mycompany.proyecto2_ss24.backend.utils.TokenService;
import java.util.Base64;

/**
 *
 * @author Carlos Cotom
 */
public class RegistroUsuarioController {
    
    private final LogInUsuarioDB dataUsuario = new LogInUsuarioDB();
    private final UsuarioAplicacionTS usuario;

    public RegistroUsuarioController(UsuarioAplicacionTS usuario) {
        this.usuario = usuario;
    }
    
    public String verificarDatosUsuario() {
        if (isDatosVacios()) {
            return "DEBE COMPLETAR TODOS LOS CAMPOS DEL FORMULARIO";
        }
        if (!isDatosValidos().equals("")) {
            return isDatosValidos();
        }
        return "";
    }
    
    private boolean isDatosVacios() {        
        return usuario.getUsuario().getUserName().equals("")
                || usuario.getUsuario().getPassword().equals("")
                || usuario.getPerfil().getPathFoto().equals("")
                || usuario.getPerfil().getNombre().equals("");
    }
    
    private String isDatosValidos() {
        if (!usuario.getUsuario().getTipoUsuario().equals("editor")
                && !usuario.getUsuario().getTipoUsuario().equals("suscriptor")
                && !usuario.getUsuario().getTipoUsuario().equals("inversionista")
                && !usuario.getUsuario().getTipoUsuario().equals("administrador_sistema")) {
            return "ERROR AL SELECCIONAR EL TIPO DE USUARIO";
        }
        if (!this.extensCorrect(usuario.getPerfil().getPathFoto())) {
            return "DEBE ELEGIR UN ARCHIVO DE IMAGEN PARA LA FOTO (.ico .png .jpg .jpeg)";
        }
        return "";
    }
    
    private boolean extensCorrect(String fileName) {
        String[] extens = {".ico", ".png", ".jpg", ".jpeg"};        
        for (String exten : extens) {
            if (fileName.toLowerCase().endsWith(exten)) {
                return true;
            }
        }
        return false;
    }
    
    public UsuarioAplicacion verificarUsuarioExistente() {
        UsuarioAplicacion userAplication = new UsuarioAplicacion();
        userAplication.setUserName(usuario.getUsuario().getUserName());
        String codificado = Base64.getEncoder().encodeToString(usuario.getUsuario().getPassword().getBytes());
        userAplication.setPassword(codificado);
        userAplication.setNombre(usuario.getPerfil().getNombre());
        userAplication.setFoto(usuario.getPerfil().getPathFoto());
        int idTipoUsuario = dataUsuario.getIdTipoUsuario(usuario.getUsuario().getTipoUsuario());
        userAplication.setIdTipoUsuario(idTipoUsuario);
        UsuarioAplicacion usuarioExistente = dataUsuario.getUsuario(userAplication.getUserName(), userAplication.getPassword());
        if (usuarioExistente != null) {
            return null;
        }
        return userAplication;
    }
    
    public String crearUsuario(UsuarioAplicacion nuevoUsuario) {
        dataUsuario.crearUsuario(nuevoUsuario);
        System.out.println("CREANDO EL NUEVO USUARIO");
        TokenService tokenService = new TokenService();
        String token = tokenService.generarToken(nuevoUsuario);
        String mensaje = "nuevo";
        return "{\"mensaje\": \"" + mensaje + "\", \"token\": \"" + token + "\", \"usuario\":" + nuevoUsuario.toJSON() + "}";
    }
    
}
