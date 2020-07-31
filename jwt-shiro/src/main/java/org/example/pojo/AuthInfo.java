package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_uc_user")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Data
@DynamicInsert
@DynamicUpdate
public class AuthInfo implements Serializable {

    @Id
    @GeneratedValue(generator = "uuidStrategy")
    @GenericGenerator(name = "uuidStrategy", strategy = "uuid")
    @Column(name = "user_id", nullable = false, length = 32)
    private String id;

    @Column(name = "loginname")
    @JsonAlias(value = {"username"})
    private String loginName;


    @Column(name = "loginpassword")
    @JsonAlias(value = {"password"})
    private String password;

    @Column(name = "salt")
    private String salt;

    @Column(name = "haslogin")
    private Integer hasLogin;

    @Column(name = "createtime")
    private Date createTime;



}
