package com.studyinghome.business.entity;

import com.studyinghome.framework.object.AbstractDO;
import lombok.Data;


/**
 * 用户信息
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019-04-26 15:02
 */
@Data
public class User extends AbstractDO {
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
