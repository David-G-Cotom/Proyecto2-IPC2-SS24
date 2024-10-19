/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.controllers;

import com.mycompany.proyecto2_ss24.backend.data.CompraAnuncioDB;
import com.mycompany.proyecto2_ss24.backend.model.Recarga;
import com.mycompany.proyecto2_ss24.backend.model.anuncios.AnuncioTexto;
import com.mycompany.proyecto2_ss24.backend.model.anuncios.AnuncioTextoTS;

/**
 *
 * @author Carlos Cotom
 */
public class RegistroAnuncioController {
    
    private CompraAnuncioDB dataCompra = new CompraAnuncioDB();
    private final AnuncioTextoTS anuncioTexto;
    private int idUsuario;

    public RegistroAnuncioController(AnuncioTextoTS anuncioTexto) {
        this.anuncioTexto = anuncioTexto;
    }
    
    public String verificarDatosAdText() {
        String mensajeDatosVacios = isDatosVaciosAdText();
        if (!mensajeDatosVacios.equals("")) {
            return mensajeDatosVacios;
        }
        String mensajeDatosValidos = isDatosVAlidosAdText();
        if (!mensajeDatosValidos.equals("")) {
            return mensajeDatosValidos;
        }
        String mensajeCreditValidos = hayCredito();
        if (!mensajeCreditValidos.equals("")) {
            return mensajeCreditValidos;
        }
        return "";
    }
    
    private String isDatosVaciosAdText() {
        if (this.anuncioTexto.getFechaCompra().equals("")) {
            return "DEBE SELECCIONAR UNA FECHA";
        }
        if (this.anuncioTexto.getVigenciaDias() == 0) {
            return "DEBE SELECCIONAR UNA DURACION PARA EL ANUNCIO";
        }
        if (this.anuncioTexto.getTitulo().equals("")) {
            return "DEBE RELLENAR EL CAMPO PARA EL TIRULO DEL ANUNCIO";
        }
        if (this.anuncioTexto.getContenido().equals("")) {
            return "DEBE RELLENAR EL CAMPO PARA EL CONTENIDO DEL ANUNCIO";
        }
        return "";
    }
    
    private String isDatosVAlidosAdText() {
        if (!this.anuncioTexto.getFechaCompra().contains("-")) {
            return "FORMATO DE FECHA INCORRECTO";
        }
        String[] datos = this.anuncioTexto.getFechaCompra().split("-");
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
            if (this.anuncioTexto.getVigenciaDias() ==  duracionDiaValido) {
                duracionValida = true;
                break;
            }
        }
        if (!duracionValida) {
            return "ERROR EN LA SELECCION DE DURACION DE DIAS PARA EL ANUNCIO";
        }
        
        return "";
    }
    
    private String hayCredito() {
        this.idUsuario = this.anuncioTexto.getIdInversionista();
        int idInversionista = dataCompra.getIdInversionista(this.idUsuario);
        double costoCompra = dataCompra.getCostoCompra("ANUNCIO_TEXTO", this.anuncioTexto.getVigenciaDias());
        double creditoInversionista = dataCompra.getCredito(idInversionista);
        if (costoCompra > creditoInversionista) {
            return "error";
        }
        this.anuncioTexto.setPrecio(costoCompra);
        this.anuncioTexto.setIdInversionista(idInversionista);
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
        AnuncioTexto anuncio = convertirAdText();
        dataCompra.crearAnuncio(anuncio, this.anuncioTexto.getIdInversionista());
        dataCompra.crearAnuncioTexto(anuncio);
        Recarga pago = new Recarga(anuncio.getPrecio()+"", this.anuncioTexto.getFechaCompra(), this.idUsuario, 3);
        dataCompra.crearApago(pago);
        dataCompra.actualizarCreditoInversionistaCompra(Double.parseDouble(pago.getCantidad()), this.anuncioTexto.getIdInversionista());
        String mensaje = "nuevo";
        return "{\"mensaje\":\"" + mensaje + "\"}";
    }
    
    private AnuncioTexto convertirAdText() {
        int idPeriodoTiempo = dataCompra.getIdPeriodoTiempo(this.anuncioTexto.getVigenciaDias());
        AnuncioTexto adTextJava = new AnuncioTexto(this.anuncioTexto.getContenido(), this.anuncioTexto.getPrecio(),
                this.anuncioTexto.getVigenciaDias(), true, this.anuncioTexto.getIdInversionista(), idPeriodoTiempo, 1);
        adTextJava.setTitulo(this.anuncioTexto.getTitulo());
        return adTextJava;
    }
    
}
