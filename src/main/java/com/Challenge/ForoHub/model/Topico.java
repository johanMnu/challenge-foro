package com.Challenge.ForoHub.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Table(name = "topicos")
@Entity(name = "Topicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private String status;

    @ManyToOne
    private Usuario autor;

    @ManyToOne
    private Curso curso;

    @OneToMany(mappedBy = "topico")
    private List<Respuesta> respuestas;

    public Topico( String titulo, String mensaje, Usuario autor, Curso curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = LocalDateTime.now();
        this.status = "ABIERTO";
        this.autor = autor;
        this.curso = curso;
    }
    @Override
    public String toString() {
        return "Topico{" +
                "titulo='" + titulo + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", autor=" + (autor != null ? autor.getNombre() : null) +
                ", curso=" + (curso != null ? curso.getNombre() : null) +
                '}';
    }

}
