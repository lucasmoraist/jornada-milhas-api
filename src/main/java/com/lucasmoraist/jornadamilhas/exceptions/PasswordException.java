package com.lucasmoraist.jornadamilhas.exceptions;

public class PasswordException extends RuntimeException{

    public PasswordException(){
        super("Senha incorreta");
    }

    public PasswordException(String message){
        super(message);
    }

}
