/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.controllers;

import com.mycompany.proyecto2_ss24.backend.data.ReporteAdminDB;
import com.mycompany.proyecto2_ss24.backend.model.reportes.ContenidoReporteCompraAnuncio;
import com.mycompany.proyecto2_ss24.backend.model.reportes.ReporteCompraAnuncio;
import java.util.ArrayList;

/**
 *
 * @author Carlos Cotom
 */
public class ReporteBuyAdController {

    private final ReporteAdminDB dataReporte = new ReporteAdminDB();
    private final ReporteCompraAnuncio filtroReporte;
    private boolean hayFechas;
    private boolean hayIdTipoAnuncio;
    private boolean hayIdPeriodoTiempo;

    public ReporteBuyAdController(ReporteCompraAnuncio filtroReporte) {
        this.filtroReporte = filtroReporte;
        this.hayIdTipoAnuncio = false;
        this.hayIdPeriodoTiempo = false;
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

    public ArrayList<ContenidoReporteCompraAnuncio> getContenidoReporte() {
        if (!this.filtroReporte.getTipoAnuncio().equals("")) {
            this.hayIdTipoAnuncio = true;
        }
        if (!this.filtroReporte.getPeriodoTiempo().equals("")) {
            this.hayIdPeriodoTiempo = true;
        }
        ArrayList<ContenidoReporteCompraAnuncio> contenido;
        //  Eceneario 1: Todos los datos fueron ingresados
        //  Eceneario 2: (Solo las Fechas | el Tipo de Anuncio | el Periodo de Tiempo) se ingreso
        //  Eceneario 3: Culaquier Combinacion entre las Fechas, el Tipo de Anuncio y el Periodo de Tiempo
        //  Eceneario 4: No se ingreso ningun dato
        String query = this.generarConsultaWHERE();
        contenido = this.dataReporte.getReporteAcompraAnuncios(query, this.contarIterrogantes(query), this.filtroReporte);
        return contenido;
    }

    private int contarIterrogantes(String queryWHERE) {
        int cantidadInterrograntes = 0;
        for (int i = 0; i < queryWHERE.length(); i++) {
            if (queryWHERE.charAt(i) == '?') {
                cantidadInterrograntes++;
            }
        }
        System.out.println("Cantidad: " + cantidadInterrograntes);
        //  cantidad = 0 => no hay datos
        //  cantidad = 1 => hay idTipoAnuncio | hay idPeriodoTiempo
        //  cantidad = 2 => hay fechas | hay idTipoAnuncio y idPeriodoTiempo
        //  cantidad = 3 => hay fechas | (hay idTipoAnuncio | idPeriodoTiempo)
        //  cantidad = 4 => estan todas las clausulas
        return cantidadInterrograntes;
    }

    private String generarConsultaWHERE() {
        String query = "";
        boolean hayPrimerCondicion = false;
        if (this.hayFechas) {
            query += " WHERE fecha_compra BETWEEN ? AND ?";
            hayPrimerCondicion = true;
        }
        if (this.hayIdTipoAnuncio) {
            if (!hayPrimerCondicion) {
                query += " WHERE tipo_anuncio = ?";
                hayPrimerCondicion = true;
            } else {
                query += " AND tipo_anuncio = ?";
            }
        }
        if (this.hayIdPeriodoTiempo) {
            if (!hayPrimerCondicion) {
                query += " WHERE id_periodo = ?";
            } else {
                query += " AND id_periodo = ?";
            }
        }
        return query;
    }

}