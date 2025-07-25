package com.Challenge.ForoHub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JsonIgnore
    private Topico topico;

    @ManyToOne
    private Usuario autor;

    private boolean solucion;

    public Respuesta() {}

    public Respuesta(Long id, String mensaje, LocalDateTime fechaCreacion, Topico topico, Usuario autor, boolean solucion) {
        this.id = id;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
        this.topico = topico;
        this.autor = autor;
        this.solucion = solucion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public Topico getTopico() { return topico; }
    public void setTopico(Topico topico) { this.topico = topico; }

    public Usuario getAutor() { return autor; }
    public void setAutor(Usuario autor) { this.autor = autor; }

    public boolean isSolucion() { return solucion; }
    public void setSolucion(boolean solucion) { this.solucion = solucion; }
}
