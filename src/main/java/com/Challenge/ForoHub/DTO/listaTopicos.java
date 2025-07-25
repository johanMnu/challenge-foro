package com.Challenge.ForoHub.DTO;

import com.Challenge.ForoHub.model.Topico;

import java.time.LocalDateTime;

public record listaTopicos(
        Long Id,
    String titulo,
    String mensaje,
    LocalDateTime fechaCreacion,
    String estado,
    String autor,
    String curso

) {
}
