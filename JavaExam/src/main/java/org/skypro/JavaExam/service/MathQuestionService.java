package org.skypro.JavaExam.service;

import org.skypro.JavaExam.exception.MathQuestionMethodNotAllowedException;
import org.skypro.JavaExam.interfaces.QuestionService;
import org.skypro.JavaExam.question.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Random;

@Service
public class MathQuestionService implements QuestionService {
    private char[] operators = {'+', '-', '*', '/'};

    public MathQuestionService() {
    }

    @Override
    public void add(Question question) {
        throw new MathQuestionMethodNotAllowedException("Method Not Allowed");
    }

    @Override
    public void add(String question, String  answer) {
        throw new MathQuestionMethodNotAllowedException("Method Not Allowed");
    }

    @Override
    public Collection<Question> getAll() {
        throw new MathQuestionMethodNotAllowedException("Method Not Allowed");
    }

    @Override
    public void remove(Question question) {
        throw new MathQuestionMethodNotAllowedException("Method Not Allowed");
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();

        int firstNumber = random.nextInt(100);
        int secondNumber = random.nextInt(100);
        char operator = operators[random.nextInt(operators.length)];

        String equation = String.valueOf(firstNumber) + operator + secondNumber;

        return new Question(equation, evalEquation(firstNumber, secondNumber, operator) + "");
    }

    @Override
    public String getType() {
        return "math";
    }

    private int evalEquation(int firstNumber, int secondNumber, char operator) {
        return switch (operator) {
            case '+' -> firstNumber + secondNumber;
            case '-' -> firstNumber - secondNumber;
            case '*' -> firstNumber * secondNumber;
            case '/' -> firstNumber / secondNumber;
            default -> 0;
        };
    }
}