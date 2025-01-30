package org.skypro.JavaExam.controller;

import org.skypro.JavaExam.question.Question;
import org.skypro.JavaExam.service.MathQuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class MathQuestionController {
    private final MathQuestionService mathQuestionService;

    public MathQuestionController(MathQuestionService mathQuestionService) {
        this.mathQuestionService = mathQuestionService;
    }

    @GetMapping("/exam/math/add")
    public String addQuestion(@RequestParam("question") String QuestionText,
                              @RequestParam("answer") String  QuestionAnswer) {
        Question question = new Question(QuestionText, QuestionAnswer);
        if (mathQuestionService.getAll().contains(question)) {
            return "Такой примера уже есть";
        }
        mathQuestionService.add(QuestionText, QuestionAnswer);
        return "Пример добавлен";
    }

    @GetMapping("/exam/math/remove")
    public String removeQuestion(@RequestParam("question") String QuestionText,
                                 @RequestParam("answer") String  QuestionAnswer) {
        Question question = new Question(QuestionText, QuestionAnswer);
        if (mathQuestionService.getAll().contains(question)) {
            mathQuestionService.remove(new Question(QuestionText, QuestionAnswer));
            return "Пример удален";
        }
        return "Такого примера нет";
    }

    @GetMapping("/exam/math")
    public Collection<Question> getQuestions() {
        return mathQuestionService.getAll();
    }
}
