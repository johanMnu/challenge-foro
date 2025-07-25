package com.Challenge.ForoHub.infra.exception;

public class CursoNoEncontrado extends RuntimeException{
    public CursoNoEncontrado(String mensaje) {
        super(mensaje);
    }
}
