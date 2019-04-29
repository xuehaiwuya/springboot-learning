package com.studyinghome.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

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
    private String url;
    private String path;
    private Object component;
    private String name;
    private String iconCls;
    private Long parentId;
    private List<Role> roles;
    private List<Menu> children;

}
