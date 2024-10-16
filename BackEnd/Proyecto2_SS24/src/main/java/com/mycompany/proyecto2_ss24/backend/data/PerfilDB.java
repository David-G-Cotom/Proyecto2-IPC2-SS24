/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.data;

import com.mycompany.proyecto2_ss24.backend.model.users.UsuarioAplicacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Carlos Cotom
 */
public class PerfilDB {
    
    private final ConexionDB data = ConexionDB.getInstance();
    private Connection connection = null;

    public PerfilDB() {
        try {
            this.connection = data.getConnection();
        } catch (SQLException ex) {
            System.out.println("Error en Conexxion a DB");
        }
    }
    
    public byte[] getImageUsuario(int idUsuario) {
        String query = "SELECT foto FROM usuario WHERE id_usuario = ?";
        byte[] dataImage = null;
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idUsuario);
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    dataImage = resul.getBytes("foto");
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir el Contenido de la Foto: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error en recibir el Contenido de la Foto: " + e);
        }
        return dataImage;
    }
    
    public UsuarioAplicacion getUsuario(int idUsuario) {
        String query = "SELECT * FROM usuario WHERE id_usuario = ?";
        UsuarioAplicacion usuario = null;
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idUsuario);
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    usuario = new UsuarioAplicacion();
                    usuario.setIdUsuario(idUsuario);
                    usuario.setHobbies(resul.getString("hobbie"));
                    usuario.setTemasInteres(resul.getString("temas_interes"));
                    usuario.setDescripcion(resul.getString("descripcion"));
                    usuario.setGustos(resul.getString("gustos"));
                    usuario.setNombre(resul.getString("nombre"));
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir a un Usuario " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error en recibir a un Usuario " + e);
        }
        return usuario;
    }
    
    public boolean actualizarPerfil(UsuarioAplicacion nuevoUsuario) {
        String query = "UPDATE usuario SET foto = ?, hobbie = ?, temas_interes = ?, descripcion = ?, gustos = ?, nombre = ?, user_name = ?, user_password = ? WHERE id_usuario = ?";
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setString(1, nuevoUsuario.getFoto());
            prepared.setString(2, nuevoUsuario.getHobbies());
            prepared.setString(3, nuevoUsuario.getTemasInteres());
            prepared.setString(4, nuevoUsuario.getDescripcion());
            prepared.setString(5, nuevoUsuario.getGustos());
            prepared.setString(6, nuevoUsuario.getNombre());
            prepared.setString(7, nuevoUsuario.getUserName());
            prepared.setString(8, nuevoUsuario.getPassword());
            prepared.setInt(9, nuevoUsuario.getIdUsuario());
            prepared.executeUpdate();
            System.out.println("Perfil del Usuario Actualizado!!!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al Actualizar los Datos del Usuario " + e);
            return false;
        }
    }
    
}
