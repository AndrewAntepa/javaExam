package org.skypro.JavaExam.controller;

import org.skypro.JavaExam.question.Question;
import org.skypro.JavaExam.service.JavaQuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class JavaQuestionController {
    private final JavaQuestionService javaQuestionService;

    public JavaQuestionController(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @GetMapping("/exam/java/add")
    public String addQuestion(@RequestParam("question") String QuestionText,
                              @RequestParam("answer") String QuestionAnswer) {
        Question question = new Question(QuestionText, QuestionAnswer);
        if (javaQuestionService.getAll().contains(question)) {
            return "Такой вопрос уже есть";
        }
        javaQuestionService.add(QuestionText, QuestionAnswer);
        return "Вопрос добавлен";
    }

    @GetMapping("/exam/java/remove")
    public String removeQuestion(@RequestParam("question") String QuestionText,
                                 @RequestParam("answer") String QuestionAnswer) {
        Question question = new Question(QuestionText, QuestionAnswer);
        if (javaQuestionService.getAll().contains(question)) {
            javaQuestionService.remove(new Question(QuestionText, QuestionAnswer));
            return "Вопрос удален";
        }
        return "Такого вопроса нет";
    }

    @GetMapping("/exam/java")
    public Collection<Question> getQuestions() {
        return javaQuestionService.getAll();
    }
}
