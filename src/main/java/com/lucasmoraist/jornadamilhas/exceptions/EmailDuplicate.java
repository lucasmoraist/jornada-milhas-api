package com.lucasmoraist.jornadamilhas.exceptions;

public class EmailDuplicate extends RuntimeException{

    public EmailDuplicate(){
        super("Este email já existe");
    }

    public EmailDuplicate(String message){
        super(message);
    }

}
