package org.skypro.JavaExam.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.JavaExam.exception.TooManyQuestionsRequestException;
import org.skypro.JavaExam.question.Question;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    @Mock
    private GenerateQuestionService generateQuestion;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetJavaQuestions() {
        int amount = 5;
        Collection<Question> actualQuestions = examinerService.getJavaQuestions(amount);

        assertEquals(generateQuestion.generateJavaQuestions(amount).size(), actualQuestions.size());
        verify(generateQuestion).generateJavaQuestions(amount);
    }

    @Test
    public void testGetTooManyJavaQuestions() {
        int amount = 15;
        Collection<Question> actualQuestions = generateQuestion.generateJavaQuestions(amount);

        assertThrows(TooManyQuestionsRequestException.class, () -> generateQuestion.generateJavaQuestions(amount));
        assertEquals(generateQuestion.generateJavaQuestions(amount).size(), actualQuestions.size());
        verify(generateQuestion).generateJavaQuestions(amount);
    }

    @Test
    public void testGetMathQuestions() {
        int amount = 3;

        Collection<Question> actualQuestions = examinerService.getMathQuestions(amount);

        assertEquals(generateQuestion.generateMathQuestions(amount).size(), actualQuestions.size());
        verify(generateQuestion).generateMathQuestions(amount);
    }
}
