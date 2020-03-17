package com.studyinghome.service.impl;

import com.studyinghome.dao.UserDao;
import com.studyinghome.entity.User;
import com.studyinghome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Leslie (panxiang_work@163.com)
 * @website https://studyinghome.com
 * @create 2020-03-17 下午 2:01
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User selectById(long id) {
        return userDao.getOne(id);
    }
}
