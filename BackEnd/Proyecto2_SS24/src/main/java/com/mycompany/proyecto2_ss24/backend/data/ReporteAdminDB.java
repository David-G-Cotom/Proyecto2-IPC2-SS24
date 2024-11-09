/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.data;

import com.mycompany.proyecto2_ss24.backend.model.reportes.ContenidoReporteCompraAnuncio;
import com.mycompany.proyecto2_ss24.backend.model.reportes.ContenidoReporteRevistaComentada;
import com.mycompany.proyecto2_ss24.backend.model.reportes.ContenidoReporteRevistaPopular;
import com.mycompany.proyecto2_ss24.backend.model.reportes.ReporteCompraAnuncio;
import com.mycompany.proyecto2_ss24.backend.model.reportes.ReporteRevistaComentada;
import com.mycompany.proyecto2_ss24.backend.model.reportes.ReporteRevistaPopular;
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
                case 0 ->
                    System.out.println("NO HAY CLAUSULA WHERE PARA EL REPORTE DE ANUNCIOS COMPRADOS");
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
                    String nombreAnunciante = this.getNombreUsuario(idUsuarioAnunciante);
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

    private String getNombreUsuario(int idUsuario) {
        String query = "SELECT nombre FROM usuario WHERE id_usuario = ?";
        String nombreUsuario = "";
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idUsuario);
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    nombreUsuario = resul.getString("nombre");
                }
            } catch (SQLException e) {
                System.out.println("Error getNombreUsuario(idUsuario) en ReporteAdminDB: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error getNombreUsuario(idUsuario) en ReporteAdminDB: " + e);
        }
        return nombreUsuario;
    }

    public ArrayList<ContenidoReporteRevistaPopular> getReporteRevistasPopulares(String clausulaWHERE, int cantidadInterrogantes, ReporteRevistaPopular datos) {
        //Consulta que genera la tabla con el ID de las Revistas junto con la cantidad de "veces"=Suscripciones que tiene
        String query = "SELECT revista, COUNT(revista) AS veces FROM suscripcion" + clausulaWHERE + " GROUP BY revista ORDER BY veces DESC LIMIT 5";
        System.out.println(query);
        System.out.println("? = " + cantidadInterrogantes);
        ArrayList<ContenidoReporteRevistaPopular> contenido = new ArrayList<>();
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            if (cantidadInterrogantes == 2) {
                prepared.setString(1, datos.getFechaInicio());
                prepared.setString(2, datos.getFechaFin());
            }
            try (ResultSet resul = prepared.executeQuery()) {
                while (resul.next()) {
                    int idRevista = resul.getInt("revista");
                    int cantidadSucripciones = resul.getInt("veces");
                    String[] datosRevista = this.getDatosRevista(idRevista);    //[idEditor, descripcion, nombre]
                    String nombreEditor = this.getNombreUsuario(this.getIdUsuarioEditor(Integer.parseInt(datosRevista[0])));
                    ArrayList<String> nombresSuscriptores = this.getNombresSuscriptores(idRevista, clausulaWHERE, datos);
                    ArrayList<String> fechasSuscripciones = this.getFechasSuscripciones(idRevista, clausulaWHERE, datos);
                    ContenidoReporteRevistaPopular contenidoFila
                            = new ContenidoReporteRevistaPopular(nombreEditor, datosRevista[1], datosRevista[2], cantidadSucripciones, nombresSuscriptores, fechasSuscripciones);
                    contenido.add(contenidoFila);
                }
            } catch (SQLException e) {
                System.out.println("Error getReporteRevistasTop() en ReporteEditorDB: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error getReporteRevistasTop() en ReporteEditorDB: " + e);
        }
        return contenido;
    }

    private String[] getDatosRevista(int idRevista) {
        String query = "SELECT editor, descripcion, nombre FROM revista WHERE id_revista = ?";
        String[] datosRevista = new String[3];
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idRevista);
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    datosRevista[0] = resul.getInt("editor") + "";
                    datosRevista[1] = resul.getString("descripcion");
                    datosRevista[2] = resul.getString("nombre");
                }
            } catch (SQLException e) {
                System.out.println("Error getDatosRevista(idRevista) en ReporteAdminDB: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error getDatosRevista(idRevista) en ReporteAdminDB: " + e);
        }
        return datosRevista;
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
                System.out.println("Error getIdUsuarioEditor(idAnunciante) en ReporteAdminDB: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error getIdUsuarioEditor(idAnunciante) en ReporteAdminDB: " + e);
        }
        return idUsuarioEditor;
    }

    private ArrayList<String> getNombresSuscriptores(int idRevista, String clausulaWHERE, ReporteRevistaPopular datos) {
        String query = "SELECT suscriptor FROM suscripcion";
        int cantidadInterrogantes;
        if (clausulaWHERE.equals("")) {
            query += " WHERE revista = ?";
            cantidadInterrogantes = 1;
        } else {
            query += (clausulaWHERE + " AND revista = ?");
            cantidadInterrogantes = 3;
        }
        ArrayList<String> contenido = new ArrayList<>();
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            switch (cantidadInterrogantes) {
                case 1 -> prepared.setInt(1, idRevista);
                case 3 -> {
                    prepared.setString(1, datos.getFechaInicio());
                    prepared.setString(2, datos.getFechaFin());
                    prepared.setInt(3, idRevista);
                }
            }
            try (ResultSet resul = prepared.executeQuery()) {
                while (resul.next()) {
                    int idSuscriptor = resul.getInt("suscriptor");
                    String nombreSuscriptor = this.getNombreUsuario(this.getIdUsuarioSuscriptor(idSuscriptor));
                    contenido.add("\"" + nombreSuscriptor + "\"");
                }
            } catch (SQLException e) {
                System.out.println("Error getNombresSuscriptores(idRevista) en ReporteEditorDB: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error getNombresSuscriptores(idRevista) en ReporteEditorDB: " + e);
        }
        return contenido;
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
                System.out.println("Error getIdUsuarioSuscriptor(idSuscriptor) en ReporteAdminDB: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error getIdUsuarioSuscriptor(idSuscriptor) en ReporteAdminDB: " + e);
        }
        return idUsuarioSuscriptor;
    }

    private ArrayList<String> getFechasSuscripciones(int idRevista, String clausulaWHERE, ReporteRevistaPopular datos) {
        String query = "SELECT fecha_suscripcion FROM suscripcion";
        int cantidadInterrogantes;
        if (clausulaWHERE.equals("")) {
            query += " WHERE revista = ?";
            cantidadInterrogantes = 1;
        } else {
            query += (clausulaWHERE + " AND revista = ?");
            cantidadInterrogantes = 3;
        }
        ArrayList<String> contenido = new ArrayList<>();
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            switch (cantidadInterrogantes) {
                case 1 -> prepared.setInt(1, idRevista);
                case 3 -> {
                    prepared.setString(1, datos.getFechaInicio());
                    prepared.setString(2, datos.getFechaFin());
                    prepared.setInt(3, idRevista);
                }
            }
            try (ResultSet resul = prepared.executeQuery()) {
                while (resul.next()) {
                    contenido.add("\"" + resul.getString("fecha_suscripcion") + "\"");
                }
            } catch (SQLException e) {
                System.out.println("Error getFechasSuscripciones(idRevista) en ReporteEditorDB: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error getFechasSuscripciones(idRevista) en ReporteEditorDB: " + e);
        }
        return contenido;
    }

    public ArrayList<ContenidoReporteRevistaComentada> getReporteRevistasComentadas(String clausulaWHERE, int cantidadInterrogantes, ReporteRevistaComentada datos) {
        //Consulta que genera la tabla con el ID de las Revistas junto con la cantidad de "veces"=Comentarios que tiene
        String query = "SELECT revista, COUNT(revista) AS veces FROM comentario" + clausulaWHERE + " GROUP BY revista ORDER BY veces DESC LIMIT 5";
        System.out.println(query);
        System.out.println("? = " + cantidadInterrogantes);
        ArrayList<ContenidoReporteRevistaComentada> contenido = new ArrayList<>();
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            if (cantidadInterrogantes == 2) {
                prepared.setString(1, datos.getFechaInicio());
                prepared.setString(2, datos.getFechaFin());
            }
            try (ResultSet resul = prepared.executeQuery()) {
                while (resul.next()) {
                    int idRevista = resul.getInt("revista");
                    int cantidadComentarios = resul.getInt("veces");
                    String[] datosRevista = this.getDatosRevista(idRevista);    //[idEditor, descripcion, nombre]
                    String nombreEditor = this.getNombreUsuario(this.getIdUsuarioEditor(Integer.parseInt(datosRevista[0])));
                    ArrayList<String> nombresSuscriptores = this.getNombresSuscriptoresComentaristas(idRevista, clausulaWHERE, datos);
                    ArrayList<String> comentarios = this.getComentarios(idRevista, clausulaWHERE, datos);
                    ArrayList<String> fechasComentarios = this.getFechasComentarios(idRevista, clausulaWHERE, datos);
                    ContenidoReporteRevistaComentada contenidoFila
                            = new ContenidoReporteRevistaComentada(nombreEditor, datosRevista[1], datosRevista[2], cantidadComentarios, nombresSuscriptores, comentarios, fechasComentarios);
                    contenido.add(contenidoFila);
                }
            } catch (SQLException e) {
                System.out.println("Error getReporteRevistasTop() en ReporteEditorDB: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error getReporteRevistasTop() en ReporteEditorDB: " + e);
        }
        return contenido;
    }
    
    private ArrayList<String> getComentarios(int idRevista, String clausulaWHERE, ReporteRevistaComentada datos) {
        String query = "SELECT contenido FROM comentario";
        int cantidadInterrogantes;
        if (clausulaWHERE.equals("")) {
            query += " WHERE revista = ?";
            cantidadInterrogantes = 1;
        } else {
            query += (clausulaWHERE + " AND revista = ?");
            cantidadInterrogantes = 3;
        }
        ArrayList<String> contenido = new ArrayList<>();
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            switch (cantidadInterrogantes) {
                case 1 -> prepared.setInt(1, idRevista);
                case 3 -> {
                    prepared.setString(1, datos.getFechaInicio());
                    prepared.setString(2, datos.getFechaFin());
                    prepared.setInt(3, idRevista);
                }
            }
            try (ResultSet resul = prepared.executeQuery()) {
                while (resul.next()) {
                    contenido.add("\"" + resul.getString("contenido") + "\"");
                }
            } catch (SQLException e) {
                System.out.println("Error getComentarios(idRevista) en ReporteEditorDB: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error getComentarios(idRevista) en ReporteEditorDB: " + e);
        }
        return contenido;
    }
    
    private ArrayList<String> getFechasComentarios(int idRevista , String clausulaWHERE, ReporteRevistaComentada datos) {
        String query = "SELECT fecha_comentario FROM comentario";
        int cantidadInterrogantes;
        if (clausulaWHERE.equals("")) {
            query += " WHERE revista = ?";
            cantidadInterrogantes = 1;
        } else {
            query += (clausulaWHERE + " AND revista = ?");
            cantidadInterrogantes = 3;
        }
        ArrayList<String> contenido = new ArrayList<>();
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            switch (cantidadInterrogantes) {
                case 1 -> prepared.setInt(1, idRevista);
                case 3 -> {
                    prepared.setString(1, datos.getFechaInicio());
                    prepared.setString(2, datos.getFechaFin());
                    prepared.setInt(3, idRevista);
                }
            }
            try (ResultSet resul = prepared.executeQuery()) {
                while (resul.next()) {
                    contenido.add("\"" + resul.getString("fecha_comentario") + "\"");
                }
            } catch (SQLException e) {
                System.out.println("Error getFechasComentarios(idRevista) en ReporteEditorDB: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error getFechasComentarios(idRevista) en ReporteEditorDB: " + e);
        }
        return contenido;
    }
    
    private ArrayList<String> getNombresSuscriptoresComentaristas(int idRevista, String clausulaWHERE, ReporteRevistaComentada datos) {
        String query = "SELECT suscriptor FROM comentario";
        int cantidadInterrogantes;
        if (clausulaWHERE.equals("")) {
            query += " WHERE revista = ?";
            cantidadInterrogantes = 1;
        } else {
            query += (clausulaWHERE + " AND revista = ?");
            cantidadInterrogantes = 3;
        }
        ArrayList<String> contenido = new ArrayList<>();
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            switch (cantidadInterrogantes) {
                case 1 -> prepared.setInt(1, idRevista);
                case 3 -> {
                    prepared.setString(1, datos.getFechaInicio());
                    prepared.setString(2, datos.getFechaFin());
                    prepared.setInt(3, idRevista);
                }
            }
            try (ResultSet resul = prepared.executeQuery()) {
                while (resul.next()) {
                    int idSuscriptor = resul.getInt("suscriptor");
                    String nombreSuscriptor = this.getNombreUsuario(this.getIdUsuarioSuscriptor(idSuscriptor));
                    contenido.add("\"" + nombreSuscriptor + "\"");
                }
            } catch (SQLException e) {
                System.out.println("Error getNombresSuscriptores(idRevista) en ReporteEditorDB: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error getNombresSuscriptores(idRevista) en ReporteEditorDB: " + e);
        }
        return contenido;
    }

}
