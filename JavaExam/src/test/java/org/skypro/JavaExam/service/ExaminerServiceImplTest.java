package org.skypro.JavaExam.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.JavaExam.exeption.TooManyQuestionsRequestException;
import org.skypro.JavaExam.interfaces.QuestionService;
import org.skypro.JavaExam.question.Question;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private final Question question1 = new Question("Question 1?", "Answer 1");
    private final Question question2 = new Question("Question 2?", "Answer 2");
    private final Question question3 = new Question("Question 3?", "Answer 3");

    @BeforeEach
    void setUp() {
        when(questionService.getAll()).thenReturn(Set.of(question1, question2, question3));
    }

    @Test
    void getQuestions_ShouldReturnAllJavaQuestions() {
        Collection<Question> questions = examinerService.getJavaQuestions();
        assertEquals(3, questions.size());
        assertTrue(questions.containsAll(Arrays.asList(question1, question2, question3)));
    }

    @Test
    void createQuestions_ShouldReturnRequestedNumberOfQuestions() {
        when(questionService.getRandomQuestion()).thenReturn(question1, question2);

        Collection<Question> questions = examinerService.createJavaQuestions(2);

        assertEquals(2, questions.size());
        verify(questionService, atLeast(2)).getRandomQuestion();
    }

    @Test
    void createQuestions_ShouldThrowException_WhenNotEnoughQuestions() {
        when(questionService.getAll()).thenReturn(Set.of(question1));

        TooManyQuestionsRequestException exception = assertThrows(
                TooManyQuestionsRequestException.class,
                () -> examinerService.createJavaQuestions(2)
        );

        assertEquals("Слишком много вопросов", exception.getMessage());
    }

    @Test
    void createQuestions_ShouldNotReturnDuplicateQuestions() {
        when(questionService.getRandomQuestion()).thenReturn(question1, question1, question2);

        Collection<Question> questions = examinerService.createJavaQuestions(2);

        assertEquals(2, questions.size());
        assertTrue(questions.containsAll(List.of(question1, question2)));
    }
}
