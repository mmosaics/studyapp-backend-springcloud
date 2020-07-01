package org.example.service;

import org.apache.dubbo.config.annotation.Reference;
import org.example.dao.HomeworkDAO;
import org.example.pojo.Homework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HomeworkService {
    private HomeworkDAO homeworkDAO;
    //private TestService testService;

    @Autowired
    public HomeworkService(HomeworkDAO homeworkDAO) {
        this.homeworkDAO = homeworkDAO;
     //   this.testService = testService;
    }

    @Reference
    TestPaperService testPaperService;

    //更新用户
    public void update(Homework homework) {
        this.homeworkDAO.save(homework);
    }

    //根据UsertohomeworkId找到Homework
    public Homework getHomeworkByUsertohomeworkId(String usertohomeworkId) {
        return this.homeworkDAO.findByUsertohomeworkId(usertohomeworkId);
    }

    //返回用户json，这里现在只能返回整个用户的json，还需要用hashmap细化, modify
    public Map<String, Object> getHomeworkTest(String usertohomeworkId) {

        Homework homework = this.getHomeworkByUsertohomeworkId(usertohomeworkId);

        String examId = homework.getHomeworkExamId();

        Map<String, Object> test = testPaperService.getTestByTestId(examId);

        return test;

    }


    //获取分页的作业
    public Page<Homework> getAllHomeworksByUserId(String userID,int start){
        Sort sort = new Sort(Sort.Direction.DESC,"createtime");
        Pageable pageable = new PageRequest(start,10,sort);
        return this.homeworkDAO.findAllByUserId(userID,pageable);
    }

}
