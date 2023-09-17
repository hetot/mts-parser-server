package com.example.mtstestproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No updates are found")
public class NoUpdateFoundException extends Exception {
    public NoUpdateFoundException(String notFound) {
        super(notFound);
    }
}
