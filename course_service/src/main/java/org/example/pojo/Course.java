package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@DynamicUpdate
@DynamicInsert
@Table(name = "t_cc_course")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Entity
public class Course {

    @Id
    @GeneratedValue(generator = "uuidStrategy")
    @GenericGenerator(name = "uuidStrategy", strategy = "uuid")
    @Column(name = "course_id", nullable = false, length = 32)
    private String courseId;

    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "coursename")
    private String courseName;

    @Lob
    @Column(name = "introduce", columnDefinition = "text")
    private String introduce;

    @Column(name = "createtime")
    private Date createTime;

    @Column(name = "teachername")
    private String teachername;

    @Lob
    @Column(name = "homework", columnDefinition = "text")
    private String homework;

    @Column(name = "isvip")
    private Integer isvip;

    @Lob
    @Column(name = "coursepic", columnDefinition = "text")
    private String coursePic;

    @Lob
    @Column(name = "resourcepath", columnDefinition = "text")
    private String resourcePath;

    @Transient
    private List<Chapter> chapters;



}
