package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Entity
@Data
@DynamicUpdate
@DynamicInsert
@Table(name = "t_cc_test")
public class Test implements Serializable {

    @Id
    @GeneratedValue(generator = "uuidStrategy")
    @GenericGenerator(name = "uuidStrategy", strategy = "uuid")
    @Column(name = "test_id", nullable = false, length = 32)
    private String testId;

    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "categoryname")
    private String categoryName;

    @Column(name = "course_catid")
    private String courseCategoryId;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "descrip", columnDefinition = "text")
    private String description;

    /**
     * 试卷试题
     */
    @Lob
    @Column(name = "testdata", columnDefinition = "text")
    private String testData;

    /**
     * status 0-不可用 1-可用
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 难度 0-简单 1-普通 2-困难
     */
    @Column(name = "diffic")
    private Integer diffic;

    @Column(name = "org_id")
    private String orgId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "answertime")
    private Integer answerTime;

    @Column(name = "passpoint")
    private Integer passPoint;

    @Column(name = "totalpoint")
    private Integer totalPoint;

    /**
     * 类型 1-试卷 2-作业， 默认1
     */
    @Column(name = "type")
    private Integer type = 1;

    @Column(name = "createtime")
    private Date createTime;



}
