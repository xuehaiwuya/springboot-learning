package com.studyinghome.service.impl;

import com.studyinghome.mapper.UserMapper;
import com.studyinghome.model.User;
import com.studyinghome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
    public User getUserById(int id) {
        return userMapper.getById(id);
    }

    @Override
    public int save(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int delete(int id) {
        return userMapper.delete(id);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userMapper.getByName(name);
        if (user == null) {
            throw new UsernameNotFoundException("账户不存在!");
        }
        user.setRoles(userMapper.getRolesByUid(user.getUid()));
        return user;
    }

//    public static void main(String[] args) {
//        System.out.println(new BCryptPasswordEncoder().encode("123456"));
//    }
}
