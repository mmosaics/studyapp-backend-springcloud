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
@Table(name = "t_cc_chapter")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Entity
public class Chapter {

    @Id
    @GeneratedValue(generator = "uuidStrategy")
    @GenericGenerator(name = "uuidStrategy", strategy = "uuid")
    @Column(name = "chapter_id", nullable = false, length = 32)
    private String chapterId;

    @Column(name = "course_id")
    private String courseId;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "previous_id")
    private String previousId;

    @Column(name = "type")
    private String type;

    @Column(name = "chaptername")
    private String chapterName;

    @Lob
    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Lob
    @Column(name = "linkurl", columnDefinition = "text")
    private String linkUrl;

    @Lob
    @Column(name = "resourcepath", columnDefinition = "text")
    private String resourcePath;

    @Lob
    @Column(name = "realvideourl", columnDefinition = "text")
    private String realVideoPath;

    @Column(name = "createtime")
    private Date createTime;



}
