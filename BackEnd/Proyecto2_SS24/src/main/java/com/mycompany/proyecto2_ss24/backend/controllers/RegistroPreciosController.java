/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.controllers;

import com.mycompany.proyecto2_ss24.backend.data.PreciosAnuncioDB;
import com.mycompany.proyecto2_ss24.backend.data.PreciosRevistaDB;

/**
 *
 * @author Carlos Cotom
 */
public class RegistroPreciosController {

    private final String[] precios;

    public RegistroPreciosController(String[] precios) {
        this.precios = precios;
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
        for (String precio : precios) {
            if (precio.equals("")) {
                return true;
            }
        }
        return false;
    }

    private String isDatosValidos() {
        for (String precio : precios) {
            if (!isDoublePositive(precio)) {
                return "DEBE INGRESAR UN NUMERO POSITIVO PARA LA CANTIDAD A RECARGAR";
            }
        }
        return "";
    }

    private boolean isDoublePositive(String texto) {
        try {
            double numero = Double.parseDouble(texto);
            return numero >= 0;
        } catch (NumberFormatException e) {
            System.out.println("Texto ingresado NO puede ser Numero Entero");
            return false;
        }
    }

    public String actualizar(String tipoActualizacio, int idRevista) {
        PreciosAnuncioDB dataPrecios = new PreciosAnuncioDB();
        double[] nuevosPrecios = new double[this.precios.length];
        for (int i = 0; i < nuevosPrecios.length; i++) {
            nuevosPrecios[i] = Double.parseDouble(this.precios[i]);
        }
        switch (tipoActualizacio) {
            case "TipoAnuncio" -> dataPrecios.actualizarPrecioTipoAnuncios(nuevosPrecios);
            case "PeriodoTiempoAnuncio" -> dataPrecios.actualizarPrecioPeriodoTiempo(nuevosPrecios);
            case "Revista" -> {
                PreciosRevistaDB data = new PreciosRevistaDB();
                data.actualizarPrecioRevista(nuevosPrecios[0], nuevosPrecios[1], nuevosPrecios[2], idRevista);
            }
        }
        String mensaje = "exito";
        return "{\"mensaje\":\"" + mensaje + "\"}";
    }

}
