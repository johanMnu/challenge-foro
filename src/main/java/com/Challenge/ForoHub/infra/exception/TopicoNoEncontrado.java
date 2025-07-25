package com.Challenge.ForoHub.infra.exception;

public class TopicoNoEncontrado extends RuntimeException{
    public TopicoNoEncontrado(String mensaje) {
        super(mensaje);
    }
}
