package com.javanauta.usuario.infrastructure.exception;

public class ResourceNotFoudException extends RuntimeException{

    public ResourceNotFoudException (String mensagem){
        super(mensagem);
    }

    public ResourceNotFoudException (String mensagem,Throwable throwable){
        super(mensagem);
    }
}
