package io.github.gabrielnavas.url_shortener.Link;

import io.github.gabrielnavas.url_shortener.Exception.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class LinkRestExceptionHandler {
    @ExceptionHandler(LinkAlreadyExistsException.class)
    public ResponseEntity<ApiError> linkAlreadyExists(LinkAlreadyExistsException ex) {
        ApiError apiError = ApiError
                .builder()
                .errors(List.of(ex.getMessage()))
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LinkAlreadyExpiredAtException.class)
    public ResponseEntity<ApiError> linkAlreadyExpiredAt(LinkAlreadyExpiredAtException ex) {
        ApiError apiError = ApiError
                .builder()
                .errors(List.of(ex.getMessage()))
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LinkNotFoundException.class)
    public ResponseEntity<ApiError> linkNotFound(LinkNotFoundException ex) {
        ApiError apiError = ApiError
                .builder()
                .errors(List.of(ex.getMessage()))
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LinkIsNotValidException.class)
    public ResponseEntity<ApiError> linkNotFound(LinkIsNotValidException ex) {
        ApiError apiError = ApiError
                .builder()
                .errors(List.of(ex.getMessage()))
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
