package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@DynamicUpdate
@DynamicInsert
@Table(name = "t_cc_usercourse")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Entity
public class UserToCourseInfo {

    @Id
    @GeneratedValue(generator = "uuidStrategy")
    @GenericGenerator(name = "uuidStrategy", strategy = "uuid")
    @Column(name = "usercourse_id", nullable = false, length = 32)
    private String userCourseId;

    @Column(name = "course_id")
    private String courseId;

    @Column(name = "user_id")
    private String userId;

    @Lob
    @Column(name = "lastchapterpath", columnDefinition = "text")
    private String lastChapterPath;




}
