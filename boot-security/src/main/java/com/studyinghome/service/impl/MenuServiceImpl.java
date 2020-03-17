package com.studyinghome.service.impl;

import com.studyinghome.entity.Menu;
import com.studyinghome.mapper.MenuMapper;
import com.studyinghome.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019-04-29 13:25
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> getAllMenu() {
        return menuMapper.getAllMenus();
    }
}
