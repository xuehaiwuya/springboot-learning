package com.studyinghome.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Role {
    private Integer id;
    private String name;
    private String cname;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
