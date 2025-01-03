/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.data;

import com.mycompany.proyecto2_ss24.backend.model.Recarga;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Carlos Cotom
 */
public class CarteraDB {
    
    private final ConexionDB data = ConexionDB.getInstance();
    private Connection connection = null;

    public CarteraDB() {
        try {
            this.connection = data.getConnection();
        } catch (SQLException ex) {
            System.out.println("Error en Conexxion a DB");
        }
    }
    
    public void crearApago(Recarga pago) {
        String query = "INSERT INTO pago (inversionista, monto, fecha_pago) VALUES (?, ?, ?)";
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {            
            this.connection.setAutoCommit(false);
            prepared.setInt(1, pago.getIdUsuario());
            prepared.setDouble(2, Double.parseDouble(pago.getCantidad()));
            prepared.setString(3, pago.getFechaRecarga());
            prepared.executeUpdate();
            this.connection.commit();
            this.connection.setAutoCommit(true);
            System.out.println("Pago Creado!!!");
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Exception de RollBack: " + ex);
            }
            System.out.println("Error en crear un Pago: " + e);
        }
    }
    
    public void actualizarCreditoAbono(double abono, int idUsuario, String tablaConsulta) {
        String query = "UPDATE " + tablaConsulta + " SET credito = ? WHERE usuario = ?";
        double creditoActual = this.getCredito(idUsuario, tablaConsulta);
        creditoActual += abono;
        try (PreparedStatement prepared = connection.prepareStatement(query)) {
            this.connection.setAutoCommit(false);
            prepared.setDouble(1, creditoActual);
            prepared.setInt(2, idUsuario);
            prepared.executeUpdate();
            this.connection.commit();
            this.connection.setAutoCommit(true);
            System.out.println("Credito del " + tablaConsulta + " actualizado");
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Exception de RollBack: " + ex);
            }
            System.out.println("Error al actualizar el Credito del Inversionista: " + e);
        }
    }
    
    public double getCredito(int idInversionista, String tablaConsulta) {
        String query = "SELECT credito FROM " + tablaConsulta + " WHERE usuario = ?";
        double credito = 0;
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idInversionista);
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    credito = resul.getDouble("credito");
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir el Credito del Inversionista: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error en recibir el Credito del Inversionista: " + e);
        }
        return credito;
    }
    
}
