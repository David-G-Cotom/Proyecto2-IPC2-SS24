/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.controllers;

import com.mycompany.proyecto2_ss24.backend.data.AnuncioDB;
import com.mycompany.proyecto2_ss24.backend.model.anuncios.Anuncio;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Carlos Cotom
 */
public class AnunciosPublicidadController {
    
    private final AnuncioDB dataAnuncios = new AnuncioDB();
    private final ArrayList<Anuncio> anuncios;
    private final ArrayList<Anuncio> anunciosIzquierda = new ArrayList<>();
    private final ArrayList<Anuncio> anunciosDerecha = new ArrayList<>();
    private final int cantidadAnuncios;
    private final ArrayList<Integer> anunciosPosteados = new ArrayList<>();
    
    public AnunciosPublicidadController() {
        anuncios = dataAnuncios.getAllAnunciosActivos();
        this.evaluarVigenciaAnuncios(anuncios);
        for (Anuncio anuncio : anuncios) {
            if (!anuncio.isIsVigente()) {
                anuncios.remove(anuncio);
            }
        }
        cantidadAnuncios = anuncios.size();
        if (cantidadAnuncios == 0) {
            return;
        }
        this.dividirAnuncios();
    }
    
    private void dividirAnuncios() {
        Random random = new Random();
        boolean tocaIzquierda = true;
        boolean anuncioRepetido;
        int anuncioAleatorio;
        int i = 0;
        do {
            do {
                anuncioRepetido = false;
                anuncioAleatorio = random.nextInt(cantidadAnuncios);
                for (Integer anuncioPosteado : anunciosPosteados) {
                    if (anuncioPosteado == anuncioAleatorio) {
                        anuncioRepetido = true;
                        break;
                    }
                }
                if (!anuncioRepetido) {
                    this.anunciosPosteados.add(anuncioAleatorio);
                }
            } while (anuncioRepetido);
            if (tocaIzquierda) {
                this.anunciosIzquierda.add(this.anuncios.get(anuncioAleatorio));
                tocaIzquierda = false;
            } else {
                this.anunciosDerecha.add(this.anuncios.get(anuncioAleatorio));
                tocaIzquierda = true;
            }
            i++;
        } while (i < this.cantidadAnuncios);
    }
    
    private void evaluarVigenciaAnuncios(ArrayList<Anuncio> anuncios) {
        for (Anuncio anuncio : anuncios) {
            if (anuncio.isIsVigente()) {
                LocalDate fechaCompra = Date.valueOf(anuncio.getFechaCompra()).toLocalDate();
                LocalDate fechaActual = LocalDate.now();
                long diasDiferencia = ChronoUnit.DAYS.between(fechaCompra, fechaActual);
                System.out.println("fechaCompra: " + fechaCompra);
                System.out.println("fechaActual: " + fechaActual);
                System.out.println("diasDiferencia: " + diasDiferencia);
                System.out.println("vigenciaDias: " + anuncio.getVigenciaDias());
                int tiempoRestante = anuncio.getVigenciaDias() - (int) diasDiferencia;
                System.out.println("tiempoRestante: " + tiempoRestante);
                if (tiempoRestante <= 0) {
                    System.out.println("Anuncio: " + anuncio.getIdAnuncio() + "YA NO ES VIGENTE");
                    anuncio.setIsVigente(false);
                    anuncio.setIsActivo(false);
                    dataAnuncios.actualizarEstadoVigencia(false, anuncio.getIdAnuncio());
                    dataAnuncios.editarAnuncio(anuncio.getIdAnuncio(), false);
                }
            }
        }
    }
    
    public ArrayList<Anuncio> getAnunciosIzquierda() {
        return anunciosIzquierda;
    }

    public ArrayList<Anuncio> getAnunciosDerecha() {
        return anunciosDerecha;
    }
    
}
