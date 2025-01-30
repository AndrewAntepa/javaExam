package org.skypro.JavaExam.exeption;

import org.springframework.http.HttpStatus;

public class TooManyQuestionsRequestException extends RuntimeException{
    private final HttpStatus status;
    public TooManyQuestionsRequestException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
