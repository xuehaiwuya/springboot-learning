package com.studyinghome.service.impl;

import com.studyinghome.beans.SysUser;
import com.studyinghome.business.common.RedisConst;
import com.studyinghome.business.entity.User;
import com.studyinghome.persistence.mapper.UserMapper;
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
    public SysUser getUserById(Long id) {
        User user = userMapper.getById(id);
        SysUser sysUser = new SysUser();
        sysUser.setUser(user);
        if (null != sysUser) {
            sysUser.setRoles(userMapper.getRolesByUid(user.getId()));
        }
        return sysUser;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = JsonUtil.string2Obj(redisUtil.get(name), User.class);
        SysUser sysUser = new SysUser();
        if (user == null) {
            //当用户缓存信息为空时，查询用户信息并添加进缓存
            user = userMapper.getByName(name);
            if (user == null) {
                throw new UsernameNotFoundException("账户不存在!");
            }
            sysUser.setUser(user);
            sysUser.setRoles(userMapper.getRolesByUid(user.getId()));
            redisUtil.setEx(name, JsonUtil.obj2String(user), RedisConst.DEFAULT_REDIS_SESSION_TIMEOUT);
        } else {
            //当用户缓存信息不为空时，更新缓存时间
            redisUtil.expire(name, RedisConst.DEFAULT_REDIS_SESSION_TIMEOUT);
        }
        return sysUser;
    }
}
