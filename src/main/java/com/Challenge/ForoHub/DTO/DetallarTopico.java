package com.Challenge.ForoHub.DTO;

import java.time.LocalDateTime;

public record DetallarTopico(
        String titulo,
        String mensaje,
        LocalDateTime FechaCreacion,
        String estado,
        String autor,
        String curso

) {
}
