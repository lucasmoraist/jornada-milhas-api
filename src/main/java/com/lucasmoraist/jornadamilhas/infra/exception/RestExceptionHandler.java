package com.lucasmoraist.jornadamilhas.infra.exception;

import com.lucasmoraist.jornadamilhas.exceptions.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionDTO> errorRegisterBody(ConstraintViolationException e){
        ExceptionDTO dto = new ExceptionDTO(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(dto);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDTO> nullPointer(DataIntegrityViolationException e){
        ExceptionDTO dto = new ExceptionDTO(e.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(dto);
    }

    @ExceptionHandler(DestinyNotFound.class)
    public ResponseEntity<ExceptionDTO> destinyNotFound(DestinyNotFound e){
        ExceptionDTO dto = new ExceptionDTO(e.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(dto);
    }

    @ExceptionHandler(TestimonialsNotFound.class)
    public ResponseEntity<ExceptionDTO> testimonialsNotFound(TestimonialsNotFound e){
        ExceptionDTO dto = new ExceptionDTO("Testimonials Not Found", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(dto);
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ExceptionDTO> userNotFound(UserNotFound e){
        ExceptionDTO dto = new ExceptionDTO("User Not Found", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(dto);
    }

    @ExceptionHandler(EmailDuplicate.class)
    public ResponseEntity<ExceptionDTO> emailDuplicate(EmailDuplicate e){
        ExceptionDTO dto = new ExceptionDTO(e.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(dto);
    }

    @ExceptionHandler(PasswordException.class)
    public ResponseEntity<ExceptionDTO> passwordIncorret(PasswordException e){
        ExceptionDTO dto = new ExceptionDTO("Senha incorreta", HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(dto);
    }
}
