package com.studyinghome.config;

import com.studyinghome.result.CodeMsg;
import com.studyinghome.result.Result;
import com.studyinghome.utils.JsonUtil;
import com.studyinghome.utils.RedisUtil;
import com.studyinghome.utils.UserUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * SpringSecurity登录操作返回信息
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019-05-07 9:40
 */
@Component
public class LoginHandler implements AuthenticationFailureHandler, AuthenticationSuccessHandler, LogoutSuccessHandler {
    @Autowired
    RedisUtil redisUtil;

    /**
     * 登录失败操作
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        Result result;
        if (e instanceof BadCredentialsException || e instanceof UsernameNotFoundException) {
            result = Result.error(CodeMsg.LOGIN_ERROR);
        } else if (e instanceof LockedException) {
            result = Result.error(CodeMsg.ACCOUNT_ERROR);
        } else if (e instanceof CredentialsExpiredException) {
            result = Result.error(CodeMsg.ACCOUNT_ERROR);
        } else if (e instanceof AccountExpiredException) {
            result = Result.error(CodeMsg.ACCOUNT_ERROR);
        } else if (e instanceof DisabledException) {
            result = Result.error(CodeMsg.ACCOUNT_ERROR);
        } else {
            result = Result.error(CodeMsg.LOGIN_ERROR);
        }
        PrintWriter out = response.getWriter();
        out.write(JsonUtil.obj2String(result));
        out.flush();
        out.close();
    }

    /**
     * 登录成功操作
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(JsonUtil.obj2String(Result.success(CodeMsg.SUCCESS, UserUtils.getCurrentHr())));
        out.flush();
        out.close();
    }

    /**
     * 退出成功操作
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //获取用户name
        String name = request.getParameter("name");
        //执行退出操作
        //1.删除redis缓存
        if (!StringUtils.isBlank(name) && redisUtil.get(name) != null) {
            redisUtil.del(name);
        }
        //2.返回用户退出成功信息
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(JsonUtil.obj2String(Result.success(CodeMsg.SUCCESS)));
        out.flush();
        out.close();
    }
}
