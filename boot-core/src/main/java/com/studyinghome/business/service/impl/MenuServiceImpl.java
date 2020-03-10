package com.studyinghome.business.service.impl;

import com.studyinghome.business.entity.Menu;
import com.studyinghome.business.service.MenuService;
import com.studyinghome.persistence.mapper.MenuMapper;
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
