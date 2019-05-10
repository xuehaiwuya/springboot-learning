package com.studyinghome.common;

/**
 * ${DESCRIPTION}
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019-05-09 11:15
 */

public final class Const {
    /**
     * redis默认缓存时间，单位：秒
     *
     * @DEFAULT_REDIS_SESSION_TIMEOUT 默认缓存时间 30min
     */
    public static final long DEFAULT_REDIS_SESSION_TIMEOUT = 30 * 60;

    public static final String ROLE_LOGIN = "ROLE_LOGIN";
    public static final String USERNAME = "name";
    public static final String PASSWORD = "pwd";

    /**
     * redis key
     *
     * @ALL_MENU 所有菜单及其访问所需权限
     */
    public static final String ALL_MENU = "menu:all";

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

    /**
     * MQ 常量
     */
    public static final String TOPICNAME = "Leslie";
    public static final String ORDERQUEUE = "order";
    public static final String USERQUEUE = "user";

}
