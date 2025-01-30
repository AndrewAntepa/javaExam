package org.skypro.JavaExam.controller;

import org.skypro.JavaExam.question.Question;
import org.skypro.JavaExam.service.ExaminerServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ExamController {
    public final ExaminerServiceImpl examinerService;

    public ExamController(ExaminerServiceImpl examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/exam/java/questions")
    public Collection<Question> getJavaQuestions(@RequestParam("amount") int amount) {
        return examinerService.getJavaQuestions(amount);
    }

    @GetMapping("/exam/math/questions")
    public Collection<Question> getMathQuestions(@RequestParam("amount") int amount) {
        return examinerService.getMathQuestions(amount);
    }
}
