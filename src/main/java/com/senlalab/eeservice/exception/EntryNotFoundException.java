package com.senlalab.eeservice.exception;

public class EntryNotFoundException extends RuntimeException {

    private String message;

    private Long id;

    public EntryNotFoundException() {
        super();
    }

    public EntryNotFoundException(String message) {
        this.message = message;
    }

    public EntryNotFoundException(String message, Long id) {
        this.message = message;
        this.id = id;
    }

    public String toString() {
        return message + " entity with id " + id + " not found";
    }
}
