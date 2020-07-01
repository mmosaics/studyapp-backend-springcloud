package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@DynamicUpdate
@DynamicInsert
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Entity
@Table(name = "t_cc_usertoexam", schema = "chuanda", catalog = "")
public class UserToExam {
    private String usertoexamId;
    private String userId;
    private String examId;
    private String arrangetestId;
    private int status;
    private String answer;
    private Timestamp starttime;
    private Timestamp endtime;
    private Integer socre;
    private Integer objectivescore;
    private Integer rightcount;
    private Integer errorcount;
    private Timestamp createtime;

    @Id
    @GeneratedValue(generator = "uuidStrategy")
    @GenericGenerator(name = "uuidStrategy", strategy = "uuid")
    @Column(name = "usertoexam_id", length = 32)
    public String getUsertoexamId() {
        return usertoexamId;
    }

    public void setUsertoexamId(String usertoexamId) {
        this.usertoexamId = usertoexamId;
    }

    @Basic
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "exam_id")
    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    @Basic
    @Column(name = "arrangetest_id")
    public String getArrangetestId() {
        return arrangetestId;
    }

    public void setArrangetestId(String arrangetestId) {
        this.arrangetestId = arrangetestId;
    }

    @Basic
    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "answer")
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Basic
    @Column(name = "starttime")
    public Timestamp getStarttime() {
        return starttime;
    }

    public void setStarttime(Timestamp starttime) {
        this.starttime = starttime;
    }

    @Basic
    @Column(name = "endtime")
    public Timestamp getEndtime() {
        return endtime;
    }

    public void setEndtime(Timestamp endtime) {
        this.endtime = endtime;
    }

    @Basic
    @Column(name = "socre")
    public Integer getSocre() {
        return socre;
    }

    public void setSocre(Integer socre) {
        this.socre = socre;
    }

    @Basic
    @Column(name = "objectivescore")
    public Integer getObjectivescore() {
        return objectivescore;
    }

    public void setObjectivescore(Integer objectivescore) {
        this.objectivescore = objectivescore;
    }

    @Basic
    @Column(name = "rightcount")
    public Integer getRightcount() {
        return rightcount;
    }

    public void setRightcount(Integer rightcount) {
        this.rightcount = rightcount;
    }

    @Basic
    @Column(name = "errorcount")
    public Integer getErrorcount() {
        return errorcount;
    }

    public void setErrorcount(Integer errorcount) {
        this.errorcount = errorcount;
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
        UserToExam that = (UserToExam) o;
        return status == that.status &&
                Objects.equals(usertoexamId, that.usertoexamId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(examId, that.examId) &&
                Objects.equals(arrangetestId, that.arrangetestId) &&
                Objects.equals(answer, that.answer) &&
                Objects.equals(starttime, that.starttime) &&
                Objects.equals(endtime, that.endtime) &&
                Objects.equals(socre, that.socre) &&
                Objects.equals(objectivescore, that.objectivescore) &&
                Objects.equals(rightcount, that.rightcount) &&
                Objects.equals(errorcount, that.errorcount) &&
                Objects.equals(createtime, that.createtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usertoexamId, userId, examId, arrangetestId, status, answer, starttime, endtime, socre, objectivescore, rightcount, errorcount, createtime);
    }
}
