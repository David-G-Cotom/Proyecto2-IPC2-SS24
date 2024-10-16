/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.model.users;

/**
 *
 * @author Carlos Cotom
 */
public class UsuarioAplicacionTS {
    
    private UsuarioTS usuario;
    private PerfilTS perfil;

    public UsuarioAplicacionTS(UsuarioTS usuario, PerfilTS perfil) {
        this.usuario = usuario;
        this.perfil = perfil;
    }

    public UsuarioTS getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioTS usuario) {
        this.usuario = usuario;
    }

    public PerfilTS getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilTS perfil) {
        this.perfil = perfil;
    }
    
}
