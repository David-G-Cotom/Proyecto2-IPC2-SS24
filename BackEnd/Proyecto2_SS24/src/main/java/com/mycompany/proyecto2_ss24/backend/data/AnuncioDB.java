/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.data;

import com.mycompany.proyecto2_ss24.backend.model.anuncios.Anuncio;
import com.mycompany.proyecto2_ss24.backend.model.anuncios.AnuncioTexto;
import com.mycompany.proyecto2_ss24.backend.model.anuncios.AnuncioTextoImagen;
import com.mycompany.proyecto2_ss24.backend.model.anuncios.AnuncioVideo;
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
public class AnuncioDB {

    private final ConexionDB data = ConexionDB.getInstance();
    private Connection connection = null;

    public AnuncioDB() {
        try {
            this.connection = data.getConnection();
        } catch (SQLException ex) {
            System.out.println("Error en Conexxion a DB");
        }
    }

    public boolean editarAnuncio(int idAnuncio, boolean estado, String titulo) {
        String query = "UPDATE anuncio SET estado = ?, titulo = ? WHERE id_anuncio = ?";
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setBoolean(1, estado);
            prepared.setString(2, titulo);
            prepared.setInt(3, idAnuncio);
            prepared.execute();
            System.out.println("Estados del Anuncio Actualizado!!!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al Actualizar el Estado del Anuncio: " + e);
            return false;
        }
    }

    public boolean editarAnuncio(int idAnuncio, boolean estado) {
        String query = "UPDATE anuncio SET estado = ? WHERE id_anuncio = ?";
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setBoolean(1, estado);
            prepared.setInt(2, idAnuncio);
            prepared.execute();
            System.out.println("Estados del Anuncio Actualizado!!!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al Actualizar el Estado del Anuncio: " + e);
            return false;
        }
    }

    public int getIdInversionista(int idUsuario) {
        String query = "SELECT id_inversionista FROM inversionista WHERE usuario = ?";
        int idInversionista = 0;
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idUsuario);
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    idInversionista = resul.getInt("id_inversionista");
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir el ID del Inversionista: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error en recibir el ID del Inversionista: " + e);
        }
        return idInversionista;
    }

    public ArrayList<Anuncio> getAnunciosAnunciante(int idInversionista) {
        String query = "SELECT * FROM anuncio WHERE inversionista = ?";
        ArrayList<Anuncio> anuncios = new ArrayList<>();
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idInversionista);
            try (ResultSet resul = prepared.executeQuery()) {
                while (resul.next()) {
                    int idAnuncio = resul.getInt("id_anuncio");
                    double costo = resul.getDouble("costo");
                    int idTipoAnuncio = resul.getInt("tipo_anuncio");
                    int vigencia = resul.getInt("vigencia_dias");
                    boolean estado = resul.getBoolean("estado");
                    int idPeriodo = resul.getInt("id_periodo");
                    Anuncio anuncio = new Anuncio(costo, vigencia, estado, idInversionista, idPeriodo, idTipoAnuncio);
                    anuncio.setIdAnuncio(idAnuncio);
                    anuncio.setTitulo(resul.getString("titulo"));
                    anuncio.setFechaCompra(resul.getString("fecha_compra"));
                    boolean isVigente = resul.getBoolean("isVigente");
                    anuncio.setIsVigente(isVigente);
                    this.agregarAnuncioEspecifico(anuncios, anuncio);
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir los Anuncios de un Inversionista: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error en recibir los Anuncios de un Inversionista: " + e);
        }
        return anuncios;
    }

    public ArrayList<Anuncio> getAllAnuncios() {
        String query = "SELECT * FROM anuncio";
        ArrayList<Anuncio> anuncios = new ArrayList<>();
        try (Statement state = this.connection.createStatement(); ResultSet resul = state.executeQuery(query)) {
            while (resul.next()) {
                int idAnuncio = resul.getInt("id_anuncio");
                double costo = resul.getDouble("costo");
                int idTipoAnuncio = resul.getInt("tipo_anuncio");
                int idInversionista = resul.getInt("inversionista");
                int vigencia = resul.getInt("vigencia_dias");
                boolean isActivo = resul.getBoolean("estado");
                int idPeriodo = resul.getInt("id_periodo");
                Anuncio anuncio = new Anuncio(costo, vigencia, isActivo, idInversionista, idPeriodo, idTipoAnuncio);
                anuncio.setIdAnuncio(idAnuncio);
                anuncio.setTitulo(resul.getString("titulo"));
                anuncio.setFechaCompra(resul.getString("fecha_compra"));
                boolean isVigente = resul.getBoolean("isVigente");
                anuncio.setIsVigente(isVigente);
                this.agregarAnuncioEspecifico(anuncios, anuncio);
            }
        } catch (SQLException e) {
            System.out.println("Error al Consultar getAllAnuncios() en AnuncioDB: " + e);
        }
        return anuncios;
    }

    public ArrayList<Anuncio> getAllAnunciosActivos() {
        String query = "SELECT * FROM anuncio";
        ArrayList<Anuncio> anuncios = new ArrayList<>();
        try (Statement state = this.connection.createStatement(); ResultSet resul = state.executeQuery(query)) {
            while (resul.next()) {
                int idAnuncio = resul.getInt("id_anuncio");
                double costo = resul.getDouble("costo");
                int idTipoAnuncio = resul.getInt("tipo_anuncio");
                int idInversionista = resul.getInt("inversionista");
                int vigencia = resul.getInt("vigencia_dias");
                boolean isActivo = resul.getBoolean("estado");
                int idPeriodo = resul.getInt("id_periodo");
                Anuncio anuncio = new Anuncio(costo, vigencia, isActivo, idInversionista, idPeriodo, idTipoAnuncio);
                anuncio.setIdAnuncio(idAnuncio);
                anuncio.setTitulo(resul.getString("titulo"));
                anuncio.setFechaCompra(resul.getString("fecha_compra"));
                boolean isVigente = resul.getBoolean("isVigente");
                anuncio.setIsVigente(isVigente);
                if (isActivo && isVigente) {
                    this.agregarAnuncioEspecifico(anuncios, anuncio);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al Consultar Todos los Anuncios Registrados: " + e);
        }
        return anuncios;
    }

    private void agregarAnuncioEspecifico(ArrayList<Anuncio> anuncios, Anuncio anuncio) {
        switch (anuncio.getIdTipoAnuncio()) {
            case 1 -> //ANUNCIO DE TEXTO
                anuncios.add(this.getAnuncioTexto(anuncio));
            case 2 -> //ANUNCIO DE TEXTO E IMAGEN
                anuncios.add(this.getAnuncioTextoImagen(anuncio));
            case 3 -> //ANUNCIO DE VIDEO
                anuncios.add(this.getAnuncioVideo(anuncio));
        }
    }

    private AnuncioTexto getAnuncioTexto(Anuncio anuncio) {
        String query = "SELECT * FROM anuncio_texto WHERE id_anuncio = ?";
        AnuncioTexto anuncioTexto = new AnuncioTexto(anuncio.getPrecio(), anuncio.getVigenciaDias(), anuncio.isIsActivo(), anuncio.getIdInversionista(), anuncio.getIdPeriodoTiempo(), anuncio.getIdTipoAnuncio());
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, anuncio.getIdAnuncio());
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    anuncioTexto.setContenido(resul.getString("contenido"));
                    anuncioTexto.setIdAnuncioTexto(resul.getInt("id_ad_texto"));
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir los Anuncios de un Inversionista: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error en recibir los Anuncios de un Inversionista: " + e);
        }
        anuncioTexto.setIdAnuncio(anuncio.getIdAnuncio());
        anuncioTexto.setTitulo(anuncio.getTitulo());
        anuncioTexto.setFechaCompra(anuncio.getFechaCompra());
        anuncioTexto.setIsVigente(anuncio.isIsVigente());
        return anuncioTexto;
    }

    private AnuncioTextoImagen getAnuncioTextoImagen(Anuncio anuncio) {
        String query = "SELECT * FROM anuncio_texto_imagen WHERE id_anuncio = ?";
        AnuncioTextoImagen anuncioTextoImagen = new AnuncioTextoImagen(anuncio.getPrecio(), anuncio.getVigenciaDias(), anuncio.isIsActivo(), anuncio.getIdInversionista(), anuncio.getIdPeriodoTiempo(), anuncio.getIdTipoAnuncio());
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, anuncio.getIdAnuncio());
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    anuncioTextoImagen.setContenido(resul.getString("contenido"));
                    anuncioTextoImagen.setImagen(resul.getBinaryStream("imagen"));
                    anuncioTextoImagen.setIdAdTextoImagen(resul.getInt("id_ad_texto_imagen"));
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir los Anuncios de un Inversionista: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error en recibir los Anuncios de un Inversionista: " + e);
        }
        anuncioTextoImagen.setIdAnuncio(anuncio.getIdAnuncio());
        anuncioTextoImagen.setTitulo(anuncio.getTitulo());
        anuncioTextoImagen.setFechaCompra(anuncio.getFechaCompra());
        anuncioTextoImagen.setIsVigente(anuncio.isIsVigente());
        return anuncioTextoImagen;
    }

    private AnuncioVideo getAnuncioVideo(Anuncio anuncio) {
        String query = "SELECT * FROM anuncio_video WHERE id_anuncio = ?";
        AnuncioVideo anuncioVideo = new AnuncioVideo(anuncio.getPrecio(), anuncio.getVigenciaDias(), anuncio.isIsActivo(), anuncio.getIdInversionista(), anuncio.getIdPeriodoTiempo(), anuncio.getIdTipoAnuncio());
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, anuncio.getIdAnuncio());
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    anuncioVideo.setVideo(resul.getBinaryStream("video"));
                    anuncioVideo.setIdAnuncioVideo(resul.getInt("id_ad_video"));
                }
            } catch (SQLException e) {
                System.out.println("Error en recibir los Anuncios de un Inversionista: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error en recibir los Anuncios de un Inversionista: " + e);
        }
        anuncioVideo.setIdAnuncio(anuncio.getIdAnuncio());
        anuncioVideo.setTitulo(anuncio.getTitulo());
        anuncioVideo.setFechaCompra(anuncio.getFechaCompra());
        anuncioVideo.setIsVigente(anuncio.isIsVigente());
        return anuncioVideo;
    }

    public double[] getPreciosTipoAnuncios() {
        String query = "SELECT precio FROM tipo_anuncio";
        double[] precios = new double[3];   //PORQUE SON 3 TIPOS DE ANUNCIOS
        int i = 0;
        try (Statement state = this.connection.createStatement(); ResultSet resul = state.executeQuery(query)) {
            while (resul.next()) {
                precios[i] = resul.getDouble("precio");
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Error al Consulta los Precios de los Diferentes Tipos de Anuncios: " + e);
        }
        return precios;
    }

    public double[] getPreciosPeriodoTiempo() {
        String query = "SELECT precio FROM periodo_tiempos";
        double[] precios = new double[4];   //PORQUE SON 4 PERIODOS DE TIEMPO
        int i = 0;
        try (Statement state = this.connection.createStatement(); ResultSet resul = state.executeQuery(query)) {
            while (resul.next()) {
                precios[i] = resul.getDouble("precio");
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Error al Consulta los Precios de los Diferentes Periodos de Tiempo: " + e);
        }
        return precios;
    }

    /**
     * Metodo que se utiliza para cuando el anuncio ha vencido, por lo tanto se
     * actualiza el estado de la vigencia y el estado de activo en la DB
     *
     * @param isVigente
     * @param idAnuncio
     * @return
     */
    public boolean actualizarEstadoVigencia(boolean isVigente, int idAnuncio) {
        String query = "UPDATE anuncio SET isVigente = ? WHERE id_anuncio = ?";
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setBoolean(1, isVigente);
            prepared.setInt(2, idAnuncio);
            prepared.execute();
            System.out.println("Estados isVIgente del Anuncio Actualizado!!!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error actualizarEstadoVigencia(isVigente, idAnuncio) en AnuncioDB: " + e);
            return false;
        }
    }

    public boolean deleteAnuncio(int idAnuncio) {
        int tipoAnuncio = this.getTipoAnuncio(idAnuncio);
        if (!this.deleteAnuncioEspecifico(tipoAnuncio, idAnuncio)) {
            return false;
        }
        String query = "DELETE FROM anuncio WHERE id_anuncio = ?";
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idAnuncio);
            prepared.executeUpdate();
            System.out.println("Anuncio General: " + idAnuncio + " del Tipo: " + tipoAnuncio + " ELIMINDAO!!!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error deleteAnuncio(idAnuncio) en AnuncioDB: " + e);
            return false;
        }
    }

    private int getTipoAnuncio(int idAnuncio) {
        String query = "SELECT tipo_anuncio FROM anuncio WHERE id_anuncio = ?";
        int idTipoAnuncio = 0;
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idAnuncio);
            try (ResultSet resul = prepared.executeQuery()) {
                if (resul.next()) {
                    idTipoAnuncio = resul.getInt("tipo_anuncio");
                }
            } catch (SQLException e) {
                System.out.println("Error getTipoAnuncio(idAnuncio) en AnuncioDB: " + e);
            }
        } catch (SQLException e) {
            System.out.println("Error getTipoAnuncio(idAnuncio) en AnuncioDB: " + e);
        }
        return idTipoAnuncio;
    }

    private boolean deleteAnuncioEspecifico(int tipoAnuncio, int idAnuncio) {
        String tablaEspecifica = "";
        switch (tipoAnuncio) {
            case 1 ->
                tablaEspecifica = "anuncio_texto";
            case 2 ->
                tablaEspecifica = "anuncio_texto_imagen";
            case 3 ->
                tablaEspecifica = "anuncio_video";
        }
        String query = "DELETE FROM " + tablaEspecifica + " WHERE id_anuncio = ?";
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setInt(1, idAnuncio);
            prepared.executeUpdate();
            System.out.println("Anuncio en Especifico: " + idAnuncio + " del Tipo: " + tipoAnuncio + " ELIMINDAO!!!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error deleteAnuncioEspecifico(tipoAnuncio, idAnuncio) en AnuncioDB: " + e);
            return false;
        }
    }
    /*
        try (PreparedStatement prepared = this.connection.prepareStatement(query)) {
            prepared.setBoolean(1, isVigente);
            prepared.setInt(2, idAnuncio);
            prepared.execute();
            System.out.println("Estados isVIgente del Anuncio Actualizado!!!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error actualizarEstadoVigencia(isVigente, idAnuncio) en AnuncioDB: " + e);
            return false;
        }
     */

}
