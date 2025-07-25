package com.Challenge.ForoHub.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalException {


        @ExceptionHandler(AutorNoEncontrado.class)
        public ResponseEntity<Map<String, String>> handleAutorNoEncontrado(AutorNoEncontrado ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", ex.getMessage()));
        }

        @ExceptionHandler(CursoNoEncontrado.class)
        public ResponseEntity<Map<String, String>> handleCursoNoEncontrado(CursoNoEncontrado ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", ex.getMessage()));
        }

        @ExceptionHandler(TopicoNoEncontrado.class)
        public ResponseEntity<Map<String, String>> handleTopicoNoEncontrado(TopicoNoEncontrado ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", ex.getMessage()));
        }

        // Por si querés manejar cualquier otra excepción no contemplada
        @ExceptionHandler(Exception.class)
        public ResponseEntity<Map<String, String>> handleGeneral(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error interno del servidor"));
        }
}
