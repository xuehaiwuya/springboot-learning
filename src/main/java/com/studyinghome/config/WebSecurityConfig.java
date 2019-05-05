package com.studyinghome.config;

import com.studyinghome.result.CodeMsg;
import com.studyinghome.result.Result;
import com.studyinghome.service.UserService;
import com.studyinghome.utils.JsonUtil;
import com.studyinghome.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
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
 * WebSecurityConfig
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019/4/29 13:38
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;
    @Autowired
    CustomMetadataSource metadataSource;
    @Autowired
    UrlAccessDecisionManager urlAccessDecisionManager;
    @Autowired
    AuthenticationAccessDeniedHandler deniedHandler;
    @Autowired
    CustomAuthenticationFailureHandler failureHandler;
    @Autowired
    CustomAuthenticationSuccessHandler successHandler;
    @Autowired
    CustomLogoutSuccessHandler logoutSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //对密码进行加密
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        //过滤不需要登录的请求
        web.ignoring().antMatchers("/login");
    }

    /**
     * 1.在configure(HttpSecurity http)方法中，通过withObjectPostProcessor将刚刚创建的
     * UrlFilterInvocationSecurityMetadataSource和UrlAccessDecisionManager注入进来。
     * 到时候，请求都会经过刚才的过滤器（除了configure(WebSecurity web)方法忽略的请求）。
     * <p>
     * 2.successHandler中配置登录成功时返回的JSON，登录成功时返回当前用户的信息。
     * <p>
     * 3.failureHandler表示登录失败，登录失败的原因可能有多种，我们根据不同的异常输出不同的错误提示即可。
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(metadataSource);
                        o.setAccessDecisionManager(urlAccessDecisionManager);
                        return o;
                    }
                })
                .and()
                .formLogin()
                //loginPage登录页面，此处定义为用户未登录时返回的信息，前端接收到信息转跳登录页面，loginProcessingUrl所有登录请求地址
                .loginPage("/login").loginProcessingUrl("/login")
                //登录请求时的用户名和密码字段
                .usernameParameter("name").passwordParameter("pwd")
                .failureHandler(failureHandler)
                .successHandler(successHandler)
                .permitAll()
                .and()
                //退出登录操作
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll()
                .and().csrf().disable()
                .exceptionHandling().accessDeniedHandler(deniedHandler);
    }
}

/**
 * 登录失败操作
 */
@Component
class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
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
        PrintWriter out = httpServletResponse.getWriter();
        out.write(JsonUtil.obj2String(result));
        out.flush();
        out.close();
    }
}

/**
 * 登录成功操作
 */
@Component
class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        PrintWriter out = httpServletResponse.getWriter();
        out.write(JsonUtil.obj2String(Result.success(CodeMsg.SUCCESS, UserUtils.getCurrentHr())));
        out.flush();
        out.close();
    }
}

/**
 * 退出成功操作
 */
@Component
class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //获取用户token
        String token = httpServletRequest.getParameter("token");
        //执行退出操作
        //1.删除redis缓存

        //2.返回用户退出成功信息
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.write(JsonUtil.obj2String(Result.success(CodeMsg.SUCCESS)));
        out.flush();
        out.close();
    }
}