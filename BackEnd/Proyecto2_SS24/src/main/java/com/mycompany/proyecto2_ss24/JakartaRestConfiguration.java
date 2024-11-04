package com.mycompany.proyecto2_ss24;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Configures Jakarta RESTful Web Services for the application.
 * @author Juneau
 */
@ApplicationPath("resources")
public class JakartaRestConfiguration extends Application {
    
     @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        // Registra tus recursos y filtros aquí
        resources.add(com.mycompany.proyecto2_ss24.resources.RegistroUsuarioResource.class);
        resources.add(com.mycompany.proyecto2_ss24.resources.InicioSesionResource.class);
        resources.add(com.mycompany.proyecto2_ss24.resources.PerfilUsuarioResource.class);
        resources.add(com.mycompany.proyecto2_ss24.resources.RevistaResource.class);
        resources.add(com.mycompany.proyecto2_ss24.resources.ObtencionRevistaResource.class);
        resources.add(com.mycompany.proyecto2_ss24.resources.RecargaCreditoResource.class);
        resources.add(com.mycompany.proyecto2_ss24.resources.RevistaNoSuscritaResource.class);
        resources.add(com.mycompany.proyecto2_ss24.resources.SuscripcionResource.class);
        resources.add(com.mycompany.proyecto2_ss24.resources.ComentarioResource.class);
        resources.add(com.mycompany.proyecto2_ss24.resources.LikeResource.class);
        resources.add(com.mycompany.proyecto2_ss24.resources.AnuncioResource.class);
        resources.add(com.mycompany.proyecto2_ss24.resources.ImagenResource.class);
        resources.add(com.mycompany.proyecto2_ss24.resources.ObtencionAnuncioResource.class);
        resources.add(com.mycompany.proyecto2_ss24.resources.MediaResource.class);
        resources.add(com.mycompany.proyecto2_ss24.resources.RegistroPublicacionResource.class);
        resources.add(com.mycompany.proyecto2_ss24.resources.ObtencionPreciosResource.class);
        resources.add(com.mycompany.proyecto2_ss24.resources.RegistroPreciosResource.class);
        resources.add(com.mycompany.proyecto2_ss24.resources.RegistroBloqueResource.class);
        return resources;
    }
    
}
