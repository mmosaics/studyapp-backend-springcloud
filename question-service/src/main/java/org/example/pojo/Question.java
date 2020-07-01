package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@DynamicUpdate
@DynamicInsert
@Table(name = "t_cc_questions")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Entity
public class Question {

    @Id
    @GeneratedValue(generator = "uuidStrategy")
    @GenericGenerator(name = "uuidStrategy", strategy = "uuid")
    @Column(name = "questions_id", nullable = false, length = 32)
    private String questionId;

    @Column(name = "type")
    private Integer type;

    //技术分类ID
    @Column(name = "direction_id")
    private String directionId;

    /**
     * 试题状态。 0-试题更新 1-本地试题
     */
    @Column(name = "status")
    private Integer status = 0;

    /**
     * 难度 0-简单 1-普通 2-困难
     */
    @Column(name = "diffic")
    private Integer difficult;


    /**
     * 是否被占用。 0-没占用 1-占用 默认0
     */
    @Column(name = "isused")
    private Integer isUsed = 0;

    /**
     * 试题内容
     */
    @Lob
    @Column(name = "content", columnDefinition = "text")
    private String content;

    /**
     * 提供的可选答案XML格式
     */
    @Lob
    @Column(name = "options", columnDefinition = "text")
    private String options;


    /**
     * 参考答案
     */
    @Lob
    @Column(name = "answer", columnDefinition = "text")
    private String answer;

    /**
     * 试题解析
     */
    @Lob
    @Column(name = "analyzes", columnDefinition = "text")
    private String analyze;


    /**
     * 试题创建者编号
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * techPoint
     */
    @Lob
    @Column(name = "techpoint", columnDefinition = "text")
    private String techPoint;

    /**
     * 更新时间
     */
    @Column(name = "updatetime")
    private Date updateTime;

    /**
     * 创建试题的时间
     */
    @Column(name = "createtime")
    private Date createTime;



}
