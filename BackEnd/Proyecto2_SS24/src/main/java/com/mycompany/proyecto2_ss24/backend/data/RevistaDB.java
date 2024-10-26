/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.data;

import com.mycompany.proyecto2_ss24.backend.model.CategoriaEnum;
import com.mycompany.proyecto2_ss24.backend.model.EtiquetaEnum;
import com.mycompany.proyecto2_ss24.backend.model.Publicacion;
import com.mycompany.proyecto2_ss24.backend.model.Revista;
import com.mycompany.proyecto2_ss24.backend.model.RevistaTS;
import java.io.InputStream;
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
public class RevistaDB {

    private final ConexionDB data = ConexionDB.getInstance();
    private Connection connection = null;

    public RevistaDB() {
        try {
            this.connection = data.getConnection();
        } catch (SQLException ex) {
            System.out.println("Error en Conexxion a DB");
        }
    }

    public void crearRevista(Revista revista, int idUsuario) {
        String query = "INSERT INTO revista (editor, descripcion, likes, costo, fecha_creacion, nombre, estado_comentarios, estado_likes, estado_suscripcion, categoria, costo_global) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            int idAutor = this.getIdEditor(idUsuario);
            prepared.setInt(1, idAutor);
            prepared.setString(2, revista.getDescripcion());
            prepared.setInt(3, 0);
            prepared.setDouble(4, 0);
            prepared.setString(5, revista.getFechaCreacion());
            prepared.setString(6, revista.getNombreRevista());
            prepared.setBoolean(7, revista.isPuedeComentarse());
            prepared.setBoolean(8, revista.isPuedeTenerLikes());
            prepared.setBoolean(9, revista.isPuedeSuscribirse());
            int idCategoria = this.getIdCategoria(revista.getCategoria());
            prepared.setInt(10, idCategoria);
            prepared.setDouble(11, 0);
            prepared.executeUpdate();
            System.out.println("Revista Creada!!!");
        } catch (SQLException e) {
            System.out.println("Error en crear una Revista: " + e);
        }
    }

    private int getIdCategoria(CategoriaEnum categoria) {
        String query = "SELECT id_categoria FROM categoria WHERE tipo = ?";
        int id = 0;
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setString(1, categoria.toString());
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    id = resul.getInt("id_categoria");
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir el id de la categoria: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error en recibir el id de la categoria: " + e);
        }
        return id;
    }

    public int getIdEditor(int idUsuario) {
        String query = "SELECT id_editor FROM editor WHERE usuario = ?";
        int idAutor = 0;
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idUsuario);
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    idAutor = resul.getInt("id_editor");
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir el id del Autor: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error en recibir el id del Autor: " + e);
        }
        return idAutor;
    }

    public boolean editarRevista(RevistaTS revista) {
        String query = "UPDATE revista SET descripcion = ?, nombre = ?, estado_comentarios = ?, estado_likes = ?, estado_suscripcion = ?, categoria = ? WHERE id_revista = ?";
        int idCategoriaNueva = this.getIdCategoria(CategoriaEnum.valueOf(revista.getCategoria()));
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setString(1, revista.getDescripcion());
            prepared.setString(2, revista.getNombre());
            prepared.setBoolean(3, revista.isPuedeComentarse());
            prepared.setBoolean(4, revista.isPuedeTenerLikes());
            prepared.setBoolean(5, revista.isPuedeSuscribirse());
            prepared.setInt(6, idCategoriaNueva);
            prepared.setInt(7, revista.getIdRevista());
            prepared.executeUpdate();
            System.out.println("Estados de la Revista Actualizada!!!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al Actualizar los Estados de la Revista " + e);
            return false;
        }
    }

    public ArrayList<Revista> getRevistasEditor(int idEditor) {
        String query = "SELECT * FROM revista WHERE editor = ?";
        ArrayList<Revista> revistas = new ArrayList<>();
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idEditor);
            try (ResultSet resul = prepared.executeQuery()) {
                while (resul.next()) {
                    int idRevista = resul.getInt("id_revista");
                    String descripcion = resul.getString("descripcion");
                    int likes = resul.getInt("likes");
                    String nombre = resul.getString("nombre");
                    int idCategoria = resul.getInt("categoria");
                    String categoria = this.getCategoria(idCategoria);
                    ArrayList<EtiquetaEnum> etiquetas = this.getEtiquetas(idRevista);
                    Revista revista = new Revista(descripcion, CategoriaEnum.valueOf(categoria), etiquetas, likes, nombre, idRevista);
                    revista.setPuedeComentarse(resul.getBoolean("estado_comentarios"));
                    revista.setPuedeSuscribirse(resul.getBoolean("estado_suscripcion"));
                    revista.setPuedeTenerLikes(resul.getBoolean("estado_likes"));
                    revistas.add(revista);
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir las revistas de un autor " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error en recibir las revistas de un autor " + e);
        }
        return revistas;
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

    private ArrayList<Integer> getIdTiposEtiquetas(ArrayList<EtiquetaEnum> etiquetas) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (EtiquetaEnum etiqueta : etiquetas) {
            String query = "SELECT id_tipo FROM tipo_etiqueta WHERE tipo = ?";
            try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
                prepared.setString(1, etiqueta.toString());
                try (ResultSet resul = prepared.executeQuery()) {
                    if (resul.next()) {
                        ids.add(resul.getInt("id_tipo"));
                    }
                } catch (SQLException e) {
                    System.out.println("Error en recibir el id de la Etiqueta: " + e);
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir el id de la Etiqueta: " + e);
            }
        }
        return ids;
    }

    public void crearEtiquetas(int idRevista, ArrayList<EtiquetaEnum> etiquetas) {
        ArrayList<Integer> ids = this.getIdTiposEtiquetas(etiquetas);
        for (Integer id : ids) {
            String query = "INSERT INTO etiqueta (revista, tipo_etiqueta) VALUES (?, ?)";
            try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
                prepared.setInt(1, idRevista);
                prepared.setInt(2, id);
                prepared.executeUpdate();
                System.out.println("Etiqueta Creada!!!");
            } catch (SQLException e) {
                System.out.println("Error en crear una Etiqueta: " + e);
            }
        }
    }

    public int getIdRevistaCreada() {
        String query = "SELECT id_revista FROM revista ORDER BY id_revista DESC LIMIT 1";
        int idRevista = 0;
        try (Statement state = connection.createStatement(); ResultSet resultSet = state.executeQuery(query)) {
            if (resultSet.next()) {
                idRevista = resultSet.getInt("id_revista");
            }
        } catch (SQLException e) {
            System.out.println("Error en recibir el id de la Ultima Revista Creada: " + e);
        }
        return idRevista;
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

    public byte[] getPdfPublicacion(int idPublicaicon) {
        String query = "SELECT archivo_pdf FROM publicacion WHERE id_publicacion= ?";
        byte[] dataPdf = null;
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idPublicaicon);
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    dataPdf = resul.getBytes("archivo_pdf");
                    System.out.println("CARGO LOS BYTES");
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir el Contenido de la Publicacion PDF: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error en recibir el Contenido de la Publicacion PDF: " + e);
        }
        return dataPdf;
    }
    
    public void actualizarLikes(int idRevista) {
        String query = "UPDATE revista SET likes = ? WHERE id_revista = ?";
        int likes = this.getLikes(idRevista);
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, likes);
            prepared.setInt(2, idRevista);
            prepared.executeUpdate();
            System.out.println("Likes Actualizados de la Revista");
        } catch (SQLException e) {
            System.out.println("Error al Actualizar los Likes de la Revista: " + e);
        }
    }
    
    private int getLikes(int idRevista) {
        String query = "SELECT * FROM likes WHERE revista = ?";
        int cantidadLikes = 0;
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idRevista);
            try (ResultSet resul = prepared.executeQuery()) {
                while (resul.next()) {
                    cantidadLikes++;
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir la CAntidad de Likes de una Revista: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error en recibir la CAntidad de Likes de una Revista: " + e);
        }
        return cantidadLikes;
    }
    
    public boolean crearPublicacion(String numeroPublicacion, InputStream archivo, int idRevista) {
        String query = "INSERT INTO publicacion (archivo_pdf, id_revista, numero_publicacion) VALUES (?, ?, ?)";
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setBlob(1, archivo);
            prepared.setInt(2, idRevista);
            prepared.setString(3, numeroPublicacion);
            prepared.executeUpdate();
            System.out.println("Publicacion Creada!!!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error en crear una Publicacion: " + e);
            return false;
        }
    }

}
