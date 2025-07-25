package com.Challenge.ForoHub.service;

import com.Challenge.ForoHub.DTO.listaTopicos;
import com.Challenge.ForoHub.Repositorio.TopicoRepository;
import com.Challenge.ForoHub.model.Topico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;

    public Page<listaTopicos> listarTopicosActivos(Pageable paginacion) {
        return topicoRepository.findByActivoTrue(paginacion)
                .map(this::mapearADTO);
    }

    private listaTopicos mapearADTO(Topico topico) {
        return new listaTopicos(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}
