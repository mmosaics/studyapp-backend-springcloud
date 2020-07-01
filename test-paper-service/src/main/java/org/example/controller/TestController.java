package org.example.controller;

import com.hlr.utils.GlobalResult;
import org.example.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/test-sheet")
public class TestController {

    private TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/{testId}")
    public GlobalResult getTestSheetByTestId(@PathVariable("testId") String testId) {

        Map<String, Object> map = testService.getTestByTestId(testId);

        return GlobalResult.ok(map);

    }
}
