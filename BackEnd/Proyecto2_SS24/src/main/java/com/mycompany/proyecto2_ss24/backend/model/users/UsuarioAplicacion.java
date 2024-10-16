/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.model.users;


/**
 *
 * @author Carlos Cotom
 */
public class UsuarioAplicacion {

    private String foto;
    private String hobbies;
    private String temasInteres;
    private String descripcion;
    private String gustos;
    private String userName;
    private String password;
    private int idTipoUsuario;
    private String nombre;
    private int idUsuario;

    public UsuarioAplicacion() {
    }

    public UsuarioAplicacion(String foto, String hobbies, String temasInteres, String descripcion, String gustos, String userName, String password, int tipoUsuario, String nombre, int idUsuario) {
        this.foto = foto;
        this.hobbies = hobbies;
        this.temasInteres = temasInteres;
        this.descripcion = descripcion;
        this.gustos = gustos;
        this.userName = userName;
        this.password = password;
        this.idTipoUsuario = tipoUsuario;
        this.nombre = nombre;
        this.idUsuario = idUsuario;
    }

    public UsuarioAplicacion(String userName, String password, int tipoUsuario, String nombre) {
        this.userName = userName;
        this.password = password;
        this.idTipoUsuario = tipoUsuario;
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getTemasInteres() {
        return temasInteres;
    }

    public void setTemasInteres(String temasInteres) {
        this.temasInteres = temasInteres;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGustos() {
        return gustos;
    }

    public void setGustos(String gustos) {
        this.gustos = gustos;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String toJSON() {
        return "{"
                + "\"foto\":\"\","
                + "\"hobbies\":\"" + hobbies + "\","
                + "\"temasInteres\":\"" + temasInteres + "\","
                + "\"descripcion\":\"" + descripcion + "\","
                + "\"gustos\":\"" + gustos + "\","
                + "\"userName\":\"" + userName + "\","
                + "\"password\":\"" + password + "\","
                + "\"idTipoUsuario\":" + idTipoUsuario + ","
                + "\"nombre\":\"" + nombre + "\","
                + "\"idUsuario\":" + idUsuario +
                "}";
    }

    @Override
    public String toString() {
        return "UsuarioAplicacion{" + "foto=" + foto + ", hobbies=" + hobbies + ", temasInteres=" + temasInteres + ", descripcion=" + descripcion + ", gustos=" + gustos + ", userName=" + userName + ", password=" + password + ", idTipoUsuario=" + idTipoUsuario + ", nombre=" + nombre + ", idUsuario=" + idUsuario + '}';
    }

}
