package com.studyinghome.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studyinghome.result.CodeMsg;
import com.studyinghome.result.Result;
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
                .formLogin().loginPage("/login_p").loginProcessingUrl("/login")
                .usernameParameter("username").passwordParameter("password")
                .failureHandler((req, resp, e) -> {
                    resp.setContentType("application/json;charset=utf-8");
//                    Result result = null;
//                    if (e instanceof BadCredentialsException ||
//                            e instanceof UsernameNotFoundException) {
////                        result = RespBean.error("账户名或者密码输入错误!");
//
//                    } else if (e instanceof LockedException) {
////                        result = RespBean.error("账户被锁定，请联系管理员!");
//                    } else if (e instanceof CredentialsExpiredException) {
////                        result = RespBean.error("密码过期，请联系管理员!");
//                    } else if (e instanceof AccountExpiredException) {
////                        result = RespBean.error("账户过期，请联系管理员!");
//                    } else if (e instanceof DisabledException) {
////                        result = RespBean.error("账户被禁用，请联系管理员!");
//                    } else {
////                        result = RespBean.error("登录失败!");
//                    }
                    resp.setStatus(401);
                    ObjectMapper om = new ObjectMapper();
                    PrintWriter out = resp.getWriter();
                    out.write(om.writeValueAsString(Result.error(CodeMsg.ERROR)));
                    out.flush();
                    out.close();
                })
                .successHandler((req, resp, auth) -> {
//                    RespBean respBean = RespBean.ok("登录成功!", HrUtils.getCurrentHr());
                    ObjectMapper om = new ObjectMapper();
                    PrintWriter out = resp.getWriter();
                    out.write(om.writeValueAsString(Result.success(CodeMsg.SUCCESS)));
                    out.flush();
                    out.close();
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler((req, resp, authentication) -> {
                    resp.setContentType("application/json;charset=utf-8");
//                    RespBean respBean = RespBean.ok("注销成功!");
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