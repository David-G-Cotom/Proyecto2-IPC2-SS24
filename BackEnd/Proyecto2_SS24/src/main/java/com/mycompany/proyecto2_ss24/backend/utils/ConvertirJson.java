/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.utils;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 *
 * @author Carlos Cotom
 */
public class ConvertirJson {
    
    private Gson gson = new Gson();
    
    public Object getObjeto(String datos, Type tipoObjeto) {
        return this.gson.fromJson(datos, tipoObjeto);
    }
    
    public String getJson(Object objeto, Type tipoObjeto) {
        return this.gson.toJson(objeto, tipoObjeto);
    }
    
    public String entradaJson(BufferedReader lector) {
        String body = "";
        try {
           String line = lector.readLine();
            while (line != null) {
                body += line;
                line = lector.readLine();
            }
            System.out.println("body: ");
            System.out.println(body);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return body;
    }
    
}
