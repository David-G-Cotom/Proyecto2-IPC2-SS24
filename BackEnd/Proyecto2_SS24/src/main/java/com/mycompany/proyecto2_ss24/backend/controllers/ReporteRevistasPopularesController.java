/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.controllers;

import com.mycompany.proyecto2_ss24.backend.data.ReporteAdminDB;
import com.mycompany.proyecto2_ss24.backend.model.reportes.ContenidoReporteRevistaPopular;
import com.mycompany.proyecto2_ss24.backend.model.reportes.ReporteRevistaPopular;
import java.util.ArrayList;

/**
 *
 * @author Carlos Cotom
 */
public class ReporteRevistasPopularesController {

    private final ReporteAdminDB dataReporte = new ReporteAdminDB();
    private final ReporteRevistaPopular filtroReporte;
    private boolean hayFechas;

    public ReporteRevistasPopularesController(ReporteRevistaPopular filtroReporte) {
        this.filtroReporte = filtroReporte;
    }

    public String verificarFechas() {
        if (this.filtroReporte.getFechaInicio().equals("")
                && this.filtroReporte.getFechaFin().equals("")) {
            this.hayFechas = false;
            return "";
        } else if (!this.filtroReporte.getFechaInicio().equals("")
                && !this.filtroReporte.getFechaFin().equals("")) {
            return isFechasValidas();
        } else {
            return "NO PUEDE DEJAR UN CAMPO DE FECHA VACIO";
        }
    }

    private String isFechasValidas() {
        int ciclo = 0;
        do {
            String fechaEvaluar;
            String fechaEspecifica;
            if (ciclo == 0) {
                fechaEvaluar = this.filtroReporte.getFechaInicio();
                fechaEspecifica = "INICIO";
            } else {
                fechaEvaluar = this.filtroReporte.getFechaFin();
                fechaEspecifica = "FIN";
            }
            if (!fechaEvaluar.contains("-")) {
                return "FORMATO DE FECHA " + fechaEspecifica + " INCORRECTO";
            }
            String[] datos = fechaEvaluar.split("-");
            if (datos.length != 3) {
                return "FORMATO DE FECHA " + fechaEspecifica + " INCORRECTO";
            }
            String yyyy = datos[0];
            String mm = datos[1];
            String dd = datos[2];
            if (!isIntegerPositive(yyyy) || !isIntegerPositive(mm) || !isIntegerPositive(dd)) {
                return "FORMATO DE FECHA " + fechaEspecifica + " INCORRECTO";
            }
            if (Integer.parseInt(mm) > 12 && Integer.parseInt(dd) > 31 && Integer.parseInt(yyyy) > 9999) {
                return "FORMATO DE FECHA " + fechaEspecifica + " INCORRECTO";
            }
            ciclo++;
        } while (ciclo < 2);
        this.hayFechas = true;
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

    public ArrayList<ContenidoReporteRevistaPopular> getContenidoReporte() {
        ArrayList<ContenidoReporteRevistaPopular> contenido;
        //  Eceneario 1: Las Fechas fueron ingresadas
        //  Eceneario 2: No se ingreso ningun dato
        String query = this.generarConsultaWHERE();
        contenido = this.dataReporte.getReporteRevistasPopulares(query, this.contarIterrogantes(query), this.filtroReporte);
        return contenido;

    }

    private int contarIterrogantes(String queryWHERE) {
        int cantidadInterrograntes = 0;
        for (int i = 0; i < queryWHERE.length(); i++) {
            if (queryWHERE.charAt(i) == '?') {
                cantidadInterrograntes++;
            }
        }
        //  cantidad = 0 => no hay fechas
        //  cantidad = 2 => hay fechas
        return cantidadInterrograntes;
    }

    private String generarConsultaWHERE() {
        String query = "";
        if (this.hayFechas) {
            query += " WHERE fecha_suscripcion BETWEEN ? AND ?";
        }
        return query;
    }

}
