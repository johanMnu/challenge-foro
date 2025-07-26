package com.Challenge.ForoHub.controller;

import com.Challenge.ForoHub.DTO.DatosLogin;
import com.Challenge.ForoHub.infra.security.DatosTokenJWT;
import com.Challenge.ForoHub.infra.security.TokenService;
import com.Challenge.ForoHub.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosLogin datos) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(datos.login(), datos.contrasena());
        var autenticacion = authenticationManager.authenticate(authenticationToken);

        var tokenJWT = tokenService.generarToken((Usuario) autenticacion.getPrincipal());

        return ResponseEntity.ok( new DatosTokenJWT(tokenJWT));
    }
}
