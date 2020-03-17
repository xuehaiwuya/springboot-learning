package com.studyinghome.service.impl;

import com.studyinghome.business.common.RedisConst;
import com.studyinghome.business.entity.User;
import com.studyinghome.entity.SecurityUser;
import com.studyinghome.entity.SysUser;
import com.studyinghome.mapper.UserMapper;
import com.studyinghome.service.UserService;
import com.studyinghome.utils.JsonUtil;
import com.studyinghome.utils.RedisUtil;
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
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public SecurityUser getUserById(Long id) {
        SysUser user = userMapper.getById(id);
        SecurityUser securityUser = new SecurityUser();
        securityUser.setUser(user);
        if (null != securityUser) {
            securityUser.setRoles(userMapper.getRolesByUid(user.getId()));
        }
        return securityUser;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = JsonUtil.string2Obj(redisUtil.get(name), User.class);
        SecurityUser securityUser = new SecurityUser();
        if (user == null) {
            //当用户缓存信息为空时，查询用户信息并添加进缓存
            user = userMapper.getByName(name);
            if (user == null) {
                throw new UsernameNotFoundException("账户不存在!");
            }
            securityUser.setUser(user);
            securityUser.setRoles(userMapper.getRolesByUid(user.getId()));
            redisUtil.setEx(name, JsonUtil.obj2String(user), RedisConst.DEFAULT_REDIS_SESSION_TIMEOUT);
        } else {
            //当用户缓存信息不为空时，更新缓存时间
            redisUtil.expire(name, RedisConst.DEFAULT_REDIS_SESSION_TIMEOUT);
        }
        return securityUser;
    }
}
