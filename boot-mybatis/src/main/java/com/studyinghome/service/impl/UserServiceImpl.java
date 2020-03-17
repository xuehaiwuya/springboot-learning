package com.studyinghome.service.impl;

import com.studyinghome.entity.SysUser;
import com.studyinghome.mapper.UserMapper;
import com.studyinghome.service.UserService;
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
    public SysUser insert(SysUser entity) {
        return null;
    }

    @Override
    public boolean removeByPrimaryKey(Long id) {
        return userMapper.delete(id);
    }

    @Override
    public boolean update(SysUser user) {
        return userMapper.update(user);
    }

    @Override
    public SysUser getByPrimaryKey(Long id) {
        return userMapper.getById(id);
    }

    @Override
    public SysUser getOneByEntity(SysUser entity) {
        return null;
    }

    @Override
    public List<SysUser> listAll() {
        return null;
    }

    @Override
    public List<SysUser> listByEntity(SysUser entity) {
        return null;
    }

}
