/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.data;

import com.mycompany.proyecto2_ss24.backend.model.reportes.ContenidoReporteCompraAnuncio;
import com.mycompany.proyecto2_ss24.backend.model.reportes.ReporteCompraAnuncio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Carlos Cotom
 */
public class ReporteAdminDB {

    private final ConexionDB data = ConexionDB.getInstance();
    private Connection connection = null;

    public ReporteAdminDB() {
        try {
            this.connection = data.getConnection();
        } catch (SQLException ex) {
            System.out.println("Error en Conexion a DB con ReporteEditorDB");
        }
    }

    public ArrayList<ContenidoReporteCompraAnuncio> getReporteAcompraAnuncios(String clausulaWHERE, int cantidadInterrogantes, ReporteCompraAnuncio datos) {
        String query = "SELECT * FROM anuncio " + clausulaWHERE;
        System.out.println(query);
        System.out.println("? = " + cantidadInterrogantes);
        ArrayList<ContenidoReporteCompraAnuncio> contenido = new ArrayList<>();
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            switch (cantidadInterrogantes) {
                case 0 -> System.out.println("NO HAY CLAUSULA WHERE PARA EL REPORTE DE ANUNCIOS COMPRADOS");
                case 1 -> {
                    if (datos.getTipoAnuncio().equals("")) {
                        prepared.setInt(1, Integer.parseInt(datos.getPeriodoTiempo()));
                    } else {
                        prepared.setInt(1, Integer.parseInt(datos.getTipoAnuncio()));
                    }
                }
                case 2 -> {
                    if (datos.getFechaInicio().equals("") && datos.getFechaFin().equals("")) {
                        prepared.setInt(1, Integer.parseInt(datos.getTipoAnuncio()));
                        prepared.setInt(2, Integer.parseInt(datos.getPeriodoTiempo()));
                    } else {
                        prepared.setString(1, datos.getFechaInicio());
                        prepared.setString(2, datos.getFechaFin());
                    }
                }
                case 3 -> {
                    prepared.setString(1, datos.getFechaInicio());
                    prepared.setString(2, datos.getFechaFin());
                    if (datos.getTipoAnuncio().equals("")) {
                        prepared.setInt(3, Integer.parseInt(datos.getPeriodoTiempo()));
                    } else {
                        prepared.setInt(3, Integer.parseInt(datos.getTipoAnuncio()));
                    }
                }
                case 4 -> {
                    prepared.setString(1, datos.getFechaInicio());
                    prepared.setString(2, datos.getFechaFin());
                    prepared.setInt(3, Integer.parseInt(datos.getTipoAnuncio()));
                    prepared.setInt(4, Integer.parseInt(datos.getPeriodoTiempo()));
                }
            }
            try (ResultSet resul = prepared.executeQuery()) {
                while (resul.next()) {
                    double costo = resul.getDouble("costo");
                    int idTipoAnuncio = resul.getInt("tipo_anuncio");
                    String tipoAnuncio = "";
                    switch (idTipoAnuncio) {
                        case 1 ->
                            tipoAnuncio = "Anuncio de Texto";
                        case 2 ->
                            tipoAnuncio = "Anuncio de Texto e Imagen";
                        case 3 ->
                            tipoAnuncio = "Anuncio de Video";
                    }
                    int idUsuarioAnunciante = this.getIdUsuarioAnunciante(resul.getInt("inversionista"));
                    String nombreAnunciante = this.getNombreAnunciante(idUsuarioAnunciante);
                    int periodoTiempo = resul.getInt("vigencia_dias");
                    String titulo = resul.getString("titulo");
                    String fechaCompra = resul.getString("fecha_compra");
                    boolean isVigente = resul.getBoolean("isVigente");
                    ContenidoReporteCompraAnuncio contenidoComentario
                            = new ContenidoReporteCompraAnuncio(costo, tipoAnuncio, nombreAnunciante, periodoTiempo, titulo, fechaCompra, isVigente);
                    contenido.add(contenidoComentario);
                }
            } catch (SQLException e) {
                System.out.println("Error getReporteAcompraAnuncios() en ReporteAdminDB: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error getReporteAcompraAnuncios() en ReporteAdminDB: " + e);
        }
        return contenido;
    }

    private int getIdUsuarioAnunciante(int idAnunciante) {
        String query = "SELECT usuario FROM inversionista WHERE id_inversionista = ?";
        int idUsuarioSuscriptor = 0;
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idAnunciante);
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    idUsuarioSuscriptor = resul.getInt("usuario");
                }
            } catch (SQLException e) {
                System.out.println("Error getIdUsuarioAnunciante(idAnunciante) en ReporteAdminDB: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error getIdUsuarioAnunciante(idAnunciante) en ReporteAdminDB: " + e);
        }
        return idUsuarioSuscriptor;
    }

    private String getNombreAnunciante(int idUsuario) {
        String query = "SELECT nombre FROM usuario WHERE id_usuario = ?";
        String nombreSuscriptor = "";
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idUsuario);
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    nombreSuscriptor = resul.getString("nombre");
                }
            } catch (SQLException e) {
                System.out.println("Error getNombreAnunciante(idUsuario) en ReporteAdminDB: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error getNombreAnunciante(idUsuario) en ReporteAdminDB: " + e);
        }
        return nombreSuscriptor;
    }

}
