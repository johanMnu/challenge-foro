package com.Challenge.ForoHub.controller;

import com.Challenge.ForoHub.Repositorio.CursoRepository;
import com.Challenge.ForoHub.model.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public Curso crearCurso(@RequestBody Curso curso){
        return cursoRepository.save(curso);
    }
}
