/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.data;

import com.mycompany.proyecto2_ss24.backend.model.CategoriaEnum;
import com.mycompany.proyecto2_ss24.backend.model.EtiquetaEnum;
import com.mycompany.proyecto2_ss24.backend.model.Revista;
import com.mycompany.proyecto2_ss24.backend.model.Suscripcion;
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
public class SuscripcionDB {

    private final ConexionDB data = ConexionDB.getInstance();
    private Connection connection = null;

    public SuscripcionDB() {
        try {
            this.connection = data.getConnection();
        } catch (SQLException ex) {
            System.out.println("Error en Conexxion a DB");
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

    public ArrayList<Revista> getRevistasNoSuscritas(int idUsuario) {
        int idSuscriptor = this.getIdSuscriptor(idUsuario);
        ArrayList<Integer> idRevistasSuscritas = this.getIdRevistasSuscritas(idSuscriptor);
        ArrayList<Integer> idRevistasNoSuscritas = this.getIdRevistasNoSuscritas(idRevistasSuscritas);
        ArrayList<Revista> revistas = new ArrayList<>();
        for (Integer idRevistaNoSuscrita : idRevistasNoSuscritas) {
            String query = "SELECT * FROM revista WHERE id_revista = ?";
            try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
                prepared.setInt(1, idRevistaNoSuscrita);
                try (ResultSet resul = prepared.executeQuery()) {
                    if (resul.next()) {
                        if (resul.getBoolean("estado_suscripcion")) {
                            Revista revista = this.crearPOJORevista(resul);
                            revistas.add(revista);
                        }
                    }
                } catch (SQLException e) {
                    System.out.println("Error en recibir la Revista NO Suscrita: " + e);
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir la Revista NO Suscrita: " + e);
            }
        }
        return revistas;
    }
    
    private ArrayList<Integer> getIdRevistasNoSuscritas(ArrayList<Integer> idRevistasSuscritas) {
        ArrayList<Integer> idsRevistas = new ArrayList<>();
        ArrayList<Integer> idsRevistasNoSuscritas = new ArrayList<>();
        String query = "SELECT id_revista FROM revista";
        try (Statement state = connection.createStatement(); ResultSet resul = state.executeQuery(query)) {
            while (resul.next()) {
                idsRevistas.add(resul.getInt("id_revista"));
            }
        } catch (SQLException e) {
            System.out.println("Error en recibir los ids de las Revista: " + e);
        }
        boolean revistaSuscrita = false;
        for (int i = 0; i < idsRevistas.size(); i++) {
            for (int j = 0; j < idRevistasSuscritas.size(); j++) {
                if (idsRevistas.get(i) == idRevistasSuscritas.get(j)) {
                    revistaSuscrita = true;
                    break;
                }
            }
            if (!revistaSuscrita) {
                idsRevistasNoSuscritas.add(idsRevistas.get(i));
            }
            revistaSuscrita = false;
        }
        return idsRevistasNoSuscritas;
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
    
    public void crearSuscripcion(Suscripcion suscripcion, int idUsuario) {
        String query = "INSERT INTO suscripcion (revista, suscriptor, fecha_suscripcion) VALUES (?, ?, ?)";
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            this.connection.setAutoCommit(false);
            int idSuscriptor = this.getIdSuscriptor(idUsuario);
            prepared.setInt(1, suscripcion.getIdRevista());
            prepared.setInt(2, idSuscriptor);
            prepared.setString(3, suscripcion.getFecha());
            prepared.executeUpdate();
            this.connection.commit();
            this.connection.setAutoCommit(true);
            System.out.println("Suscripcion Creada!!!");
        } catch (SQLException e) {
            try {
                this.connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Exception de RollBack: " + ex);
            }
            System.out.println("Error en crear una Suscripcion: " + e);
        }
    }
    
    public ArrayList<Revista> getRevistasSuscritas(int idSuscriptor) {
        ArrayList<Integer> idsRevistasSuscritas = this.getIdRevistasSuscritas(idSuscriptor);
        ArrayList<Revista> revistas = new ArrayList<>();
        for (Integer idRevistaSuscrita : idsRevistasSuscritas) {
            String query = "SELECT * FROM revista WHERE id_revista = ?";
            try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
                prepared.setInt(1, idRevistaSuscrita);
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

}
