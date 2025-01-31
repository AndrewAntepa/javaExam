package org.skypro.JavaExam.service;

import org.skypro.JavaExam.interfaces.QuestionRepository;
import org.skypro.JavaExam.interfaces.QuestionService;
import org.skypro.JavaExam.question.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Primary
public class JavaQuestionService implements QuestionService {
    private final QuestionRepository javaQuestionRepository;

    public JavaQuestionService(@Qualifier("javaQuestionRepository") QuestionRepository javaQuestionRepository) {
        this.javaQuestionRepository = javaQuestionRepository;
    }

    @Override
    public void add(Question question) {
        javaQuestionRepository.add(question);
    }

    @Override
    public void add(String question, String answer) {
        javaQuestionRepository.add(new Question(question, answer));
    }

    @Override
    public Collection<Question> getAll() {
        return javaQuestionRepository.getAll();
    }

    @Override
    public void remove(Question question) {
        javaQuestionRepository.remove(question);
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        return javaQuestionRepository.getAll().toArray(new Question[0])[random.nextInt(javaQuestionRepository.getAll().size())];
    }

    @Override
    public String getType() {
        return "java";
    }
}
