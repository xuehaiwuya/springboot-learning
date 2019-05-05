package com.studyinghome.service;

import com.studyinghome.model.Menu;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019-04-29 13:25
 */
public interface MenuService {
    /**
     * 获取所有权限信息
     *
     * @return
     */
    List<Menu> getAllMenu();
}
