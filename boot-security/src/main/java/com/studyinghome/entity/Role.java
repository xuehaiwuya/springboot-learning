package com.studyinghome.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.studyinghome.framework.object.AbstractDO;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * Role
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019/4/29 11:38
 */
@Alias("role")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role extends AbstractDO {
    /**
     * 角色名称：ROLE_xxx
     */
    private String name;
    /**
     * 角色中文名
     */
    private String cname;
}
