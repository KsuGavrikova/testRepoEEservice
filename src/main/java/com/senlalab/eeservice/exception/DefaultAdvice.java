package com.senlalab.eeservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class DefaultAdvice {

    @ExceptionHandler(MyRepoException.class)
    public ResponseEntity<Response> repositoryException(MyRepoException e) {
        Response response = new Response(e.getMessage());
        log.warn(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(EntryNotFoundException.class)
    public ResponseEntity<Response> entityNotFoundException(EntryNotFoundException e) {
        Response response = new Response(e.toString());
        log.warn(e.toString());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<Response> emptyListException(EmptyListException e) {
        Response response = new Response(e.toString());
        log.warn(e.toString());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
