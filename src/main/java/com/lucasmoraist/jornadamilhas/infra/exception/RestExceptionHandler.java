package com.lucasmoraist.jornadamilhas.infra.exception;

import com.lucasmoraist.jornadamilhas.exceptions.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    private ResponseEntity<ExceptionDTO> errorRegisterBody(ConstraintViolationException e){
        ExceptionDTO dto = new ExceptionDTO(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(dto);
    }

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

    @ExceptionHandler(UserNotFound.class)
    private ResponseEntity<ExceptionDTO> userNotFound(UserNotFound e){
        ExceptionDTO dto = new ExceptionDTO("User Not Found", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(dto);
    }

    @ExceptionHandler(EmailDuplicate.class)
    private ResponseEntity<ExceptionDTO> emailDuplicate(EmailDuplicate e){
        ExceptionDTO dto = new ExceptionDTO(e.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(dto);
    }

    @ExceptionHandler(PasswordException.class)
    private ResponseEntity<ExceptionDTO> passwordIncorret(PasswordException e){
        ExceptionDTO dto = new ExceptionDTO("Senha incorreta", HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(dto);
    }
}
