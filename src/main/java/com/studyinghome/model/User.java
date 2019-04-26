package com.studyinghome.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * 用户信息
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019-04-26 15:02
 */
@Getter
@Setter
@Alias("user")
public class User {
    private int uid;
    private String name;
    private String pwd;
    private String phone;
    private String photo;
    private String email;
    private Date createTime;
    private Date updateTime;
}
