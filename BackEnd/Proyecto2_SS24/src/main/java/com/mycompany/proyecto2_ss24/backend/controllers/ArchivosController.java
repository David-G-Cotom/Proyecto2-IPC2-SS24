/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Carlos Cotom
 */
public class ArchivosController {
    
    private final String PATH = "DataServer" + File.separatorChar;
    
    public String guardarArchivo(InputStream fileStream, String nombreArchivo) {
        File carpeta = new File(PATH);
        if (!carpeta.exists()) {
            System.out.println("No esxiste carpeta");
            carpeta.mkdir();
            System.out.println("Carpeta creada");
        } else {
            System.out.println("Carpeta ya existe");
        }
        String path = PATH + nombreArchivo;
        try (OutputStream out = new FileOutputStream(new File(path))){
            int read;
            byte[] bytes = new byte[1024];
            while ((read = fileStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            System.out.println(path);
            System.out.println("Archivo guardado");
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR AL GUARDAR EL ARCHIVO: " + ex);
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println("ERROR AL GUARDAR EL ARCHIVO: " + ex);
            System.out.println(ex.getMessage());
        }
        return path;
    }
    
    public File getArchivo(String path) {
        return new File(path);
    }
    
}
