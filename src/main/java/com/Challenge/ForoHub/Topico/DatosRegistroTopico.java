package com.Challenge.ForoHub.Topico;

public record DatosRegistroTopico(
        String titulo,
        String mensaje,
        String autor,  // nombre del usuario
        String curso   // nombre del curso

) {
}
