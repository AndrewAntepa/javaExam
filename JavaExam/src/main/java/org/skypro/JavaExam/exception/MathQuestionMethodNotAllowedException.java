package org.skypro.JavaExam.exception;

import org.springframework.http.HttpStatus;

public class MathQuestionMethodNotAllowedException extends RuntimeException{
    private final HttpStatus status;
    public MathQuestionMethodNotAllowedException(String message) {
        super(message);
        this.status = HttpStatus.METHOD_NOT_ALLOWED;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
