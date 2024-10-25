/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.data;

import com.mycompany.proyecto2_ss24.backend.model.users.UsuarioAplicacion;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Carlos Cotom
 */
public class LogInUsuarioDB {

    private final ConexionDB data = ConexionDB.getInstance();
    private Connection connection = null;

    public LogInUsuarioDB() {
        try {
            this.connection = data.getConnection();
        } catch (SQLException ex) {
            System.out.println("Error en Conexxion a DB");
        }
    }

    public UsuarioAplicacion getUsuario(String userName, String password) {
        String query = "SELECT * FROM usuario WHERE user_name = ? AND user_password = ?";
        UsuarioAplicacion usuario = null;
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setString(1, userName);
            prepared.setString(2, password);
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    int idUsuario = resul.getInt("id_usuario");
                    String foto = resul.getString("foto");
                    String hobbies = resul.getString("hobbie");
                    String temasInteres = resul.getString("temas_interes");
                    String descripcion = resul.getString("descripcion");
                    String gustos = resul.getString("gustos");
                    int tipoUsuairo = resul.getInt("tipo_usuario");
                    String nombre = resul.getString("nombre");
                    usuario = new UsuarioAplicacion(foto, hobbies, temasInteres, descripcion, gustos, userName, password, tipoUsuairo, nombre, idUsuario);                    
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir usuario: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error en recibir usuario: " + e);
        }
        return usuario;
    }
    
    public int getIdTipoUsuario(String tipoUsuario) {
        String query = "SELECT id_tipo FROM tipo_usuario WHERE tipo = ?";
        int idTipo = 0;
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setString(1, tipoUsuario);
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    idTipo = resul.getInt("id_tipo");                    
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir el id del tipo de usuario: " + e);
            }
        } catch (SQLException e) {            
            System.out.println("Error en recibir el id del tipo de usuario: " + e);
        }
        return idTipo;
    }
    
    public void crearUsuario(UsuarioAplicacion usuario, InputStream foto) {
        String query = "INSERT INTO usuario (foto, tipo_usuario, user_name, user_password, nombre, hobbie, temas_interes, descripcion, gustos) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";        
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setBlob(1, foto);
            prepared.setInt(2, usuario.getIdTipoUsuario());
            prepared.setString(3, usuario.getUserName());
            prepared.setString(4, usuario.getPassword());
            prepared.setString(5, usuario.getNombre());
            prepared.setString(6, "");
            prepared.setString(7, "");
            prepared.setString(8, "");
            prepared.setString(9, "");
            prepared.executeUpdate();
            System.out.println("Nuevo Usuario Creado");
        } catch (SQLException e) {
            System.out.println("Error en crear un Usuario: " + e);
        }
        this.crearTipoUsuario(usuario);
    }
    
    private void crearTipoUsuario(UsuarioAplicacion usuario) {
        int idUsuario = this.getIdUsuario(usuario);
        switch (usuario.getIdTipoUsuario()) {
            case 1 -> //editor
                this.crearEditor(idUsuario);
            case 2 -> //suscriptor
                this.crearSuscriptor(idUsuario);
            case 3 -> //inverssionista
                this.crearInversionista(idUsuario);
            case 4 -> //administrador
                this.crearSisAdmin(idUsuario);
        }
    }
    
    public int getIdUsuario(UsuarioAplicacion usuario) {
        String query = "SELECT id_usuario FROM usuario WHERE user_name = ? AND user_password = ?";
        int idUsuario = 0;
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setString(1, usuario.getUserName());
            prepared.setString(2, usuario.getPassword());
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    idUsuario = resul.getInt("id_usuario");                    
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir el id del usuario: " + e);
            }
        } catch (SQLException e) {            
            System.out.println("Error en recibir el id del usuario: " + e);
        }
        return idUsuario;
    }
    
    private void crearEditor(int idUsuario) {
        String query = "INSERT INTO editor (usuario, credito) VALUES (?, ?)";
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idUsuario);
            prepared.setDouble(2, 0);
            prepared.executeUpdate();
            System.out.println("Nuevo Editor Creado!!!");
        } catch (SQLException e) {
            System.out.println("Error en crear un Editor: " + e);
        }
    }
    private void crearSuscriptor(int idUsuario) {
        String query = "INSERT INTO suscriptor (usuario) VALUES (?)";
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idUsuario);
            prepared.executeUpdate();
            System.out.println("Nuevo Suscriptor Creado!!!");
        } catch (SQLException e) {
            System.out.println("Error en crear un Suscriptor: " + e);
        }
    }
    private void crearInversionista(int idUsuario) {
        String query = "INSERT INTO inversionista (credito, usuario) VALUES (?, ?)";
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setDouble(1, 0);
            prepared.setInt(2, idUsuario);
            prepared.executeUpdate();
            System.out.println("Nuevo Inversionista Creado!!!");
        } catch (SQLException e) {
            System.out.println("Error en crear un Inversionista: " + e);
        }
    }
    private void crearSisAdmin(int idUsuario) {
        String query = "INSERT INTO administrador_sistema (usuario) VALUES (?)";
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idUsuario);
            prepared.executeUpdate();
            System.out.println("Nuevo Administrador Creado!!!");
        } catch (SQLException e) {
            System.out.println("Error en crear un Administrador: " + e);
        }
    }

}
