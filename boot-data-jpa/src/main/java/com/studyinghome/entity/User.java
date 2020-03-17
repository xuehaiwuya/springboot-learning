package com.studyinghome.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 用户信息
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019-04-26 15:02
 */
@Data
@Table(name = "tb_user")
@Entity
/**
 * @JsonIgnoreProperties 避免字段值为null时报错
 */
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class User {
    @Id
    private Long uid;
    private String name;
    private String pwd;
    /**
     * 用户联系方式
     */
    private String phone;
    /**
     * 用户头像
     */
    private String photo;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户账户状态
     */
    private Integer status;
    private Date createTime;
    private Date updateTime;
}