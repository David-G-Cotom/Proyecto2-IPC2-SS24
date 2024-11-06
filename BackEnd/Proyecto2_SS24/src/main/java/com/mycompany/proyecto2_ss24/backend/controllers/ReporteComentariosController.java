/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.controllers;

import com.mycompany.proyecto2_ss24.backend.data.ReporteEditorDB;
import com.mycompany.proyecto2_ss24.backend.model.reportes.ContenidoReporteComentarios;
import com.mycompany.proyecto2_ss24.backend.model.reportes.ReporteComentarios;
import java.util.ArrayList;

/**
 *
 * @author Carlos Cotom
 */
public class ReporteComentariosController {

    private final ReporteEditorDB dataReporte = new ReporteEditorDB();
    private final ReporteComentarios filtroReporte;
    private boolean hayFechas;
    private boolean hayIdRevista;

    public ReporteComentariosController(ReporteComentarios filtroReporte) {
        this.filtroReporte = filtroReporte;
        this.hayIdRevista = false;
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
            String fechaEvaluar = "";
            String fechaEspecifica = "";
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

    public ArrayList<ContenidoReporteComentarios> getContenidoReporte() {
        if (!this.filtroReporte.getIdRevista().equals("")) {
            this.hayIdRevista = true;
        }
        ArrayList<ContenidoReporteComentarios> contenido;
        if (hayFechas || hayIdRevista) {
            //  Eceneario 1: Todos los datos fueron ingresados
            //  Eceneario 2: Solo las Fechas fueron ingresadas
            //  Eceneario 3: Solo se selecciono la Revista
            String query = this.generarConsultaWHERE();
            contenido = this.dataReporte.getReporteComentarios(query, this.contarIterrogantes(query), this.filtroReporte);
            this.generarNumeroRevista(contenido);
            return contenido;
        } else {//  Eceneario 4: No se ingreso ningun dato
            int idUsuario = this.filtroReporte.getIdUsuario();
            int idEditor = this.dataReporte.getIdEditor(idUsuario);
            ArrayList<Integer> idsRevistaEditor = this.dataReporte.getIdsRevistasEditor(idEditor);
            contenido = this.dataReporte.getFullReporteComentarios(idsRevistaEditor);
            this.generarNumeroRevista(contenido);
            return contenido;
        }
    }

    private void generarNumeroRevista(ArrayList<ContenidoReporteComentarios> contenido) {
        int numero = 1;
        int indice = 0;
        String nombreRevistaActual;
        String nombreRevistaAnterior = "";
        for (ContenidoReporteComentarios reporte : contenido) {
            nombreRevistaActual = reporte.getNombreRevista();
            if (indice == 0) {
                reporte.setNumeroRevista(numero + "");
            } else {
                if (!nombreRevistaActual.equals(nombreRevistaAnterior)) {
                    numero++;
                }
                reporte.setNumeroRevista(numero + "");
            }
            nombreRevistaAnterior = reporte.getNombreRevista();
            indice++;
        }
    }

    private int contarIterrogantes(String queryWHERE) {
        int cantidadInterrograntes = 0;
        for (int i = 0; i < queryWHERE.length(); i++) {
            if (queryWHERE.charAt(i) == '?') {
                cantidadInterrograntes++;
            }
        }
        System.out.println("Cantidad: " + cantidadInterrograntes);
        //  cantidad = 1 => hay revista
        //  cantidad = 2 => hay fechas
        //  cantidad = 3 => estan ambas clausulas
        return cantidadInterrograntes;
    }

    private String generarConsultaWHERE() {
        String query = "";
        boolean hayPrimerCondicion = false;
        if (this.hayFechas) {
            query += " WHERE fecha_comentario BETWEEN ? AND ?";
            hayPrimerCondicion = true;
        }
        if (this.hayIdRevista) {
            if (!hayPrimerCondicion) {
                query += " WHERE revista = ?";
            } else {
                query += " AND revista = ?";
            }
        }
        return query;
    }

}
