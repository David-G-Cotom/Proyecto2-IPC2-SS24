/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2_ss24.backend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mycompany.proyecto2_ss24.backend.model.users.UsuarioAplicacion;
import java.util.Date;

/**
 *
 * @author Carlos Cotom
 */
public class TokenService {
    
    private static final String SECRET = "your-256-bit-secret";
    
    public String generarToken(UsuarioAplicacion usuario) {
        Algorithm algoritmo = Algorithm.HMAC256(SECRET);
        String token = JWT.create().withClaim("USUARIO", usuario.toString()).withClaim("ROL", usuario.getIdTipoUsuario()).withIssuedAt(new Date()).sign(algoritmo);
        return token;
    }
    
    public DecodedJWT verificarToken(String token) {
        Algorithm algoritmo = Algorithm.HMAC256(SECRET);
        return JWT.require(algoritmo).build().verify(token);
    }
    
}
