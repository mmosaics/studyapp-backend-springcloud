package org.example.controller;

import com.hlr.constant.Constant;
import com.hlr.utils.GlobalResult;
import org.example.pojo.WrongQuestion;
import org.example.service.WrongQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/wrong-question")
public class WrongQuestionController {

    WrongQuestionService wrongQuestionService;

    @Autowired
    public WrongQuestionController(WrongQuestionService wrongQuestionService) {
        this.wrongQuestionService = wrongQuestionService;
    }

    @GetMapping("/user-wrong/{start}")
    public GlobalResult getWrongQuestion(@PathVariable("start") int start, HttpServletRequest httpServletRequest) {

        String sessionId = httpServletRequest.getHeader(Constant.AUTH_HEADER);

        Page<WrongQuestion> wrongQuestionPage = wrongQuestionService.getWrongInfoByUserId(sessionId, start);

        return GlobalResult.ok(wrongQuestionPage);

    }


}
