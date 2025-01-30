package org.skypro.JavaExam.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skypro.JavaExam.interfaces.QuestionRepository;
import org.skypro.JavaExam.question.Question;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MathQuestionServiceTest {
    @Mock
    private QuestionRepository mathQuestionRepository;

    @InjectMocks
    private MathQuestionService mathQuestionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddQuestion() {
        Question question = new Question("2+2", "4");
        mathQuestionService.add(question);
        verify(mathQuestionRepository, times(1)).add(question);
    }

    @Test
    void testAddQuestionWithStrings() {
        String questionText = "3+3";
        String answer = "6";
        mathQuestionService.add(questionText, answer);
        verify(mathQuestionRepository, times(1)).add(new Question(questionText, answer));
    }

    @Test
    void testGetAllQuestions() {
        Question question1 = new Question("2+2", "4");
        Question question2 = new Question("3+3", "6");
        when(mathQuestionRepository.getAll()).thenReturn(Arrays.asList(question1, question2));

        Collection<Question> questions = mathQuestionService.getAll();
        assertEquals(2, questions.size());
        assertTrue(questions.contains(question1));
        assertTrue(questions.contains(question2));
    }

    @Test
    void testRemoveQuestion() {
        Question question = new Question("2+2", "4");
        mathQuestionService.remove(question);
        verify(mathQuestionRepository, times(1)).remove(question);
    }

    @Test
    void testGetRandomQuestion() {
        Question question1 = new Question("What is 2+2?", "4");
        Question question2 = new Question("What is 3+3?", "6");
        when(mathQuestionRepository.getAll()).thenReturn(Arrays.asList(question1, question2));

        // Mocking Random to return a specific index
        Random random = mock(Random.class);
        when(random.nextInt(anyInt())).thenReturn(1); // Return index 1
        mathQuestionService = new MathQuestionService(mathQuestionRepository) {};

        Question randomQuestion = mathQuestionService.getRandomQuestion();
        assertEquals(question2, randomQuestion);
    }

    @Test
    void testGetRandomQuestionWhenNoQuestionsExist() {
        when(mathQuestionRepository.getAll()).thenReturn(Collections.emptyList());

        assertThrows(IllegalStateException.class, () -> mathQuestionService.getRandomQuestion());
    }
}
