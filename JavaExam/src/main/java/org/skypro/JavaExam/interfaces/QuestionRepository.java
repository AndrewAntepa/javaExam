package org.skypro.JavaExam.interfaces;

import org.skypro.JavaExam.question.Question;

import java.util.Collection;

public interface QuestionRepository {
    void add(Question question);
    void remove(Question question);
    Collection<Question> getAll();
}
