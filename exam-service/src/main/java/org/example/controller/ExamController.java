package org.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.hlr.constant.Constant;
import com.sun.tools.internal.jxc.ap.Const;
import org.example.pojo.UserToExam;
import org.example.service.AnswerService;
import org.example.service.UserInfoService;
import org.example.service.UserToExamService;
import org.example.util.GlobalResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/exams")
public class ExamController {

    private UserToExamService userToExamService;

    private UserInfoService userInfoService;

    private AnswerService answerService;

    @Autowired
    public ExamController(UserToExamService userToExamService, UserInfoService userInfoService, AnswerService answerService) {
        this.userToExamService = userToExamService;
        this.userInfoService = userInfoService;
        this.answerService = answerService;
    }

    @GetMapping("/user-exam/{start}")
    public GlobalResult getExams(@PathVariable("start") int start, HttpServletRequest request) {

        String session = request.getHeader(Constant.AUTH_HEADER);

        String userId = userInfoService.getUserIdBySession(session);

        if(userId == null) {
            return GlobalResult.errorMsg("wrong user");
        }

        Page<UserToExam> exams = userToExamService.getExamsByUserId(userId, start);

        return GlobalResult.ok(exams);

    }

    @PostMapping("/{exam_id}/answer")
    public GlobalResult upLoadAnswer(@PathVariable("exam_id") String examId, @RequestBody JSONObject jsonObject) {

        GlobalResult result = GlobalResult.ok(answerService.checkAnswerList(examId, jsonObject));

        return result;


    }


    @GetMapping("/test")
    public GlobalResult test() {
        return GlobalResult.ok("test");
    }


}
