/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.controllers;

import com.mycompany.proyecto2_ss24.backend.data.CarteraDB;
import com.mycompany.proyecto2_ss24.backend.model.Recarga;

/**
 *
 * @author Carlos Cotom
 */
public class RecargaCreditoController {

    private final CarteraDB dataRecarga = new CarteraDB();
    private final Recarga recarga;

    public RecargaCreditoController(Recarga recarga) {
        this.recarga = recarga;
    }

    public String verificarDatosRecarga() {
        if (isDatosVacios()) {
            return "DEBE COMPLETAR TODOS LOS CAMPOS DEL FORMULARIO";
        }
        String mensajeDatosValidos = isDatosValidos();
        if (!mensajeDatosValidos.equals("")) {
            return mensajeDatosValidos;
        }
        return "";
    }

    private boolean isDatosVacios() {
        return recarga.getCantidad().equals("")
                || recarga.getFechaRecarga().equals("");
    }

    private String isDatosValidos() {
        if (!this.recarga.getFechaRecarga().contains("-")) {
            return "FORMATO DE FECHA INCORRECTO";
        }
        String[] datos = this.recarga.getFechaRecarga().split("-");
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

        if (!isDoublePositive(recarga.getCantidad())) {
            return "DEBE INGRESAR UN NUMERO POSITIVO PARA LA CANTIDAD A RECARGAR";
        }

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

    private boolean isDoublePositive(String texto) {
        try {
            double numero = Double.parseDouble(texto);
            return numero > 0;
        } catch (NumberFormatException e) {
            System.out.println("Texto ingresado NO puede ser Numero Entero");
            return false;
        }
    }

    public String crearRegistro() {
        dataRecarga.crearApago(recarga);
        switch (recarga.getIdTipoUsuario()) {
            case 1 -> //editor
                dataRecarga.actualizarCreditoAbono(Double.parseDouble(recarga.getCantidad()), recarga.getIdUsuario(), "editor");
            case 3 -> //anunciante
                dataRecarga.actualizarCreditoAbono(Double.parseDouble(recarga.getCantidad()), recarga.getIdUsuario(), "inversionista");
            default -> {
                String mensaje = "ERROR EN PROCESAR LA RECARGA, VUELVA MAS TARDE att:Servidor";
                return "{\"mensaje\":\"" + mensaje + "\"}";
            }
        }
        String mensaje = "nuevo";
        return "{\"mensaje\":\"" + mensaje + "\"}";
    }

}
