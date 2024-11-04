/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.controllers;

import com.mycompany.proyecto2_ss24.backend.data.EditorDB;

/**
 *
 * @author Carlos Cotom
 */
public class RegistroBloqueoController {
    
    private final String idRevista;
    private final String cantidadDias;
    private final EditorDB dataEditor = new EditorDB();

    public RegistroBloqueoController(String idRevista, String cantidadDias) {
        this.idRevista = idRevista;
        this.cantidadDias = cantidadDias;
    }
    
    public String verificarDatosBloqueo() {
        if (this.cantidadDias.equals("")) {
            return "DEBE COMPLETAR EL CAMPO PARA LA CANTIDAD DE DIAS";
        }
        if (!isIntegerPositivo(this.cantidadDias)) {
            return "LA CANTIDAD DE DIAS DEBE DE SER UN VALOR NUMERICO MAYOR A 0";
        }
        String mensajeErrorCredito = hayCredito();
        if (!mensajeErrorCredito.equals("")) {
            return mensajeErrorCredito;
        }
        return "";
    }
    
    private boolean isIntegerPositivo(String texto) {
        try {
            int numero = Integer.parseInt(texto);
            return numero > 0;
        } catch (NumberFormatException e) {
            System.out.println("Texto de Cantidad de Dias NO puede ser Numero Entero Mayor a 0");
            return false;
        }
    }
    
    private String hayCredito() {
        double creditoEditor = dataEditor.getCreditoEditor(dataEditor.getIdEditor(Integer.parseInt(idRevista)));
        double costoOcultacion = dataEditor.getCostoOcultacionRevista(Integer.parseInt(idRevista));
        if (costoOcultacion > creditoEditor) {
            return "error";
        }
        return "";
    }
    
    public String realizarBloqueo() {
        this.dataEditor.bloquearAnunciosRevista(Integer.parseInt(idRevista));
        this.dataEditor.actualizarCreditoEditorGasto(dataEditor.getIdEditor(Integer.parseInt(idRevista)), dataEditor.getCostoOcultacionRevista(Integer.parseInt(idRevista)));
        String mensaje = "nuevo";
        return "{\"mensaje\":\"" + mensaje + "\"}";
    }
    
}
