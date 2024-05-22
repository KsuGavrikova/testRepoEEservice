package com.senlalab.eeservice.exception;

public class MyRepoException extends RuntimeException {

    public MyRepoException(String message) {
        super(message) ;
    }

    public MyRepoException(Throwable course) {
        super(course);
    }
}

