package com.Challenge.ForoHub.Repositorio;

import com.Challenge.ForoHub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Usuario findByNombre(String nombre);
    Optional<Usuario> findByLogin(String login);


}
