package com.studyinghome.config;

import com.studyinghome.common.Const;
import com.studyinghome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * WebSecurityConfig
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019/4/29 13:38
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
    LoginHandler loginHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //对密码进行加密
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        //过滤不需要登录的请求
        web.ignoring().antMatchers(Const.AUTH);
    }

    /**
     * 1.在configure(HttpSecurity http)方法中，通过withObjectPostProcessor将刚刚创建的
     * UrlFilterInvocationSecurityMetadataSource和UrlAccessDecisionManager注入进来。
     * 到时候，请求都会经过刚才的过滤器（除了configure(WebSecurity web)方法忽略的请求）。
     * <p>
     * 2.successHandler中配置登录成功时返回的信息。
     * <p>
     * 3.failureHandler表示登录失败
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(metadataSource);
                        object.setAccessDecisionManager(urlAccessDecisionManager);
                        return object;
                    }
                })
                .and()
                .formLogin()
                //loginPage登录页面，此处定义为用户未登录时返回的信息，前端接收到信息转跳登录页面，loginProcessingUrl所有登录请求地址
                .loginPage(Const.AUTH)
                .loginProcessingUrl(Const.LOGIN)
                //登录请求时的用户名和密码字段
                .usernameParameter(Const.USERNAME).passwordParameter(Const.PASSWORD)
                .failureHandler(loginHandler)
                .successHandler(loginHandler)
                .permitAll()
                .and()
                //退出登录操作
                .logout()
                .logoutUrl(Const.LOGOUT)
                .logoutSuccessHandler(loginHandler)
                .permitAll()
                .and()
                //.csrf().disable()是关闭SpringSecurity的CSRF验证
                .csrf().disable()
                .exceptionHandling().accessDeniedHandler(deniedHandler);
    }
}
