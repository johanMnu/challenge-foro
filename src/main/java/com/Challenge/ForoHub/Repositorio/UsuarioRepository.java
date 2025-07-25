package com.Challenge.ForoHub.Repositorio;

import com.Challenge.ForoHub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Usuario findByNombre(String nombre);
}
