package com.lucasmoraist.jornadamilhas.exceptions;

public class DestinyNotFound extends RuntimeException{

    public DestinyNotFound(){
        super("Destiny Not Found");
    }

    public DestinyNotFound(String message){
        super(message);
    }

}
