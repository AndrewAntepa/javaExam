package org.skypro.JavaExam.service;

import org.skypro.JavaExam.exception.TooManyQuestionsRequestException;
import org.skypro.JavaExam.interfaces.ExaminerService;
import org.skypro.JavaExam.interfaces.QuestionService;
import org.skypro.JavaExam.question.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService javaQuestionService;
    private final MathQuestionService mathQuestionService;

    public ExaminerServiceImpl(@Qualifier("javaQuestionService") QuestionService javaQuestionService, @Qualifier("mathQuestionService") MathQuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public Collection<Question> getJavaQuestions() {
        return javaQuestionService.getAll();
    }

    @Override
    public Collection<Question> getMathQuestions() {
        return mathQuestionService.getAll();
    }

    public Collection<Question> createJavaQuestions(int amount) {
        List<Question> questions = new ArrayList<>();
        if (javaQuestionService.getAll().size() < amount) {
            throw new TooManyQuestionsRequestException("Запрошено слишком много вопросов");
        }

        for (int i = 0; i < amount; ) {
            Question question = javaQuestionService.getRandomQuestion();
            if (!questions.contains(question)) {
                questions.add(question);
                i++;
            }
        }
        return questions;
    }

    public Collection<Question> createMathQuestions(int amount) {
        List<Question> questions = new ArrayList<>();

        for (int i = 0; i < amount; ) {
            Question question = mathQuestionService.getRandomQuestion();
            if (!questions.contains(question)) {
                questions.add(question);
                i++;
            }
        }
        return questions;
    }
}
