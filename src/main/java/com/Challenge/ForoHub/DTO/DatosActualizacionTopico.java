package com.Challenge.ForoHub.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizacionTopico(
       String titulo,

       String mensaje,

       Long idautor,

       String estado,

       Long idcurso

) {
}
