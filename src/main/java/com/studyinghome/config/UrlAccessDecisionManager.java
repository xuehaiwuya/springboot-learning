package com.studyinghome.config;

import com.studyinghome.common.Const;
import com.studyinghome.model.User;
import com.studyinghome.result.CodeMsg;
import com.studyinghome.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * UrlAccessDecisionManager
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019/4/29 13:34
 */
@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {
    @Autowired
    RedisUtil redisUtil;

    @Override
    public void decide(Authentication auth, Object o, Collection<ConfigAttribute> cas) {
        Iterator<ConfigAttribute> iterator = cas.iterator();
        //用户权限信息不为空时重置用户缓存时间
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            redisUtil.expire(((User) auth.getPrincipal()).getName(), Const.DEFAULT_REDIS_SESSION_TIMEOUT);
        }
        while (iterator.hasNext()) {
            ConfigAttribute ca = iterator.next();
            //当前请求需要的权限
            String needRole = ca.getAttribute();
            if ("ROLE_LOGIN".equals(needRole)) {
                if (auth instanceof AnonymousAuthenticationToken) {
                    throw new BadCredentialsException(CodeMsg.NEED_LOGIN.getMsg());
                } else {
                    return;
                }
            }
            //当前用户所具有的权限
            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException(CodeMsg.SECURITY_ERROR.getMsg());
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}