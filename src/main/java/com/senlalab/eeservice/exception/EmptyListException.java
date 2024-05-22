package com.senlalab.eeservice.exception;

public class EmptyListException extends RuntimeException {
    public EmptyListException() {
    }

    public EmptyListException(String message) {
        super(message);
    }

    public String toString() {
        return "The list is empty";
    }
}
