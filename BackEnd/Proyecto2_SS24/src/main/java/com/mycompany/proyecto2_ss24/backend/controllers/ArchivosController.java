/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.controllers;

import jakarta.servlet.http.Part;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Carlos Cotom
 */
public class ArchivosController {
    
    private static final String PATH = "DataServer\\";
    private static final String PDF_EXTENS = ".pdf";
    private static final String IMG_EXTENS = ".png";
    private static final String VIDEO_EXTENS = ".mp4";
    
    public String guardarArchivo(Part part, String nombreArchivo, String extension, String servidor) throws IOException {
        String path = "";
        InputStream fileStream = part.getInputStream();
        try (BufferedReader input = new BufferedReader(new InputStreamReader(fileStream))){
            String line = input.readLine();
            while (line != null) {
                line = input.readLine();
            }
            path = servidor + PATH + nombreArchivo + extension;
            part.write(path);
        } catch (Exception e) {
            System.out.println("ERROR AL GUARDAR EL ARCHIVO: " + e.getMessage());
        }
        return path;
    }
    
    public File getArchivo(String path) {
        return new File(path);
    }
    
}
