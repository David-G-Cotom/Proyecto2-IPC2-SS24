/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Carlos Cotom
 */
public class MediaAnunciosDB {
    
    private final ConexionDB data = ConexionDB.getInstance();
    private Connection connection = null;

    public MediaAnunciosDB() {
        try {
            this.connection = data.getConnection();
        } catch (SQLException ex) {
            System.out.println("Error en Conexxion a DB");
        }
    }
    
    public byte[] getImageAnuncio(int idAdTextoImagen) {
        String query = "SELECT imagen FROM anuncio_texto_imagen WHERE id_ad_texto_imagen = ?";
        byte[] dataImage = null;
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idAdTextoImagen);
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    dataImage = resul.getBytes("imagen");
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir el Contenido de la Imagen en el Anuncio: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error en recibir el Contenido de la Imagen en el Anuncio: " + e);
        }
        return dataImage;
    }
    
    public byte[] getVideoAnuncio(int idAnuncioVideo) {
        String query = "SELECT video FROM anuncio_video WHERE id_ad_video = ?";
        byte[] dataImage = null;
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idAnuncioVideo);
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    dataImage = resul.getBytes("video");
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir el Contenido de la Imagen en el Anuncio: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error en recibir el Contenido de la Imagen en el Anuncio: " + e);
        }
        return dataImage;
    }
    
}
