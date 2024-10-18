/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.data;

import com.mycompany.proyecto2_ss24.backend.model.CategoriaEnum;
import com.mycompany.proyecto2_ss24.backend.model.Comentario;
import com.mycompany.proyecto2_ss24.backend.model.EtiquetaEnum;
import com.mycompany.proyecto2_ss24.backend.model.Revista;
import com.mycompany.proyecto2_ss24.backend.model.users.Editor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Carlos Cotom
 */
public class SuscriptorDB {
    
    private final ConexionDB data = ConexionDB.getInstance();
    private Connection connection = null;

    public SuscriptorDB() {
        try {
            this.connection = data.getConnection();
        } catch (SQLException ex) {
            System.out.println("Error en Conexxion a DB");
        }
    }
    
    public void crearComentario(Comentario comentario, int idUsuario) {
        String query = "INSERT INTO comentario (revista, contenido, suscriptor) VALUES (?, ?, ?)";
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            int idSuscriptor = this.getIdSuscriptor(idUsuario);
            prepared.setInt(1, comentario.getRevista());
            prepared.setString(2, comentario.getContenido());
            prepared.setInt(3, idSuscriptor);
            prepared.executeUpdate();
            System.out.println("Comentario Creado!!!");
        } catch (SQLException e) {
            System.out.println("Error en crear un Comentario: " + e);
        }
    }
    
    public int getIdSuscriptor(int idUsuario) {
        String query = "SELECT id_suscriptor FROM suscriptor WHERE usuario = ?";
        int idSuscriptor = 0;
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idUsuario);
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    idSuscriptor = resul.getInt("id_suscriptor");
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir el id del Suscriptor: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error en recibir el id del Suscriptor: " + e);
        }
        return idSuscriptor;
    }
    
    public boolean crearLike(int idUsuario, int idRevista) {
        String query = "INSERT INTO likes (revista, suscriptor) VALUES (?, ?)";
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            int idSuscriptor = this.getIdSuscriptor(idUsuario);
            prepared.setInt(1, idRevista);
            prepared.setInt(2, idSuscriptor);
            prepared.executeUpdate();
            System.out.println("Like Creado!!!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error en crear un Like: " + e);
            return false;
        }
    }
    
    public boolean revistaConLike(int idRevista, int idSuscriptor) {
        String query = "SELECT * FROM likes WHERE revista = ? AND suscriptor = ?";
        boolean tieneLike = false;
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idRevista);
            prepared.setInt(2, idSuscriptor);
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    tieneLike = true;
                }
            } catch (SQLException e) {
                System.out.println("Error en saber si la Revista Tiene LIke: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error en saber si la Revista Tiene LIke: " + e);
        }
        return tieneLike;
    }
    
    public ArrayList<Revista> getRevistasSuscritasFiltro(int idSuscriptor, ArrayList<String> etiquetas, ArrayList<String> categorias) {
        ArrayList<Integer> idsRevistasSuscritas = this.getIdRevistasSuscritas(idSuscriptor);
        ArrayList<Integer> idsRevistasFiltradasEtiqueta = new ArrayList<>();
        ArrayList<Integer> idsRevistasFiltradasCategoria = new ArrayList<>();
        ArrayList<Revista> revistasFiltradas = new ArrayList<>();
        if (idsRevistasSuscritas.isEmpty()) {
            return revistasFiltradas;
        }
        if (!etiquetas.isEmpty()) {
            ArrayList<Integer> idsTipoEtiqeutas = this.getIdsTipoEtiquetas(etiquetas);
            idsRevistasFiltradasEtiqueta = this.getIdsRevistasFiltradasEtiqueta(idsRevistasSuscritas, idsTipoEtiqeutas);            
        }
        if (!categorias.isEmpty()) {
            ArrayList<Integer> idsTipoCategorias = this.getIdsCategoria(categorias);
            idsRevistasFiltradasCategoria = this.getIdsRevistasFiltradasCategoria(idsRevistasSuscritas, idsTipoCategorias);            
        }
        ArrayList<Integer> idsRevistasFiltradas = this.getIdsRevistasFiltradas(idsRevistasFiltradasEtiqueta, idsRevistasFiltradasCategoria);        
        revistasFiltradas = this.getRevistasFiltradas(idsRevistasFiltradas);
        return revistasFiltradas;
    }
    
    private ArrayList<Revista> getRevistasFiltradas(ArrayList<Integer> idsRevistasFiltradas) {        
        ArrayList<Revista> revistas = new ArrayList<>();
        for (Integer idRevistaFiltrada : idsRevistasFiltradas) {
            String query = "SELECT * FROM revista WHERE id_revista = ?";
            try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
                prepared.setInt(1, idRevistaFiltrada);
                try (ResultSet resul = prepared.executeQuery()) {
                    if (resul.next()) {
                        Revista revista = this.crearPOJORevista(resul);
                        revistas.add(revista);
                    }
                } catch (SQLException e) {
                    System.out.println("Error en recibir la Revista Suscrita: " + e);
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir la Revista Suscrita: " + e);
            }
        }
        return revistas;
    }
    
    private ArrayList<Integer> getIdRevistasSuscritas(int idSuscriptor) {
        String query = "SELECT revista FROM suscripcion WHERE suscriptor = ?";
        ArrayList<Integer> idsRevistas = new ArrayList<>();
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idSuscriptor);
            try (ResultSet resul = prepared.executeQuery()) {
                while (resul.next()) {
                    idsRevistas.add(resul.getInt("revista"));
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir el id de la Revista Suscrita: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error en recibir el id de la Revista Suscrita: " + e);
        }
        return idsRevistas;
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
        revista.setPuedeComentarse(resul.getBoolean("estado_comentarios"));
        revista.setPuedeSuscribirse(resul.getBoolean("estado_suscripcion"));
        revista.setPuedeTenerLikes(resul.getBoolean("estado_likes"));
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
    
    private ArrayList<Integer> getIdsTipoEtiquetas(ArrayList<String> etiquetas) {
        ArrayList<Integer> idsTipoEtiquetas = new ArrayList<>();
        for (String etiqueta : etiquetas) {
            String query = "SELECT id_tipo FROM tipo_etiqueta WHERE tipo = ?";
            try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
                prepared.setString(1, etiqueta);
                try (ResultSet resul = prepared.executeQuery()) {
                    if (resul.next()) {
                        idsTipoEtiquetas.add(resul.getInt("id_tipo"));
                    }
                } catch (SQLException e) {
                    System.out.println("Error en recibir el ID del Tipo de Etiqueta: " + e);
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir el ID del Tipo de Etiqueta: " + e);
            }
        }
        return idsTipoEtiquetas;
    }
    
    private ArrayList<Integer> getIdsRevistasFiltradasEtiqueta(ArrayList<Integer> idsRevistasSuscritas, ArrayList<Integer> idsEtiquetas) {
        ArrayList<Integer> idsRevistasFiltradas = new ArrayList<>();
        for (int i = 0; i < idsRevistasSuscritas.size(); i++) {
            for (int j = 0; j < idsEtiquetas.size(); j++) {
                String query = "SELECT * FROM etiqueta WHERE revista = ? AND tipo_etiqueta = ?";
                try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
                    prepared.setInt(1, idsRevistasSuscritas.get(i));
                    prepared.setInt(2, idsEtiquetas.get(j));
                    try (ResultSet resul = prepared.executeQuery()) {
                        if (resul.next()) {
                            idsRevistasFiltradas.add(idsRevistasSuscritas.get(i));
                            break;
                        }
                    } catch (SQLException e) {
                        System.out.println("Error en recibir el ID de las Revista filtrada por Etiqueta: " + e);
                    }
                } catch (SQLException e) {
                    System.out.println("Error en recibir el ID de las Revista filtrada por Etiqueta: " + e);
                }
            }
        }
        return idsRevistasFiltradas;
    }
    
    private ArrayList<Integer> getIdsCategoria(ArrayList<String> categorias) {
        ArrayList<Integer> idsCategoria = new ArrayList<>();
        for (String categoria : categorias) {
            String query = "SELECT id_categoria FROM categoria WHERE tipo = ?";
            try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
                prepared.setString(1, categoria);
                try (ResultSet resul = prepared.executeQuery()) {
                    if (resul.next()) {
                        idsCategoria.add(resul.getInt("id_categoria"));
                    }
                } catch (SQLException e) {
                    System.out.println("Error en recibir el ID de la Categoria: " + e);
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir el ID de la Categoria: " + e);
            }
        }
        return idsCategoria;
    }
    
    private ArrayList<Integer> getIdsRevistasFiltradasCategoria(ArrayList<Integer> idsRevistasSuscritas, ArrayList<Integer> idsCategorias) {
        ArrayList<Integer> idsRevistasFiltradas = new ArrayList<>();
        for (int i = 0; i < idsRevistasSuscritas.size(); i++) {
            for (int j = 0; j < idsCategorias.size(); j++) {
                String query = "SELECT * FROM revista WHERE id_revista = ? AND categoria = ?";
                try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
                    prepared.setInt(1, idsRevistasSuscritas.get(i));
                    prepared.setInt(2, idsCategorias.get(j));
                    try (ResultSet resul = prepared.executeQuery()) {
                        if (resul.next()) {
                            idsRevistasFiltradas.add(idsRevistasSuscritas.get(i));
                            break;
                        }
                    } catch (SQLException e) {
                        System.out.println("Error en recibir el ID de las Revista filtrada por Categoria: " + e);
                    }
                } catch (SQLException e) {
                    System.out.println("Error en recibir el ID de las Revista filtrada por Categoria: " + e);
                }
            }
        }
        return idsRevistasFiltradas;
    }
    
    private ArrayList<Integer> getIdsRevistasFiltradas(ArrayList<Integer> idsFiltradosEtiqueta, ArrayList<Integer> idsFiltradosCategoria) {
        if (idsFiltradosEtiqueta.isEmpty()) {
            return idsFiltradosCategoria;
        }
        if (idsFiltradosCategoria.isEmpty()) {
            return idsFiltradosEtiqueta;
        }
        ArrayList<Integer> idsRevistasFiltradas = idsFiltradosEtiqueta;
        boolean estaRegistrada = false;
        for (int i = 0; i < idsFiltradosCategoria.size(); i++) {
            for (int j = 0; j < idsRevistasFiltradas.size(); j++) {
                if (idsRevistasFiltradas.get(j) == idsFiltradosCategoria.get(i)) {
                    estaRegistrada = true;
                    break;
                }
            }
            if (!estaRegistrada) {
                idsRevistasFiltradas.add(idsFiltradosCategoria.get(i));
                estaRegistrada = false;
            }
        }
        return idsRevistasFiltradas;
    }
    
    public ArrayList<Revista> getAllRevistasSuscriptor(int idSuscriptor) {
        ArrayList<Revista> revistasSuscritas = new ArrayList<>();
        ArrayList<Integer> idsRevistasSuscritas = this.getIdRevistasSuscritas(idSuscriptor);
        if (idsRevistasSuscritas.isEmpty()) {
            return revistasSuscritas;
        }
        for (Integer idRevistaSuscrita : idsRevistasSuscritas) {
            String query = "SELECT * FROM revista WHERE id_revista = ?";
            try (PreparedStatement prepared = this.connection.prepareStatement(query)){
                prepared.setInt(1, idRevistaSuscrita);
                try (ResultSet resul = prepared.executeQuery()){
                    if (resul.next()) {
                        Revista revista = this.crearPOJORevista(resul);
                        revistasSuscritas.add(revista);
                    }
                } catch (SQLException e) {
                    System.out.println("Error en recibir la Revista Suscrita: " + e);
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir la Revista Suscrita: " + e);
            }
        }
        return revistasSuscritas;
    }
    
}
