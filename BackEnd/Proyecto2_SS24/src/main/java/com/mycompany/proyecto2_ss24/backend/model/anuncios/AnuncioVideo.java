/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.model.anuncios;

import java.io.InputStream;

/**
 *
 * @author Carlos Cotom
 */
public class AnuncioVideo extends Anuncio {
    
    private InputStream video;
    private int idAnuncioVideo;

    public AnuncioVideo() {
    }

    public AnuncioVideo(double precio, int vigenciaDias, boolean isActivo, int idInversionista, int idPeriodoTiempo, int idTipoAnuncio) {
        super(precio, vigenciaDias, isActivo, idInversionista, idPeriodoTiempo, idTipoAnuncio);
    }

    public AnuncioVideo(InputStream video, double precio, int vigenciaDias, boolean isActivo, int idInversionista, int idPeriodoTiempo, int idTipoAnuncio) {
        super(precio, vigenciaDias, isActivo, idInversionista, idPeriodoTiempo, idTipoAnuncio);
        this.video = video;
    }

    public InputStream getVideo() {
        return video;
    }

    public void setVideo(InputStream video) {
        this.video = video;
    }    

    public int getIdAnuncioVideo() {
        return idAnuncioVideo;
    }

    public void setIdAnuncioVideo(int idAnuncioVideo) {
        this.idAnuncioVideo = idAnuncioVideo;
    }
    
}
