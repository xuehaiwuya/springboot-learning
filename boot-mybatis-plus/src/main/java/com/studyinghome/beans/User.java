package com.studyinghome.beans;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sun.javafx.beans.IDProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019-04-26 15:02
 */
@Data
@TableName("tb_user")
public class User implements Serializable {
    @TableId
    private Integer uid;
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
}