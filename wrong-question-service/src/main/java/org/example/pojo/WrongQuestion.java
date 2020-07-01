package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.models.auth.In;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@DynamicUpdate
@DynamicInsert
@Table(name = "t_cc_userwrong")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Entity
public class WrongQuestion {

    @Id
    @GeneratedValue(generator = "uuidStrategy")
    @GenericGenerator(name = "uuidStrategy", strategy = "uuid")
    @Column(name = "userwrong_id", nullable = false, length = 32)
    private String userWrongId;

    @Column(name = "questions_id")
    private String questionId;

    @Column(name = "test_id")
    private String testId;

    @Column(name = "user_id")
    private String userId;

    /**
     * 错题状态 0-未重做 1-重做正确 2-重做错误
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 来源 1-考试 2-作业
     */
    @Column(name = "origin")
    private Integer origin;


    @Column(name = "createtime")
    private Date createTime;




}
