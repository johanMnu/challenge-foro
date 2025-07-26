package com.Challenge.ForoHub.controller;

import com.Challenge.ForoHub.DTO.*;
import com.Challenge.ForoHub.Repositorio.CursoRepository;
import com.Challenge.ForoHub.Repositorio.TopicoRepository;
import com.Challenge.ForoHub.Repositorio.UsuarioRepository;
import com.Challenge.ForoHub.infra.exception.AutorNoEncontrado;
import com.Challenge.ForoHub.infra.exception.CursoNoEncontrado;
import com.Challenge.ForoHub.infra.exception.TopicoNoEncontrado;
import com.Challenge.ForoHub.model.Curso;
import com.Challenge.ForoHub.model.Topico;
import com.Challenge.ForoHub.model.Usuario;
import com.Challenge.ForoHub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public DetalleTopicoDTO crearTopico(@RequestBody @Valid RegistroTopicoDTO datos){
        Usuario autor = usuarioRepository.findById(datos.getIdAutor())
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
        Curso curso = cursoRepository.findById(datos.getIdCurso())
                .orElseThrow(()-> new RuntimeException("Curso no encontrado"));

        Topico topico = new Topico(
                datos.getTitulo(),
                datos.getMensaje(),
                autor,
                curso
        );

        Topico topicoGuardado = topicoRepository.save(topico);
        System.out.println("topico creado: " + topicoGuardado);
        return new DetalleTopicoDTO(
                topicoGuardado.getTitulo(),
                topicoGuardado.getMensaje(),
                autor.getNombre(),
                curso.getNombre()
        );



    }
    @GetMapping
    public Page<listaTopicos> listar(@PageableDefault(size=10, sort = {"fechaCreacion"}) Pageable paginacion){
        return topicoService.listarTopicos(paginacion);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DetallarTopico> obtenerTopicoPorId(@PathVariable Long id) {
        return topicoRepository.findById(id)
                .map(topico -> ResponseEntity.ok(new DetallarTopico(
                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFechaCreacion(),
                        topico.getStatus(),
                        topico.getAutor().getNombre(),
                        topico.getCurso().getNombre()
                )))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<DetallarTopicoActualizar> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizacionTopico datos){
        var topico = topicoRepository.findById(id)
                .orElseThrow(()-> new TopicoNoEncontrado("Topico no encontrado"));
        if (datos.titulo() != null) {
            topico.setTitulo(datos.titulo());
        }

        if (datos.mensaje() != null) {
            topico.setMensaje(datos.mensaje());
        }

        if (datos.estado() != null) {
            topico.setStatus(datos.estado());
        }

        if (datos.idautor() != null){
            var autor = usuarioRepository.findById(datos.idautor())
                    .orElseThrow(()-> new AutorNoEncontrado("el autor no fue encontrado"));
            topico.setAutor(autor);
        }

        if (datos.idcurso() != null){
            var curso = cursoRepository.findById(datos.idcurso())
                    .orElseThrow(()-> new CursoNoEncontrado("el curso no fue encontrado"));
            topico.setCurso(curso);
        }

           topicoRepository.save(topico);

           DetallarTopicoActualizar detalle = new DetallarTopicoActualizar(
                   topico.getTitulo(),
                   topico.getMensaje(),
                   topico.getStatus(),
                   topico.getAutor().getNombre(),
                   topico.getCurso().getNombre()
           );
           return ResponseEntity.ok(detalle);

    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        System.out.println("Eliminando tópico con id " + id);

        return ResponseEntity.notFound().build(); // 404 Not Found
    }



}
