package com.studyinghome.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.Date;
import java.util.List;

/**
 * Menu
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019/4/29 11:39
 */
@Getter
@Setter
@Alias("menu")
public class Menu {
    private Integer id;
    /**
     * 请求路径规则，匹配形式
     */
    private String url;
    /**
     * 请求路径
     */
    private String path;
    /**
     * 菜单、组件名称（英文）
     */
    private Object component;
    /**
     * 组件名称（中文）
     */
    private String name;
    /**
     * 图标
     */
    private String icon;
    /**
     * 菜单切换时是否保活
     */
    private Integer keepAlive;
    /**
     * 是否需要访问权限
     */
    private Integer requireAuth;
    /**
     * 父菜单id
     */
    private Long parentId;
    /**
     * 是否可用(1:可用，0:不可用)
     */
    private Integer status;
    private List<Role> roles;
    private List<Menu> children;
    private Date createTime;
    private Date updateTime;

}
