package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "t_uc_user")
@Setter
@Getter
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@DynamicInsert
@DynamicUpdate
public class User {

    @Id
    @Column(name = "user_id")
    private String userId = null;

    @Column(name = "userwallet")
    private String userwallet = null;

    @Column(name = "usercredits")
    private Integer usercredits = null;

    @Column(name = "school")
    @JsonAlias(value = {"school"})
    private String school = null;

    @Column(name = "depart_id")
    @JsonAlias(value = {"depart_id"})
    private String departId = null;

    @Column(name = "ispublicdept")
    @JsonAlias(value = {"ispublicdept"})
    private Integer ispublicdept = null;

    @Column(name = "org_id")
    @JsonAlias(value = {"org_id"})
    private String orgId = null;

    @Column(name = "ispublicorg")
    @JsonAlias(value = {"ispublicorg"})
    private Integer ispublicorg = null;

    @Column(name = "post_id")
    private String postId = null;

    @Column(name = "ispublicpost")
    @JsonAlias(value = {"ispublicpost"})
    private Integer ispublicpost = null;

    @Column(name = "mobilephone")
    @JsonAlias(value = {"mobilephone"})
    private String mobilephone = null;

    @Column(name = "email")
    @JsonAlias(value = {"email"})
    private String email = null;

    @Column(name = "isverityemail")
    @JsonAlias(value = {"isverityemail"})
    private Integer isverityemail = null;

    @Column(name = "ispublicemail")
    @JsonAlias(value = {"ispublicemail"})
    private Integer ispublicemail = null;

    @Column(name = "address")
    @JsonAlias(value = {"address"})
    private String address = null;

    @Column(name = "ispublicaddress")
    @JsonAlias(value = {"ispublicaddress"})
    private Integer ispublicaddress = null;

    @Column(name = "nick")
    @JsonAlias(value = {"nick"})
    private String nick = null;

    @Column(name = "name")
    @JsonAlias(value = {"name"})
    private String name = null;

    @Column(name = "userpic")
    @JsonAlias(value = {"userpic"})
    private String userpic = null;

    @Column(name = "useridcard")
    @JsonAlias(value = {"useridcard"})
    private String useridcard = null;

    @Column(name = "userveritystatus")
    @JsonAlias(value = {"userveritystatus"})
    private Integer userveritystatus = null;

    @Column(name = "userlevel")
    @JsonAlias(value = {"userlevel"})
    private String userlevel = null;

    @Column(name = "activestatus")
    @JsonAlias(value = {"activestatus"})
    private Integer activestatus = null;

    @Column(name = "config_wpaper")
    @JsonAlias(value = {"config_wpaper"})
    private String configWpaper = null;

    @Column(name = "haslogin")
    private Integer haslogin = null;

    @Column(name = "createuser_id")
    private String createuserId = null;

    @Column(name = "grade")
    private Integer grade= null;

}
