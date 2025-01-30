package org.skypro.JavaExam.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.JavaExam.question.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JavaQuestionServiceTest {
    private JavaQuestionService javaQuestionService;
    private List<Question> mockQuestions;

    @BeforeEach
    void setUp() {
        mockQuestions = new ArrayList<>();
//        javaQuestionService = new JavaQuestionService(mockQuestions);
    }

    @Test
    void testAddQuestion() {
        Question question = new Question("Что такое java?", "Язык программирования");
        javaQuestionService.add(question);

        assertTrue(mockQuestions.contains(question));
    }

    @Test
    void testAddDuplicateQuestion() {
        Question question = new Question("Что такое java?", "Язык программирования");
        javaQuestionService.add(question);
        int sizeBefore = mockQuestions.size();
        javaQuestionService.add(question);

        System.out.println(Arrays.toString(mockQuestions.toArray()));

        assertEquals(sizeBefore, mockQuestions.size());
    }

    @Test
    void testAddQuestionByString() {
        javaQuestionService.add("Что такое ооп?", "Объектно-ориентированное программирование");
        assertTrue(mockQuestions.contains(new Question("Что такое ооп?", "Объектно-ориентированное программирование")));
    }

    @Test
    void testRemoveExistingQuestion() {
        Question question = new Question("Что такое java?", "Язык программирования");
        javaQuestionService.add(question);
        javaQuestionService.remove(question);

        assertFalse(mockQuestions.contains(question));
    }

    @Test
    void testRemoveNonExistingQuestionThrowsException() {
        Question question = new Question("нет вопроса", "нет ответа");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> javaQuestionService.remove(question));
        assertEquals("Такого вопроса нет", exception.getMessage());
    }

    @Test
    void testGetAllQuestions() {
        Question q1 = new Question("Что такое java?", "Язык программирования");
        Question q2 = new Question("Что такое ооп?", "Объектно-ориентированное программирование");

        int sizeBefore = mockQuestions.size();

        javaQuestionService.add(q1);
        javaQuestionService.add(q2);

        assertEquals(sizeBefore + 2, javaQuestionService.getAll().size());
        assertTrue(javaQuestionService.getAll().contains(q1));
        assertTrue(javaQuestionService.getAll().contains(q2));
    }

    @Test
    void testGetRandomQuestion() {
        assertNotNull(javaQuestionService.getRandomQuestion());
    }
}
