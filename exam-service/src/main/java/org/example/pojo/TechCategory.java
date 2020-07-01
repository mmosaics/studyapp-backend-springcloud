package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Entity
@Data
@DynamicUpdate
@DynamicInsert
@Table(name = "t_cc_techcategory")
public class TechCategory {

    @Id
    @GeneratedValue(generator = "uuidStrategy")
    @GenericGenerator(name = "uuidStrategy", strategy = "uuid")
    @Column(name = "techcategory_id", nullable = false, length = 32)
    private String techCategoryId;

    /**
     * 技术分类父类ID
     */
    @Column(name = "parent_id")
    private String parentId;


    @Column(name = "categoryname")
    private String categoryName;

    /**
     * 试题分类状态 0-可建子分类, 1-不可建子分类
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 试题分类状态 0-公有云同步分类， 1-私有云提交审核分类中， 2-私有云未提交审核分类
     */
    @Column(name = "check_status")
    private Integer check_status;

    @Lob
    @Column(name = "tech_point", columnDefinition = "text")
    private String techPoint;


    /**
     * 私有分类中从属公有云分类ID
     */
    @Column(name = "ocategory_id")
    private String ocategoryId;


    @Column(name = "createtime")
    private Date createTime;


}
