package org.skypro.JavaExam.service;

import org.skypro.JavaExam.exception.TooManyQuestionsRequestException;
import org.skypro.JavaExam.interfaces.ExaminerService;
import org.skypro.JavaExam.interfaces.QuestionService;
import org.skypro.JavaExam.question.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final List<QuestionService> questionServices;
    private final int javaServiceId;
    private final int mathServiceId;

    public ExaminerServiceImpl(List<QuestionService> questionServices) {
        this.questionServices = questionServices;

        if (questionServices.get(0).getType().equals("java")) {
            javaServiceId = 0;
            mathServiceId = 1;
        } else {
            javaServiceId = 1;
            mathServiceId = 0;
        }
    }

    @Override
    public Collection<Question> getJavaQuestions(int amount) {
        List<Question> questions = new ArrayList<>();

        if (questionServices.get(javaServiceId).getAll().size() < amount) {
            throw new TooManyQuestionsRequestException("Запрошено слишком много вопросов");
        }

        for (int i = 0; i < amount; ) {
            Question question = questionServices.get(javaServiceId).getRandomQuestion();
            if (!questions.contains(question)) {
                questions.add(question);
                i++;
            }
        }
        return questions;
    }

    @Override
    public Collection<Question> getMathQuestions(int amount) {
        List<Question> questions = new ArrayList<>();

        for (int i = 0; i < amount; ) {
            Question question = questionServices.get(mathServiceId).getRandomQuestion();
            if (!questions.contains(question)) {
                questions.add(question);
                i++;
            }
        }
        return questions;
    }
}

