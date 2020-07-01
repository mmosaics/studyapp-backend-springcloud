package org.example.service;


import java.util.Map;

public interface QuestionDubboService {

    Map<String, Object> getFullQuestionInfo(String questionId);

}
