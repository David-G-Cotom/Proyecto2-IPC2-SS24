/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.data;

import com.mycompany.proyecto2_ss24.backend.model.reportes.ContenidoReporteComentarios;
import com.mycompany.proyecto2_ss24.backend.model.reportes.ReporteComentarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Carlos Cotom
 */
public class ReporteEditorDB {

    private final ConexionDB data = ConexionDB.getInstance();
    private Connection connection = null;

    public ReporteEditorDB() {
        try {
            this.connection = data.getConnection();
        } catch (SQLException ex) {
            System.out.println("Error en Conexion a DB con ReporteEditorDB");
        }
    }

    public ArrayList<ContenidoReporteComentarios> getReporteComentarios(String clausulaWHERE, int cantidadInterrogantes, ReporteComentarios datos) {
        String query = "SELECT * FROM comentario " + clausulaWHERE;
        System.out.println(query);
        System.out.println("? = " + cantidadInterrogantes);
        ArrayList<ContenidoReporteComentarios> contenido = new ArrayList<>();
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            switch (cantidadInterrogantes) {
                case 1 ->
                    prepared.setInt(1, Integer.parseInt(datos.getIdRevista()));
                case 2 -> {
                    prepared.setString(1, datos.getFechaInicio());
                    prepared.setString(2, datos.getFechaFin());
                }
                case 3 -> {
                    prepared.setString(1, datos.getFechaInicio());
                    prepared.setString(2, datos.getFechaFin());
                    prepared.setInt(3, Integer.parseInt(datos.getIdRevista()));
                }
            }
            try (ResultSet resul = prepared.executeQuery()) {
                while (resul.next()) {
                    String nombreRevista = this.getNombreRevista(resul.getInt("revista"));
                    String fechaComentario = resul.getString("fecha_comentario");
                    String comentario = resul.getString("contenido");
                    String nombreComentarista = this.getNomreComentarista(resul.getInt("suscriptor"));
                    ContenidoReporteComentarios contenidoComentario = new ContenidoReporteComentarios("0",
                            nombreRevista, fechaComentario, comentario, nombreComentarista);
                    contenido.add(contenidoComentario);
                }
            } catch (SQLException e) {
                System.out.println("Error getReporteComentarios() en ReporteEditorDB: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error getReporteComentarios() en ReporteEditorDB: " + e);
        }
        return contenido;
    }

    private String getNombreRevista(int idRevista) {
        String query = "SELECT nombre FROM revista WHERE id_revista = ?";
        String nombreRevista = "";
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idRevista);
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    nombreRevista = resul.getString("nombre");
                }
            } catch (SQLException e) {
                System.out.println("Error getNombreRevista(idRevista) en ReporteEditorDB: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error getNombreRevista(idRevista) en ReporteEditorDB: " + e);
        }
        return nombreRevista;
    }

    private String getNomreComentarista(int idSuscriptor) {
        int idUsuarioSuscriptor = this.getIdUsuarioSuscriptor(idSuscriptor);
        String nombreComentarista = this.getNombreSuscriptor(idUsuarioSuscriptor);
        return nombreComentarista;
    }

    private int getIdUsuarioSuscriptor(int idSuscriptor) {
        String query = "SELECT usuario FROM suscriptor WHERE id_suscriptor = ?";
        int idUsuarioSuscriptor = 0;
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idSuscriptor);
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    idUsuarioSuscriptor = resul.getInt("usuario");
                }
            } catch (SQLException e) {
                System.out.println("Error getIdUsuarioSuscriptor(idSuscriptor) en ReporteEditorDB: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error getIdUsuarioSuscriptor(idSuscriptor) en ReporteEditorDB: " + e);
        }
        return idUsuarioSuscriptor;
    }

    private String getNombreSuscriptor(int idUsuario) {
        String query = "SELECT nombre FROM usuario WHERE id_usuario = ?";
        String nombreSuscriptor = "";
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idUsuario);
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    nombreSuscriptor = resul.getString("nombre");
                }
            } catch (SQLException e) {
                System.out.println("Error getNombreSuscriptor(idUsuario) en ReporteEditorDB: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error getNombreSuscriptor(idUsuario) en ReporteEditorDB: " + e);
        }
        return nombreSuscriptor;
    }

    public ArrayList<ContenidoReporteComentarios> getFullReporteComentarios(ArrayList<Integer> idsRevistaEditor) {
        ArrayList<ContenidoReporteComentarios> reporte = new ArrayList<>();
        for (Integer id : idsRevistaEditor) {
            String query = "SELECT * FROM comentario WHERE revista = ?";
            try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
                prepared.setInt(1, id);
                try (ResultSet resul = prepared.executeQuery()) {
                    while (resul.next()) {
                        String nombreRevista = this.getNombreRevista(resul.getInt("revista"));
                        String fechaComentario = resul.getString("fecha_comentario");
                        String comentario = resul.getString("contenido");
                        String nombreComentarista = this.getNomreComentarista(resul.getInt("suscriptor"));
                        ContenidoReporteComentarios contenidoComentario = new ContenidoReporteComentarios("0",
                                nombreRevista, fechaComentario, comentario, nombreComentarista);
                        reporte.add(contenidoComentario);
                    }
                } catch (SQLException e) {
                    System.out.println("Error getFullReporteComentarios(idsRevistaEditor) en ReporteEditorDB: " + e);
                }
            } catch (SQLException e) {
                System.out.println("Error getFullReporteComentarios(idsRevistaEditor) en ReporteEditorDB: " + e);
            }
        }
        return reporte;
    }

    public int getIdEditor(int idUsuario) {
        String query = "SELECT id_editor FROM editor WHERE usuario = ?";
        int idEditor = 0;
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idUsuario);
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    idEditor = resul.getInt("id_editor");
                }
            } catch (SQLException e) {
                System.out.println("Error getIdEditor(idUsuario) en ReporteEditorDB: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error getIdEditor(idUsuario) en ReporteEditorDB: " + e);
        }
        return idEditor;
    }

    public ArrayList<Integer> getIdsRevistasEditor(int idEditor) {
        String query = "SELECT id_revista FROM revista WHERE editor = ?";
        ArrayList<Integer> ids = new ArrayList<>();
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idEditor);
            try (ResultSet resul = prepared.executeQuery()) {
                while (resul.next()) {
                    ids.add(resul.getInt("id_revista"));
                }
            } catch (SQLException e) {
                System.out.println("Error getIdsRevistasEditor(idEditor) en ReporteEditorDB: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error getIdsRevistasEditor(idEditor) en ReporteEditorDB: " + e);
        }
        return ids;
    }

}
