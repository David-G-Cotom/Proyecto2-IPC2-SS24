/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.data;

import com.mycompany.proyecto2_ss24.backend.model.Recarga;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    
    public boolean crearApago(Recarga pago) {
        String query = "INSERT INTO pago (inversionista, monto, fecha_pago) VALUES (?, ?, ?)";
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {            
            prepared.setDouble(2, pago.getCantidadPaog());
            prepared.setString(3, pago.getFechaPago().toString());
            prepared.executeUpdate();
            System.out.println("Pago Creado!!!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error en crear un Pago: " + e);
            return false;
        }
    }
    
}
