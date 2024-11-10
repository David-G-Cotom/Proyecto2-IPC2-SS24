/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.data;

import com.mycompany.proyecto2_ss24.backend.model.CategoriaEnum;
import com.mycompany.proyecto2_ss24.backend.model.EtiquetaEnum;
import com.mycompany.proyecto2_ss24.backend.model.Revista;
import com.mycompany.proyecto2_ss24.backend.model.users.Editor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Carlos Cotom
 */
public class PreciosRevistaDB {
    
    private final ConexionDB data = ConexionDB.getInstance();
    private Connection connection = null;

    public PreciosRevistaDB() {
        try {
            this.connection = data.getConnection();
        } catch (SQLException ex) {
            System.out.println("Error en Conexxion a DB");
        }
    }
    
    public ArrayList<Revista> getAllRevistas() {
        String query = "SELECT * FROM revista";
        ArrayList<Revista> revistas = new ArrayList<>();
        try (Statement state = this.connection.createStatement();
                ResultSet resul = state.executeQuery(query)){
            while (resul.next()) {
                Revista revista = this.crearPOJORevista(resul);
                revista.setCosto(resul.getDouble("costo"));
                revista.setCostoGlobal(resul.getDouble("costo_global"));
                revista.setCostoOcultacion(resul.getDouble("costo_ocultacion"));
                revistas.add(revista);
            }
        } catch (SQLException e) {
            System.out.println("Error al Consultar todas las Revistas Registradas en Sistema: " + e);
        }
        return revistas;
    }
    
    private Revista crearPOJORevista(ResultSet resul) throws SQLException {
        int idRevista = resul.getInt("id_revista");
        String descripcion = resul.getString("descripcion");
        int likes = resul.getInt("likes");
        String nombre = resul.getString("nombre");
        int idCategoria = resul.getInt("categoria");
        String categoria = this.getCategoria(idCategoria);
        ArrayList<EtiquetaEnum> etiquetas = this.getEtiquetas(idRevista);
        Revista revista = new Revista(descripcion, CategoriaEnum.valueOf(categoria), etiquetas, likes, nombre, idRevista);
        int idEditor = resul.getInt("editor");
        int idUsuarioEditor = this.getIdUsuarioEditor(idEditor);
        String nombreEditor = this.getNombreEditor(idUsuarioEditor);
        Editor editor = new Editor();
        editor.setNombre(nombreEditor);
        editor.setIdEditor(idEditor);
        revista.setAutor(editor);
        return revista;
    }
    
    private String getCategoria(int idCategoria) {
        String query = "SELECT tipo FROM categoria WHERE id_categoria = ?";
        String categoria = "";
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idCategoria);
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    categoria = resul.getString("tipo");
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir el tipo de Categoria: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error en recibir el tipo de Categoria: " + e);
        }
        return categoria;
    }
    
    private ArrayList<EtiquetaEnum> getEtiquetas(int idRevista) {
        ArrayList<Integer> ids = this.getTipoEtiquetas(idRevista);
        ArrayList<EtiquetaEnum> etiquetas = new ArrayList<>();
        for (Integer id : ids) {
            String query = "SELECT tipo FROM tipo_etiqueta WHERE id_tipo = ?";
            try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
                prepared.setInt(1, id);
                try (ResultSet resul = prepared.executeQuery()) {
                    if (resul.next()) {
                        etiquetas.add(EtiquetaEnum.valueOf(resul.getString("tipo")));
                    }
                } catch (SQLException e) {
                    System.out.println("Error en recibir el Nombre del Tipo de Etiqueta: " + e);
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir el Nombre del Tipo de Etiqueta: " + e);
            }
        }
        return etiquetas;
    }
    
    private ArrayList<Integer> getTipoEtiquetas(int idRevista) {
        String query = "SELECT tipo_etiqueta FROM etiqueta WHERE revista = ?";
        ArrayList<Integer> idsTipoEtiqueta = new ArrayList<>();
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idRevista);
            try (ResultSet resul = prepared.executeQuery()) {
                while (resul.next()) {
                    idsTipoEtiqueta.add(resul.getInt("tipo_etiqueta"));
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir el id del Tipo de Etiqueta: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error en recibir el id del Tipo de Etiqueta: " + e);
        }
        return idsTipoEtiqueta;
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
    
    private String getNombreEditor(int idUsuarioEditor) {
        String query = "SELECT nombre FROM usuario WHERE id_usuario = ?";
        String nombreEditor = "";
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idUsuarioEditor);
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    nombreEditor = resul.getString("nombre");
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir el nombre del Usuario Autor: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error en recibir el nombre del Usuario Autor: " + e);
        }
        return nombreEditor;
    }
    
    public void actualizarPrecioRevista(double precioDia, double precioGlobal, double precioOcultacion, int idRevista) {
        String query = "UPDATE revista SET costo = ?, costo_global = ?, costo_ocultacion = ? WHERE id_revista = ?";
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            this.connection.setAutoCommit(false);
            prepared.setDouble(1, precioDia);
            prepared.setDouble(2, precioGlobal);
            prepared.setDouble(3, precioOcultacion);
            prepared.setInt(4, idRevista);
            prepared.executeUpdate();
            this.connection.commit();
            this.connection.setAutoCommit(true);
            System.out.println("Precio de la Revista Actualizado!!!");
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Exception de RollBack: " + ex);
            }
            System.out.println("Error al Actualizar el Precio de la Revista: " + e);
        }
    }
    
}
