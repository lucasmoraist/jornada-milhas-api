package com.lucasmoraist.jornadamilhas.exceptions;

import org.springframework.http.HttpStatus;

public record ExceptionDTO(String message, HttpStatus status) {
}
