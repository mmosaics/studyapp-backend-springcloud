package org.example.service;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hlr.constant.Constant;
import org.apache.dubbo.config.annotation.Reference;
import org.example.pojo.UserToExam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Service
public class AnswerService {

    @Reference
    QuestionDubboService questionDubboService;

    @Reference
    TestPaperService testPaperService;

    private UserToExamService userToExamService;

    @Autowired
    public AnswerService(UserToExamService userToExamService) {
        this.userToExamService = userToExamService;
    }

    public Map<String, Object> checkAnswerList(String examId, JSONObject jsonObject) {

        JSONArray jsonArray = jsonObject.getJSONArray(Constant.ANSWER_SRTING);

        Map<String, Object> result = new HashMap<>();

        Map<String, String> answers = new HashMap<>();

        String testId = userToExamService.getUserToExamById(examId).getExamId();

        List<Map<String, Object>> questionInfoList = testPaperService.getPartOfTestToQuestionByTestId(testId);


        for(Object object: jsonArray) {

            JSONObject answer = (JSONObject) object;

            Iterator<String> iterator = answers.keySet().iterator();

            while (iterator.hasNext()) {
                String key = iterator.next();
                String value = answer.getString(key);
                answers.put(key, value);
            }
        }

        for(Map<String, Object> mapIt: questionInfoList) {

            /*
             map.put("questionId", object[0]);
            map.put("type", object[1]);
            map.put("point", object[2]);
             */

            mapIt.put("corret", checkSingleAnswer((String)mapIt.get("questionId"), answers.get("questionId")));
            mapIt.put("youranswer", answers.get("questionId"));

        }

        result.put("result", questionInfoList);

        return result;

    }

    public Boolean checkSingleAnswer(String questionId, String answer) {

        Map<String, Object> map = questionDubboService.getFullQuestionInfo(questionId);

        //获取正确答案
        String rightAnswer = (String)map.get("rightAnswer");

        if(StrUtil.equalsIgnoreCase(rightAnswer, answer))
            return true;
        else
            return false;

    }



}
