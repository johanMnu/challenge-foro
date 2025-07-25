package com.Challenge.ForoHub.DTO;

public class DetalleTopicoDTO {
    String titulo;
    String mensaje;
    String autor;
    String curso;


    public DetalleTopicoDTO(String titulo, String mensaje, String autor, String curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.autor = autor;
        this.curso = curso;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getAutor() {
        return autor;
    }

    public String getCurso() {
        return curso;
    }
}
