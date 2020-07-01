package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "t_cc_usertohomework")
@Setter
@Getter
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@DynamicInsert
@DynamicUpdate
public class Homework {

    @Id
    @Column(name = "usertohomework_id")
    @JsonAlias(value = {"usertohomework_id"})
    private String usertohomeworkId = null;

    @Column(name = "user_id")
    @JsonAlias(value = {"user_id"})
    private String userId = null;

    @Column(name = "homework_exam_id")
    @JsonAlias(value = {"homework_exam_id"})
    private String homeworkExamId = null;

    @Column(name = "workexamname")
    @JsonAlias(value = {"workexamname"})
    private String workexamname = null;

    @Column(name = "usercourse_id")
    @JsonAlias(value = {"usercourse_id"})
    private String usercourseId = null;

    @Column(name = "course_id")
    @JsonAlias(value = {"course_id"})
    private String courseId = null;

    @Column(name = "chapter_parent_id")
    @JsonAlias(value = {"chapter_parent_id"})
    private String chapterParentId = null;

    @Column(name = "chapter_parent_name")
    @JsonAlias(value = {"chapter_parent_name"})
    private String chapterParentName = null;

    @Column(name = "chapter_id")
    @JsonAlias(value = {"chapter_id"})
    private String chapterId = null;

    @Column(name = "chapter_name")
    @JsonAlias(value = {"chapter_name"})
    private String chapterName = null;

    @Column(name = "type")
    private Integer type = null;

    @Column(name = "status")
    private Integer status = null;

    @Column(name = "answer", columnDefinition = "text")
    private String answer = null;

    @Column(name = "starttime")
    private Date starttime;

    @Column(name = "endtime")
    private Date endtime;

    @Column(name = "socre")
    private Integer score = null;

    @Column(name = "objectivescore")
    private Integer objectivescore = null;

    @Column(name = "rightcount")
    private Integer rightcount = null;

    @Column(name = "errorcount")
    private Integer errorcount = null;

    @Column(name = "attchmentpath")
    private String attchmentpath = null;

    @Column(name = "createtime")
    private Date createtime;

    @Column(name = "urlpath")
    private String urlpath = null;


}
