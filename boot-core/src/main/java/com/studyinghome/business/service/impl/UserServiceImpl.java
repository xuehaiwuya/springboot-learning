package com.studyinghome.business.service.impl;

import com.studyinghome.business.entity.User;
import com.studyinghome.business.service.UserService;
import com.studyinghome.persistence.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019-04-26 15:29
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User insert(User entity) {
        return null;
    }

    @Override
    public boolean removeByPrimaryKey(Long id) {
        return userMapper.delete(id);
    }

    @Override
    public boolean update(User user) {
        return userMapper.update(user);
    }

    @Override
    public User getByPrimaryKey(Long id) {
        return userMapper.getById(id);
    }

    @Override
    public User getOneByEntity(User entity) {
        return null;
    }

    @Override
    public List<User> listAll() {
        return null;
    }

    @Override
    public List<User> listByEntity(User entity) {
        return null;
    }

}
