/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.controllers;

import com.mycompany.proyecto2_ss24.backend.data.CompraAnuncioDB;
import com.mycompany.proyecto2_ss24.backend.model.Recarga;
import com.mycompany.proyecto2_ss24.backend.model.anuncios.AnuncioTexto;
import com.mycompany.proyecto2_ss24.backend.model.anuncios.AnuncioTextoImagen;
import com.mycompany.proyecto2_ss24.backend.model.anuncios.AnuncioTextoTS;
import com.mycompany.proyecto2_ss24.backend.model.anuncios.AnuncioVideo;
import java.io.InputStream;

/**
 *
 * @author Carlos Cotom
 */
public class RegistroAnuncioController {
    
    private final CompraAnuncioDB dataCompra = new CompraAnuncioDB();
    private AnuncioTextoTS anuncio;
    private int idUsuario;

    public RegistroAnuncioController() {
    }

    public RegistroAnuncioController(AnuncioTextoTS anuncioTexto) {
        this.anuncio = anuncioTexto;
    }

    public AnuncioTextoTS getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(AnuncioTextoTS anuncio) {
        this.anuncio = anuncio;
    }
    
    public String verificarDatosAdText() {
        String mensajeDatosVacios = isDatosVaciosAd();
        if (!mensajeDatosVacios.equals("")) {
            return mensajeDatosVacios;
        }
        if (this.anuncio.getContenido().equals("")) {
            return "DEBE RELLENAR EL CAMPO PARA EL CONTENIDO DEL ANUNCIO";
        }
        String mensajeDatosValidos = isDatosValidosAd();
        if (!mensajeDatosValidos.equals("")) {
            return mensajeDatosValidos;
        }
        String mensajeCreditValidos = hayCredito();
        if (!mensajeCreditValidos.equals("")) {
            return mensajeCreditValidos;
        }
        return "";
    }
    
    public String verificarDatosAdImage(String imageName) {
        String mensajeDatosVacios = isDatosVaciosAd();
        if (!mensajeDatosVacios.equals("")) {
            return mensajeDatosVacios;
        }
        if (this.anuncio.getContenido().equals("")) {
            return "DEBE RELLENAR EL CAMPO PARA EL CONTENIDO DEL ANUNCIO";
        }
        if (imageName.equals("")) {
            return "DEBE SELECCIONAR UNA IMAGEN PARA EL ANUNCIO";
        }
        String mensajeDatosValidos = isDatosValidosAd();
        if (!mensajeDatosValidos.equals("")) {
            return mensajeDatosValidos;
        }
        if (!this.extensCorrectImage(imageName)) {
            return "DEBE ELEGIR UN ARCHIVO DE IMAGEN PARA LA FOTO (.png, .jpg, jpeg)";
        }
        String mensajeCreditValidos = hayCredito();
        if (!mensajeCreditValidos.equals("")) {
            return mensajeCreditValidos;
        }
        return "";
    }
    
    public String verificarDatosAdVideo(String videoName) {
        String mensajeDatosVacios = isDatosVaciosAd();
        if (!mensajeDatosVacios.equals("")) {
            return mensajeDatosVacios;
        }
        if (videoName.equals("")) {
            return "DEBE SELECCIONAR UN VIDEO PARA EL ANUNCIO";
        }
        String mensajeDatosValidos = isDatosValidosAd();
        if (!mensajeDatosValidos.equals("")) {
            return mensajeDatosValidos;
        }
        System.out.println("PROCESANDO EXTENSIONE");
        if (!this.extensCorrectVideo(videoName)) {
            return "DEBE ELEGIR UN ARCHIVO DE VIDEO EN FORMATO (.mp4, .mkv)";
        }
        String mensajeCreditValidos = hayCredito();
        if (!mensajeCreditValidos.equals("")) {
            return mensajeCreditValidos;
        }
        return "";
    }
    
    private String isDatosVaciosAd() {
        if (this.anuncio.getFechaCompra().equals("")) {
            return "DEBE SELECCIONAR UNA FECHA";
        }
        if (this.anuncio.getVigenciaDias() == 0) {
            return "DEBE SELECCIONAR UNA DURACION PARA EL ANUNCIO";
        }
        if (this.anuncio.getTitulo().equals("")) {
            return "DEBE RELLENAR EL CAMPO PARA EL TIRULO DEL ANUNCIO";
        }
        return "";
    }
    
    private String isDatosValidosAd() {
        if (!this.anuncio.getFechaCompra().contains("-")) {
            return "FORMATO DE FECHA INCORRECTO";
        }
        String[] datos = this.anuncio.getFechaCompra().split("-");
        if (datos.length != 3) {
            return "FORMATO DE FECHA INCORRECTO";
        }
        String yyyy = datos[0];
        String mm = datos[1];
        String dd = datos[2];
        if (!isIntegerPositive(yyyy) || !isIntegerPositive(mm) || !isIntegerPositive(dd)) {
            return "FORMATO DE FECHA INCORRECTO";
        }
        if (Integer.parseInt(mm) > 12 && Integer.parseInt(dd) > 31 && Integer.parseInt(yyyy) > 9999) {
            return "FORMATO DE FECHA INCORRECTO";
        }
        
        int[] duracionDiasValidos = {1, 3, 7, 14};
        boolean duracionValida = false;
        for (int duracionDiaValido : duracionDiasValidos) {
            if (this.anuncio.getVigenciaDias() ==  duracionDiaValido) {
                duracionValida = true;
                break;
            }
        }
        if (!duracionValida) {
            return "ERROR EN LA SELECCION DE DURACION DE DIAS PARA EL ANUNCIO";
        }
        
        return "";
    }
    
