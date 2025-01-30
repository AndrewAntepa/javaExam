package org.skypro.JavaExam.service;

import org.skypro.JavaExam.interfaces.QuestionRepository;
import org.skypro.JavaExam.interfaces.QuestionService;
import org.skypro.JavaExam.question.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Random;

@Service
public class MathQuestionService implements QuestionService {
    private final QuestionRepository mathQuestionRepository;

    public MathQuestionService(@Qualifier("mathQuestionRepository") QuestionRepository mathQuestionRepository) {
        this.mathQuestionRepository = mathQuestionRepository;
    }

    @Override
    public void add(Question question) {
        mathQuestionRepository.add(question);
    }

    @Override
    public void add(String question, String  answer) {
        mathQuestionRepository.add(new Question(question, answer));
    }

    @Override
    public Collection<Question> getAll() {
        return mathQuestionRepository.getAll();
    }

    @Override
    public void remove(Question question) {
        mathQuestionRepository.remove(question);
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        return mathQuestionRepository.getAll().toArray(new Question[0])[random.nextInt(mathQuestionRepository.getAll().size())];
    }
}