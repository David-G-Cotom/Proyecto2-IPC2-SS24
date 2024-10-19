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
public class EditorDB {
    
    private final ConexionDB data = ConexionDB.getInstance();
    private Connection connection = null;

    public EditorDB() {
        try {
            this.connection = data.getConnection();
        } catch (SQLException ex) {
            System.out.println("Error en Conexxion a DB");
        }
    }
    
    public UsuarioAplicacion getEditor(int idEditor) {
        String query = "SELECT * FROM usuario WHERE id_usuario = ?";
        int idUsuarioEditor = this.getIdUsuarioEditor(idEditor);
        UsuarioAplicacion editor = new UsuarioAplicacion();
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idUsuarioEditor);
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    editor.setIdUsuario(idUsuarioEditor);
                    editor.setHobbies(resul.getString("hobbie"));
                    editor.setTemasInteres(resul.getString("temas_interes"));
                    editor.setDescripcion(resul.getString("descripcion"));
                    editor.setGustos(resul.getString("gustos"));
                    editor.setNombre(resul.getString("nombre"));                    
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir al Autor: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error en recibir al Autor: " + e);
        }
        return editor;
    }
    
    private int getIdUsuarioEditor(int idEditor) {
        String query = "SELECT usuario FROM editor WHERE id_editor = ?";
        int idUsuarioEditor = 0;
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idEditor);
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    idUsuarioEditor = resul.getInt("usuario");
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir el id del Usuario Autor: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error en recibir el id del Usuario Autor: " + e);
        }
        return idUsuarioEditor;
    }
    
}
