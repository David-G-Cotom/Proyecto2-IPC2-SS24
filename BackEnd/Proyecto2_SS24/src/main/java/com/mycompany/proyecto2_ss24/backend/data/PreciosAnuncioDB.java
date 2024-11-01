/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Carlos Cotom
 */
public class PreciosAnuncioDB {

    private final ConexionDB data = ConexionDB.getInstance();
    private Connection connection = null;

    public PreciosAnuncioDB() {
        try {
            this.connection = data.getConnection();
        } catch (SQLException ex) {
            System.out.println("Error en Conexxion a DB");
        }
    }

    public void actualizarPrecioTipoAnuncios(double[] precios) {
        for (int i = 0; i < precios.length; i++) {
            String query = "UPDATE tipo_anuncio SET precio = ? WHERE id_tipo = ?";
            try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
                prepared.setDouble(1, precios[i]);
                prepared.setInt(2, (i + 1));
                prepared.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al Actualizar los Precios de los Tipos de Anuncios: " + e);
            }
        }
        System.out.println("Precios de los Tipos de Anuncios Actualizados!!!");
    }

    public void actualizarPrecioPeriodoTiempo(double[] precios) {
        for (int i = 0; i < precios.length; i++) {
            String query = "UPDATE periodo_tiempos SET precio = ? WHERE id_periodo = ?";
            try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
                prepared.setDouble(1, precios[i]);
                prepared.setInt(2, (i + 1));
                prepared.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al Actualizar los Precios de los Periodos de Tiempo para los Anuncios: " + e);
            }
        }
        System.out.println("Precios de los Periodos de Tiempo para los Anuncios Actualizados!!!");
    }

}
