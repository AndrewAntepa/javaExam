package org.skypro.JavaExam.interfaces;

import org.skypro.JavaExam.question.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getJavaQuestions(int amount);
    Collection<Question> getMathQuestions(int amount);
}
