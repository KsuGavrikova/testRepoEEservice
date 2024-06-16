package com.senlalab.eeservice.exception;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static com.senlalab.eeservice.util.constants.ErrorMessageConstants.INTERNAL_SERVER_ERROR_MESSAGE;
import static com.senlalab.eeservice.util.constants.ErrorMessageConstants.INVALID_DATA_MESSAGE;
import static com.senlalab.eeservice.util.constants.ErrorMessageConstants.INVALID_DATA_TYPE_IN;
import static com.senlalab.eeservice.util.constants.ErrorMessageConstants.METHOD_ARGUMENTS_NOT_VALID_MESSAGE;

@Slf4j
@RestControllerAdvice
public class DefaultAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleInternalServerError(Exception exception) {
        return handleException(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MESSAGE, exception);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Response> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        String parameterName = exception.getName();
        return handleException(HttpStatus.BAD_REQUEST, String.format(INVALID_DATA_TYPE_IN, parameterName), exception);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Response> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return handleException(HttpStatus.BAD_REQUEST, INVALID_DATA_MESSAGE, exception);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Response> handleValidationExceptions(MethodArgumentNotValidException exception) {
        String errorMessage = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .findAny()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse(METHOD_ARGUMENTS_NOT_VALID_MESSAGE);
        return handleException(HttpStatus.BAD_REQUEST, errorMessage, exception);
    }

    private ResponseEntity<Response> handleException(HttpStatus status, String errorMessage, Exception exception) {
        log.error("{}: {}", exception.getClass().getSimpleName(), exception.getMessage());
        return ResponseEntity.status(status).body(new Response(errorMessage));
    }

    @ExceptionHandler(InvalidDefinitionException.class)
    public ResponseEntity<Response> validateException(InvalidDefinitionException e) {
        Response response = new Response(e.getMessage());
        log.error(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MyRepoException.class)
    public ResponseEntity<Response> repositoryException(MyRepoException e) {
        Response response = new Response(e.getMessage());
        log.error(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntryNotFoundException.class)
    public ResponseEntity<Response> entityNotFoundException(EntryNotFoundException e) {
        Response response = new Response(e.toString());
        log.error(e.toString());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<Response> emptyListException(EmptyListException e) {
        Response response = new Response(e.toString());
        log.error(e.toString());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
