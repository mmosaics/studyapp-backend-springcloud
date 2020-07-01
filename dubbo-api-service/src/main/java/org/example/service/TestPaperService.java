package org.example.service;

import java.util.List;
import java.util.Map;

public interface TestPaperService {

    Map<String, Object> getTestByTestId(String testId);

    List<Map<String, Object>> getQuestionInfoListByTestId(String testId);

    Map<String, Object> getTestQuestionInfo(String testQuestionId);

    List<Map<String, Object>> getPartOfTestToQuestionByTestId(String testId);

}
