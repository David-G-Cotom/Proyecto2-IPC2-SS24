/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.controllers;

import com.mycompany.proyecto2_ss24.backend.data.RevistaDB;
import com.mycompany.proyecto2_ss24.backend.model.CategoriaEnum;
import com.mycompany.proyecto2_ss24.backend.model.EtiquetaEnum;
import com.mycompany.proyecto2_ss24.backend.model.Revista;
import com.mycompany.proyecto2_ss24.backend.model.RevistaTS;
import java.util.ArrayList;

/**
 *
 * @author Carlos Cotom
 */
public class RegistroRevistaController {

    private final RevistaDB dataRevista = new RevistaDB();
    private final RevistaTS revista;

    public RegistroRevistaController(RevistaTS revista) {
        this.revista = revista;
    }

    public String verificarDatosRevista() {
        String mensajeDatosVacios = isDatosVacios();
        if (!mensajeDatosVacios.equals("")) {
            return mensajeDatosVacios;
        }
        String mensajeDatosValidos = isDatosValidos();
        if (!mensajeDatosValidos.equals("")) {
            return mensajeDatosValidos;
        }
        return "";
    }

    private String isDatosVacios() {
        if (this.revista.getNombre().equals("")) {
            return "DEBE RELLENAR EL CAMPO PARA EL NOMMBRE DE LA REVISTA";
        }
        if (this.revista.getDescripcion().equals("")) {
            return "DEBE RELLENAR EL CAMPO PARA LA DESCRIPCION DE LA REVISTA";
        }
        if (this.revista.getFechaCreacion().equals("")) {
            return "DEBE SELECCIONAR UNA FECHA";
        }
        if (this.revista.getCategoria().equals("")) {
            return "DEBE SELECCIONAR UNA CATEGORIA PARA LA REVISTA";
        }
        if (this.revista.getEtiquetas().length == 0 || this.revista.getEtiquetas() == null) {
            return "DEBE SELECCIONAR ALMENOS UNA ETIQUETA PARA LA REVISTA";
        }
        return "";
    }

    private String isDatosValidos() {
        boolean etiquetaValida = false;
        for (String etiqueta : this.revista.getEtiquetas()) {
            for (EtiquetaEnum value : EtiquetaEnum.values()) {
                if (EtiquetaEnum.valueOf(etiqueta) == value) {
                    etiquetaValida = true;
                    break;
                }
            }
            if (!etiquetaValida) {
                return "ERROR EN LA SELECCION DE ETIQUETAS";
            }
            etiquetaValida = false;
        }
        boolean categoriaValida = false;
        for (CategoriaEnum value : CategoriaEnum.values()) {
            if (CategoriaEnum.valueOf(this.revista.getCategoria()) == value) {
                categoriaValida = true;
                break;
            }
        }
        if (!categoriaValida) {
            return "ERROR EN LA SELECCION DE CATEGORIA";
        }
        if (!this.revista.getFechaCreacion().contains("-")) {
            return "FORMATO DE FECHA INCORRECTO";
        }
        String[] datos = this.revista.getFechaCreacion().split("-");
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

    public String crearRevista() {
        Revista nuevaRevista = this.convertirRevista();
        dataRevista.crearRevista(nuevaRevista, revista.getIdUsuario());
        int idRevista = dataRevista.getIdRevistaCreada();
        nuevaRevista.setIdRevista(idRevista);
        dataRevista.crearEtiquetas(idRevista, nuevaRevista.getEtiquetas());
        String mensaje = "nuevo";
        return "{\"mensaje\":\"" + mensaje + "\"}";
    }

    private Revista convertirRevista() {
        Revista revistaJava = new Revista();
        revistaJava.setDescripcion(this.revista.getDescripcion());
        revistaJava.setFechaCreacion(this.revista.getFechaCreacion());
        revistaJava.setNombreRevista(this.revista.getNombre());
        revistaJava.setPuedeComentarse(this.revista.isPuedeComentarse());
        revistaJava.setPuedeSuscribirse(this.revista.isPuedeSuscribirse());
        revistaJava.setPuedeTenerLikes(this.revista.isPuedeTenerLikes());
        revistaJava.setCategoria(CategoriaEnum.valueOf(this.revista.getCategoria()));
        ArrayList<EtiquetaEnum> etiquetasEnum = new ArrayList<>();
        for (String etiqueta : this.revista.getEtiquetas()) {
            etiquetasEnum.add(EtiquetaEnum.valueOf(etiqueta));
        }
        revistaJava.setEtiquetas(etiquetasEnum);
        return revistaJava;
    }

}
