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
    
    public int getIdEditor(int idRevista) {
        String query ="SELECT editor FROM revista WHERE id_revista = ?";
        int idEditor = 0;
        try (PreparedStatement prepared = this.connection.prepareStatement(query)){
            prepared.setInt(1, idRevista);
            try (ResultSet resul = prepared.executeQuery()){
                if (resul.next()) {
                    idEditor = resul.getInt("editor");
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir el idEditor(idRevista) en EditorDB: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error en recibir el idEditor(idRevista) en EditorDB: " + e);
        }
        return idEditor;
    }
    
    public double getCreditoEditor(int idEditor) {
        String query = "SELECT credito FROM editor WHERE id_editor = ?";
        double creditoEditor = 0;
        try (PreparedStatement prepared = this.connection.prepareStatement(query)){
            prepared.setInt(1, idEditor);
            try (ResultSet resul = prepared.executeQuery()){
                if (resul.next()) {
                    creditoEditor = resul.getDouble("credito");
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir el creditoEditor(idEditor) en EditorDB: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error en recibir el creditoEditor(idEditor) en EditorDB: " + e);
        }
        return creditoEditor;
    }
    
    public double getCostoOcultacionRevista(int idRevista) {
        String query = "SELECT costo_ocultacion FROM revista WHERE id_revista = ?";
        double costoOcultacion = 0;
        try (PreparedStatement prepared = this.connection.prepareStatement(query)){
            prepared.setInt(1, idRevista);
            try (ResultSet resul = prepared.executeQuery()){
                if (resul.next()) {
                    costoOcultacion = resul.getDouble("costo_ocultacion");
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir el costoOcultacion(idRevista) en EditorDB: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error en recibir el costoOcultacion(idRevista) en EditorDB: " + e);
        }
        return costoOcultacion;
    }
    
    public void bloquearAnunciosRevista(int idRevista) {
        String query = "UPDATE revista SET estado_ocultacion_anuncios = ? WHERE id_revista = ?";
        try (PreparedStatement prepared = connection.prepareStatement(query)) {
            prepared.setBoolean(1, true);
            prepared.setInt(2, idRevista);
            prepared.executeUpdate();
            System.out.println("estado_ocultacion_anuncios de Revista " + idRevista + " Actualizado a true!!!");
        } catch (SQLException e) {
            System.out.println("Error al Actualizar en bloquearAnunciosRevista(idRevista) en EditorDB: " + e);
        }
    }
    
    public void actualizarCreditoEditorGasto(int idEditor, double cantidadGastada) {
        String query = "UPDATE editor SET credito= ? WHERE id_editor = ?";
        double creditoActual = this.getCreditoEditor(idEditor);
        creditoActual -= cantidadGastada;
        try (PreparedStatement prepared = connection.prepareStatement(query)) {
            prepared.setDouble(1, creditoActual);
            prepared.setInt(2, idEditor);
            prepared.executeUpdate();
            System.out.println("Credito del Editor " + idEditor + " actualizado!!!");
        } catch (SQLException e) {
            System.out.println("Error al actualizar el Credito del Editor en EditorDB: " + e);
        }
    }
    
}
