package com.lucasmoraist.jornadamilhas.exceptions;

public class TestimonialsNotFound extends RuntimeException{

    public TestimonialsNotFound(){
        super("Testimonials Not Found");
    }

    public TestimonialsNotFound(String message){
        super(message);
    }

}
