package com.studyinghome.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * Role
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019/4/29 11:38
 */
@Getter
@Setter
@Alias("role")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role {
    private Integer id;
    /**
     * 角色名称：ROLE_xxx
     */
    private String name;
    /**
     * 角色中文名
     */
    private String cname;
    private Date createTime;
    private Date updateTime;
}
