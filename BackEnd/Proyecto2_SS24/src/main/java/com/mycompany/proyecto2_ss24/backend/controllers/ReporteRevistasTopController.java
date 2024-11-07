/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.controllers;

import com.mycompany.proyecto2_ss24.backend.data.ReporteEditorDB;
import com.mycompany.proyecto2_ss24.backend.model.reportes.ContenidoReporteRevistasTop;
import com.mycompany.proyecto2_ss24.backend.model.reportes.ReporteRevistasTop;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Carlos Cotom
 */
public class ReporteRevistasTopController {

    private final ReporteEditorDB dataReporte = new ReporteEditorDB();
    private final ReporteRevistasTop filtroReporte;
    private boolean hayFechas;
    private boolean hayIdRevista;

    public ReporteRevistasTopController(ReporteRevistasTop filtroReporte) {
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

    public ArrayList<ContenidoReporteRevistasTop> getContenidoReporte() {
        if (!this.filtroReporte.getIdRevista().equals("")) {
            this.hayIdRevista = true;
        }
        ArrayList<ContenidoReporteRevistasTop> contenido;
        if (hayFechas || hayIdRevista) {
            //  Eceneario 1: Todos los datos fueron ingresados
            //  Eceneario 2: Solo las Fechas fueron ingresadas
            //  Eceneario 3: Solo se selecciono la Revista
            String query = this.generarConsultaWHERE();
            ArrayList<String[]> contenidoFilasReporte = this.dataReporte.getReporteRevistasTop(query, this.contarIterrogantes(query), this.filtroReporte);
            contenido = this.generarContenidoReporte(contenidoFilasReporte);
            this.generarNumeroRevista(contenido);
            return contenido;
        } else {//  Eceneario 4: No se ingreso ningun dato
            int idUsuario = this.filtroReporte.getIdUsuario();
            int idEditor = this.dataReporte.getIdEditor(idUsuario);
            ArrayList<Integer> idsRevistaEditor = this.dataReporte.getIdsRevistasEditor(idEditor);
            ArrayList<String[]> contenidoFilasReporte = this.dataReporte.getFullReporteRevistasTop(idsRevistaEditor);
            contenido = this.generarContenidoReporte(contenidoFilasReporte);
            this.generarNumeroRevista(contenido);
            return contenido;
        }
    }

    private void generarNumeroRevista(ArrayList<ContenidoReporteRevistasTop> contenido) {
        int numero = 1;
        int indice = 0;
        String nombreRevistaActual;
        String nombreRevistaAnterior = "";
        for (ContenidoReporteRevistasTop reporte : contenido) {
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
        //  cantidad = 1 => hay revista
        //  cantidad = 2 => hay fechas
        //  cantidad = 3 => estan ambas clausulas
        return cantidadInterrograntes;
    }

    private String generarConsultaWHERE() {
        String query = "";
        boolean hayPrimerCondicion = false;
        if (this.hayFechas) {
            query += " WHERE fecha_like BETWEEN ? AND ?";
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

    private ArrayList<ContenidoReporteRevistasTop> generarContenidoReporte(ArrayList<String[]> contenidoFilas) {
        ArrayList<ContenidoReporteRevistasTop> contenidoReporte = new ArrayList<>();
        int indice = 0;
        String nombreRevistaActual;
        String nombreRevistaAnterior = "";
        boolean isNuevoReporte;
        ArrayList<String> nombreSuscriptores = new ArrayList<>();
        ArrayList<String> fechasLikes = new ArrayList<>();
        ContenidoReporteRevistasTop reporte = new ContenidoReporteRevistasTop();
        for (String[] contenidoFila : contenidoFilas) {
            System.out.println(Arrays.toString(contenidoFila));
        }
        for (String[] contenidoFila : contenidoFilas) {
            nombreRevistaActual = contenidoFila[1];
            if (indice != 0) {
                isNuevoReporte = !nombreRevistaActual.equals(nombreRevistaAnterior);
                if (isNuevoReporte) {
                    //  Se cargan los datos faltantes al anterior reporte antes de crear uno nuevo
                    reporte.setNombresSuscriptores(nombreSuscriptores);
                    reporte.setFechasLikes(fechasLikes);
                    System.out.println(reporte.toJSON());
                    contenidoReporte.add(reporte);

                    reporte = new ContenidoReporteRevistasTop();
                    reporte.setNumeroRevista(contenidoFila[0]);
                    reporte.setNombreRevista(contenidoFila[1]);
                    reporte.setCantidadlikes(Integer.parseInt(contenidoFila[2]));
                    nombreSuscriptores = new ArrayList<>();
                    fechasLikes = new ArrayList<>();
                }
                nombreSuscriptores.add("\"" + contenidoFila[3] + "\"");
                fechasLikes.add("\"" + contenidoFila[4] + "\"");
            } else {
                reporte.setNumeroRevista(contenidoFila[0]);
                reporte.setNombreRevista(contenidoFila[1]);
                reporte.setCantidadlikes(Integer.parseInt(contenidoFila[2]));
                nombreSuscriptores.add("\"" + contenidoFila[3] + "\"");
                fechasLikes.add("\"" + contenidoFila[4] + "\"");
            }
            nombreRevistaAnterior = contenidoFila[1];
            indice++;
            if (indice == contenidoFilas.size()) {
                reporte.setNombresSuscriptores(nombreSuscriptores);
                reporte.setFechasLikes(fechasLikes);
                System.out.println(reporte.toJSON());
                contenidoReporte.add(reporte);
            }
        }
        return contenidoReporte;
    }

}
