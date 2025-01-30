package org.skypro.JavaExam.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.skypro.JavaExam.exeption.JavaQuestionError;
import org.skypro.JavaExam.exeption.TooManyQuestionsRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExamAdvice {
    @ExceptionHandler(TooManyQuestionsRequestException.class)
    public ResponseEntity<JavaQuestionError> handleTooManyQuestionsRequestException(TooManyQuestionsRequestException ex, HttpServletRequest request) {
        JavaQuestionError error = new JavaQuestionError(ex.getStatus(), ex.getMessage(), request.getRequestURI());

        return new ResponseEntity<>(error, ex.getStatus());
    }
}
