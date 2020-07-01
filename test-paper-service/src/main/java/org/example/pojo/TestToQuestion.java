package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@DynamicUpdate
@DynamicInsert
@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Table(name = "t_cc_testquestion", schema = "chuanda", catalog = "")
public class TestToQuestion implements Serializable {
    private String testquestionId;
    private String testId;
    private String questionId;
    private Integer type;
    private Integer point;
    private Timestamp createtime;

    @Id
    @GeneratedValue(generator = "uuidStrategy")
    @GenericGenerator(name = "uuidStrategy", strategy = "uuid")
    @Column(name = "testquestion_id", length = 32)
    public String getTestquestionId() {
        return testquestionId;
    }

    public void setTestquestionId(String testquestionId) {
        this.testquestionId = testquestionId;
    }

    @Basic
    @Column(name = "test_id")
    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    @Basic
    @Column(name = "question_id")
    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "point")
    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    @Basic
    @Column(name = "createtime")
    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestToQuestion that = (TestToQuestion) o;
        return Objects.equals(testquestionId, that.testquestionId) &&
                Objects.equals(testId, that.testId) &&
                Objects.equals(questionId, that.questionId) &&
                Objects.equals(type, that.type) &&
                Objects.equals(point, that.point) &&
                Objects.equals(createtime, that.createtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testquestionId, testId, questionId, type, point, createtime);
    }
}
