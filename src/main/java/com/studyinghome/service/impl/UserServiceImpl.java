package com.studyinghome.service.impl;

import com.studyinghome.common.Const;
import com.studyinghome.mapper.UserMapper;
import com.studyinghome.model.User;
import com.studyinghome.service.UserService;
import com.studyinghome.utils.JsonUtil;
import com.studyinghome.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public User getUserById(int id) {
        User user = userMapper.getById(id);
        if (null != user) {
            user.setRoles(userMapper.getRolesByUid(user.getUid()));
        }
        return user;
    }

    @Override
    public int save(User user) {
        //对用户密码进行加密
        user.setPwd(new BCryptPasswordEncoder().encode(user.getPwd()));
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
        User user = JsonUtil.string2Obj(redisUtil.get(name), User.class);
        if (user == null) {
            //当用户缓存信息为空时，查询用户信息并添加进缓存
            user = userMapper.getByName(name);
            if (user == null) {
                throw new UsernameNotFoundException("账户不存在!");
            }
            user.setRoles(userMapper.getRolesByUid(user.getUid()));
            redisUtil.setEx(name, JsonUtil.obj2String(user), Const.DEFAULT_REDIS_SESSION_TIMEOUT);
        } else {
            //当用户缓存信息不为空时，更新缓存时间
            redisUtil.expire(name, Const.DEFAULT_REDIS_SESSION_TIMEOUT);
        }
        return user;
    }
}
