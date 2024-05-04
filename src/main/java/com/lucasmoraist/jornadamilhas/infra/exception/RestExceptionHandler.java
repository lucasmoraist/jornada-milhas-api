package com.lucasmoraist.jornadamilhas.infra.exception;

import com.lucasmoraist.jornadamilhas.exceptions.DestinyNotFound;
import com.lucasmoraist.jornadamilhas.exceptions.ExceptionDTO;
import com.lucasmoraist.jornadamilhas.exceptions.TestimonialsNotFound;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<ExceptionDTO> nullPointer(DataIntegrityViolationException e){
        ExceptionDTO dto = new ExceptionDTO(e.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(dto);
    }

    @ExceptionHandler(DestinyNotFound.class)
    private ResponseEntity<ExceptionDTO> destinyNotFound(DestinyNotFound e){
        ExceptionDTO dto = new ExceptionDTO(e.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(dto);
    }

    @ExceptionHandler(TestimonialsNotFound.class)
    private ResponseEntity<ExceptionDTO> testimonialsNotFound(TestimonialsNotFound e){
        ExceptionDTO dto = new ExceptionDTO("Testimonials Not Found", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(dto);
    }

}
