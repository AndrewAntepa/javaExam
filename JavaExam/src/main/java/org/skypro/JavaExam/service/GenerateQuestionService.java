package org.skypro.JavaExam.service;

import org.skypro.JavaExam.exception.TooManyQuestionsRequestException;
import org.skypro.JavaExam.question.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class GenerateQuestionService {
    private final MathQuestionService mathQuestionService;
    private final JavaQuestionService javaQuestionService;

    public GenerateQuestionService(MathQuestionService mathQuestionService, JavaQuestionService javaQuestionService) {
        this.mathQuestionService = mathQuestionService;
        this.javaQuestionService = javaQuestionService;
    }

    public Collection<Question> generateJavaQuestions(int amount) {
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

    public Collection<Question> generateMathQuestions(int amount) {
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
