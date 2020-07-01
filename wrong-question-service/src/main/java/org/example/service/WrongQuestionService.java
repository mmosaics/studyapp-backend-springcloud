package org.example.service;

import com.hlr.constant.Constant;
import org.apache.dubbo.config.annotation.Reference;
import org.example.dao.WrongQuestionDAO;
import org.example.pojo.WrongQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WrongQuestionService {

    WrongQuestionDAO wrongQuestionDAO;

    @Reference
    AuthService authService;

    @Autowired
    public WrongQuestionService(WrongQuestionDAO wrongQuestionDAO) {
        this.wrongQuestionDAO = wrongQuestionDAO;
    }


    public Page<WrongQuestion> getWrongInfoByUserId(String sessionId, int start) {

        String userId = (String) authService.authUserInfo(sessionId).get(Constant.USER_ID_STRING);

        Sort sort = new Sort(Sort.Direction.DESC, "creatTime");

        Pageable pageable = PageRequest.of(start, 10, sort);

        return wrongQuestionDAO.findAllByUserId(userId, pageable);

    }


}
