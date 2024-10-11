/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.mode.users;

import com.mycompany.proyecto2_ss24.backend.model.Revista;
import java.util.ArrayList;

/**
 *
 * @author Carlos Cotom
 */
public class Editor extends UsuarioAplicacion {
    
    private ArrayList<Revista> revistas;
    private int idEditor;

    public Editor() {
    }
    
    public Editor(String pathPhoto, String hobbies, String temasInteres, String descripcion, String gustos, String userName, String password, int tipoUsuario, String nombre, int idUsuario) {
        super(pathPhoto, hobbies, temasInteres, descripcion, gustos, userName, password, tipoUsuario, nombre, idUsuario);
    }

    public Editor(ArrayList<Revista> revistas, String pathPhoto, String hobbies, String temasInteres, String descripcion, String gustos, String userName, String password, int tipoUsuario, String nombre, int idUsuario) {
        super(pathPhoto, hobbies, temasInteres, descripcion, gustos, userName, password, tipoUsuario, nombre, idUsuario);
        this.revistas = revistas;
    }    

    public ArrayList<Revista> getRevistas() {
        return revistas;
    }

    public void setRevistas(ArrayList<Revista> revistas) {
        this.revistas = revistas;
    }

    public int getIdEditor() {
        return idEditor;
    }

    public void setIdEditor(int idEditor) {
        this.idEditor = idEditor;
    }
    
}
