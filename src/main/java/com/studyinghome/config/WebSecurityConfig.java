package com.studyinghome.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studyinghome.result.CodeMsg;
import com.studyinghome.result.Result;
import com.studyinghome.service.UserService;
import com.studyinghome.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.io.PrintWriter;

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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //对密码进行加密
        auth.userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/login", "/favicon.ico");
    }

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
                //loginPage登录页面，loginProcessingUrl所有登录请求地址
                .formLogin().loginPage("/login_p").loginProcessingUrl("/login")
                //登录请求时的用户名和密码字段
                .usernameParameter("name").passwordParameter("pwd")
                .failureHandler((req, resp, e) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    Result result = null;
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
                    resp.setStatus(401);
                    ObjectMapper om = new ObjectMapper();
                    PrintWriter out = resp.getWriter();
                    out.write(om.writeValueAsString(result));
                    out.flush();
                    out.close();
                })
                .successHandler((req, resp, auth) -> {
                    ObjectMapper om = new ObjectMapper();
                    PrintWriter out = resp.getWriter();
                    out.write(om.writeValueAsString(Result.success(CodeMsg.SUCCESS, UserUtils.getCurrentHr())));
                    out.flush();
                    out.close();
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler((req, resp, authentication) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    ObjectMapper om = new ObjectMapper();
                    PrintWriter out = resp.getWriter();
                    out.write(om.writeValueAsString(Result.success(CodeMsg.SUCCESS)));
                    out.flush();
                    out.close();
                })
                .permitAll()
                .and().csrf().disable()
                .exceptionHandling().accessDeniedHandler(deniedHandler);
    }
}