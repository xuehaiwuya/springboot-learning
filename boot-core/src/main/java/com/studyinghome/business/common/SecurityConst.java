package com.studyinghome.business.common;

/**
 * Security常量
 *
 * @author Leslie (panxiang_work@163.com)
 * @website https://studyinghome.com
 * @create 2020-03-10 下午 3:00
 */

public class SecurityConst {
    /**
     * spring security 过滤路径
     *
     * @AUTH 未登录时访问路径
     * @LOGIN 登录访问路径
     * @LOGOUT 退出登录时访问路劲
     */
    public static final String AUTH = "/auth";
    public static final String LOGIN = "/login";
    public static final String LOGOUT = "/logout";
    public static final String SWAGGER = "/swagger-ui.html";
}
