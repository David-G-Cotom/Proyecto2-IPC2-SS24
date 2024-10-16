/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.model.users;

/**
 *
 * @author Carlos Cotom
 */
public class UsuarioTS {
    
    private String tipoUsuario;
    private String userName;
    private String password;

    public UsuarioTS(String tipoUsuario, String userName, String password) {        
        this.userName = userName;
        this.password = password;
        verificarTipoUsuario(tipoUsuario);
    }
    
    private void verificarTipoUsuario(String tipoUsuario) {
        switch (tipoUsuario) {
            case "editor":
                this.tipoUsuario = tipoUsuario;
                break;
            case "suscriptor":
                this.tipoUsuario = tipoUsuario;
                break;
            case "inversionista":
                this.tipoUsuario = tipoUsuario;
                break;
            case "administrador_sistema":
                this.tipoUsuario = tipoUsuario;
                break;
            default:
                this.tipoUsuario = "";
        }
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
