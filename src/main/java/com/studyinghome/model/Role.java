package com.studyinghome.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

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
    private String nameZh;
}
