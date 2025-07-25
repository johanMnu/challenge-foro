package com.Challenge.ForoHub.DTO;

public class RegistroTopicoDTO {

    private String titulo;
    private String mensaje;
    private Long idAutor;
    private Long idCurso;

    public RegistroTopicoDTO(String titulo, String mensaje, Long idAutor, Long idCurso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.idAutor = idAutor;
        this.idCurso = idCurso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Long getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Long idAutor) {
        this.idAutor = idAutor;
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }
}