    private boolean extensCorrectImage(String fileName) {
        String[] extens = {".png", ".jpg", ".jpeg"};        
        for (String exten : extens) {
            if (fileName.toLowerCase().endsWith(exten)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean extensCorrectVideo(String fileName) {
        String[] extens = {".mp4", ".mkv"};        
        for (String exten : extens) {
            if (fileName.toLowerCase().endsWith(exten)) {
                return true;
            }
        }
        return false;
    }
    
    private String hayCredito() {
        this.idUsuario = this.anuncio.getIdInversionista();
        int idInversionista = dataCompra.getIdInversionista(this.idUsuario);
        String tipoAnuncio = "";
        switch (this.anuncio.getIdTipoAnuncio()) {
            case 1 -> tipoAnuncio = "ANUNCIO_TEXTO";
            case 2 -> tipoAnuncio = "ANUNCIO_TEXTO_IMAGEN";
            case 3 -> tipoAnuncio = "ANUNCIO_VIDEO";
        }
        double costoCompra = dataCompra.getCostoCompra(tipoAnuncio, this.anuncio.getVigenciaDias());
        double creditoInversionista = dataCompra.getCredito(idInversionista);
        if (costoCompra > creditoInversionista) {
            return "error";
        }
        this.anuncio.setPrecio(costoCompra);
        this.anuncio.setIdInversionista(idInversionista);
        return "";
    }
    
    private boolean isIntegerPositive(String texto) {
        try {
            int numero = Integer.parseInt(texto);
            return numero > 0;
        } catch (NumberFormatException e) {
            System.out.println("Texto ingresado NO puede ser Numero Entero");
            return false;
        }
    }
    
    public String crearAnuncioText() {
        AnuncioTexto anuncioTexto = convertirAdText();
        dataCompra.crearAnuncio(anuncioTexto, this.anuncio.getIdInversionista());
        dataCompra.crearAnuncioTexto(anuncioTexto);
        Recarga pago = new Recarga(anuncioTexto.getPrecio()+"", this.anuncio.getFechaCompra(), this.idUsuario, 3);
        dataCompra.crearApago(pago);
        dataCompra.actualizarCreditoInversionistaCompra(Double.parseDouble(pago.getCantidad()), this.anuncio.getIdInversionista());
        String mensaje = "nuevo";
        return "{\"mensaje\":\"" + mensaje + "\"}";
    }
    
    public String crearAnuncioImage(InputStream imagen) {
        AnuncioTextoImagen anuncioImagen = convertirAdImage(imagen);
        dataCompra.crearAnuncio(anuncioImagen, this.anuncio.getIdInversionista());
        dataCompra.crearAnuncioTextoImagen(anuncioImagen);
        Recarga pago = new Recarga(anuncioImagen.getPrecio()+"", this.anuncio.getFechaCompra(), this.idUsuario, 3);
        dataCompra.crearApago(pago);
        dataCompra.actualizarCreditoInversionistaCompra(Double.parseDouble(pago.getCantidad()), this.anuncio.getIdInversionista());
        String mensaje = "nuevo";
        return "{\"mensaje\":\"" + mensaje + "\"}";
    }
    
    public String crearAnuncioVideo(InputStream video) {
        AnuncioVideo anuncioVideo = convertirAdVideo(video);
        dataCompra.crearAnuncio(anuncioVideo, this.anuncio.getIdInversionista());
        dataCompra.crearAnuncioVideo(anuncioVideo);
        Recarga pago = new Recarga(anuncioVideo.getPrecio()+"", this.anuncio.getFechaCompra(), this.idUsuario, 3);
        dataCompra.crearApago(pago);
        dataCompra.actualizarCreditoInversionistaCompra(Double.parseDouble(pago.getCantidad()), this.anuncio.getIdInversionista());
        String mensaje = "nuevo";
        return "{\"mensaje\":\"" + mensaje + "\"}";
    }
    
    private AnuncioTexto convertirAdText() {
        int idPeriodoTiempo = dataCompra.getIdPeriodoTiempo(this.anuncio.getVigenciaDias());
        AnuncioTexto adTextJava = new AnuncioTexto(this.anuncio.getContenido(), this.anuncio.getPrecio(),
                this.anuncio.getVigenciaDias(), true, this.anuncio.getIdInversionista(), idPeriodoTiempo, 1);
        adTextJava.setTitulo(this.anuncio.getTitulo());
        return adTextJava;
    }
    
    private AnuncioTextoImagen convertirAdImage(InputStream imagen) {
        int idPeriodoTiempo = dataCompra.getIdPeriodoTiempo(this.anuncio.getVigenciaDias());
        AnuncioTextoImagen adImageJava = new AnuncioTextoImagen(this.anuncio.getContenido(), imagen, this.anuncio.getPrecio(),
                this.anuncio.getVigenciaDias(), true, this.anuncio.getIdInversionista(), idPeriodoTiempo, 2);
        adImageJava.setTitulo(this.anuncio.getTitulo());
        return adImageJava;
    }
    
    private AnuncioVideo convertirAdVideo(InputStream video) {
        int idPeriodoTiempo = dataCompra.getIdPeriodoTiempo(this.anuncio.getVigenciaDias());
        AnuncioVideo adVideoJava = new AnuncioVideo(video, this.anuncio.getPrecio(),
                this.anuncio.getVigenciaDias(), true, this.anuncio.getIdInversionista(), idPeriodoTiempo, 3);
        adVideoJava.setTitulo(this.anuncio.getTitulo());
        return adVideoJava;
    }
    
}
