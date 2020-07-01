package org.example.controller;

import com.hlr.utils.GlobalResult;
import org.example.pojo.Question;
import org.example.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }


    @GetMapping("/no-answer/{questionId}")
    public GlobalResult getQuestionWithoutAnswer(@PathVariable("questionId") String questionId) {
        Question question =  questionService.getQuestionWithoutAnswer(questionId);
        return GlobalResult.ok(question);
    }

    @GetMapping("/answer/{questionId}")
    public GlobalResult getQuestionWithAnswer(@PathVariable("questionId") String questionId) {
        Question question = questionService.getQuestionWithAnswer(questionId);
        return GlobalResult.ok(question);
    }




}
