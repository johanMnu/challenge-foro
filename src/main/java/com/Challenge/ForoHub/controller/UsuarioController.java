package com.Challenge.ForoHub.controller;

import com.Challenge.ForoHub.DTO.DatosRegistroUsuario;
import com.Challenge.ForoHub.DTO.DatosRespuestRegistroUsuario;
import com.Challenge.ForoHub.Repositorio.UsuarioRepository;
import com.Challenge.ForoHub.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public DatosRespuestRegistroUsuario crearUsuario(@RequestBody DatosRegistroUsuario datos) {
        Usuario usuario = new Usuario();
        usuario.setNombre(datos.nombre());
        usuario.setCorreoElectronico(datos.correoElectronico());
        usuario.setLogin(datos.correoElectronico());
        usuario.setContrasena(passwordEncoder.encode(datos.contrasena()));

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return new DatosRespuestRegistroUsuario(
                usuarioGuardado.getId(),
                usuarioGuardado.getNombre(),
                usuarioGuardado.getCorreoElectronico()
        );
    }

}
