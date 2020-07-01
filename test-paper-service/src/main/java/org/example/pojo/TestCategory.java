package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;


/**
 * 考试分类信息
 */
@DynamicUpdate
@DynamicInsert
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Entity
@Table(name = "t_cc_testcategory", schema = "chuanda", catalog = "")
public class TestCategory implements Serializable {
    private String testcategoryId;
    private String parentId;
    private String categoryname;
    private int sort;
    private Timestamp createtime;

    @Id
    @GeneratedValue(generator = "uuidStrategy")
    @GenericGenerator(name = "uuidStrategy", strategy = "uuid")
    @Column(name = "testcategory_id", length = 32)
    public String getTestcategoryId() {
        return testcategoryId;
    }

    public void setTestcategoryId(String testcategoryId) {
        this.testcategoryId = testcategoryId;
    }

    @Basic
    @Column(name = "parent_id")
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "categoryname")
    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    @Basic
    @Column(name = "sort")
    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
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
        TestCategory that = (TestCategory) o;
        return sort == that.sort &&
                Objects.equals(testcategoryId, that.testcategoryId) &&
                Objects.equals(parentId, that.parentId) &&
                Objects.equals(categoryname, that.categoryname) &&
                Objects.equals(createtime, that.createtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testcategoryId, parentId, categoryname, sort, createtime);
    }
}
