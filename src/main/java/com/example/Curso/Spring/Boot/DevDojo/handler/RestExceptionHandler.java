package com.example.Curso.Spring.Boot.DevDojo.handler;

import com.example.Curso.Spring.Boot.DevDojo.error.ErrorDetail;
import com.example.Curso.Spring.Boot.DevDojo.error.ResourceNotFoundDetails;
import com.example.Curso.Spring.Boot.DevDojo.error.ResourceNotFoundException;
import com.example.Curso.Spring.Boot.DevDojo.error.ValidationExceptionError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> HandleResourceNotFoundException(ResourceNotFoundException foundException) {
        ResourceNotFoundDetails builder = ResourceNotFoundDetails.Builder.newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Recurso nao encontrado ")
                .detail(foundException.getMessage()).
                developerMessage(foundException.getClass().getName()).
                build();
        return new ResponseEntity<>(builder, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException validException,
                                                               HttpHeaders headers,
                                                               HttpStatus status,
                                                               WebRequest request) {
        List<FieldError> fieldErrors = validException.getBindingResult().getFieldErrors();
        String fieldMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
        String field = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
        ValidationExceptionError exceptionError = ValidationExceptionError.
                Builder.
                newBuilder().timestamp(new Date().getTime()).
                status(HttpStatus.NOT_FOUND.value())
                .detail("Campos imcompletos ou vazios")
                .title("Problema ao validar o usuario ")
                .fieldMessage(fieldMessage)
                .field(field).
                build();

        return new ResponseEntity<>(exceptionError, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception foundException, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetail errorDetail = ResourceNotFoundDetails.Builder.newBuilder()
                .timestamp(new Date().getTime())
                .status(status.value())
                .title("Internal Exception ")
                .detail(foundException.getMessage()).
                developerMessage(foundException.getClass().getName()).
                build();

        return new ResponseEntity<>(errorDetail, headers, status);
    }
}
