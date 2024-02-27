package com.cuonvc.todoapp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class APIException extends RuntimeException {
    public HttpStatus status;
    public String message;
}
