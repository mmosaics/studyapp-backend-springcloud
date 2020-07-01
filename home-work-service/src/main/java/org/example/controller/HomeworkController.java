package org.example.controller;

import com.hlr.constant.Constant;
import com.hlr.utils.GlobalResult;
import org.example.pojo.Homework;
import org.example.service.HomeworkService;
import org.example.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/homework")
public class HomeworkController {

    private HomeworkService homeworkService;
    private UserInfoService userInfoService;

    @Autowired
    public HomeworkController(HomeworkService homeworkService, UserInfoService userInfoService) {
        this.homeworkService = homeworkService;
        this.userInfoService = userInfoService;
    }


    @GetMapping("/user-homework/{start}")
    public GlobalResult getHomeworkByUserId(@PathVariable("start") int start,
                                            HttpServletRequest httpServletRequest) {
        String sessionId = httpServletRequest.getHeader(Constant.AUTH_HEADER);

        String userId = userInfoService.getUserIdBySession(sessionId);

        Page<Homework> homework = homeworkService.getAllHomeworksByUserId(userId, start);

        return GlobalResult.ok(homework);

    }

    @GetMapping("/{homeworkId}")
    public GlobalResult getHomework(@PathVariable("homeworkId") String homeworkId) {

        return GlobalResult.ok(homeworkService.getHomeworkTest(homeworkId));

    }


}
