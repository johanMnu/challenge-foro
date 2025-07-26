package com.Challenge.ForoHub.infra.security;

import com.Challenge.ForoHub.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${foro.security.token.secret}")
    private String secret;

    public String generarToken(Usuario usuario){

        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Foro")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(fechaExpiracion())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
           throw new RuntimeException("error al generar el token", exception);
        }
    }

    private Instant fechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String tokenJWT){
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("Foro")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception){
           throw new RuntimeException("Token JWT invalido o expirado");
        }
    }
}
