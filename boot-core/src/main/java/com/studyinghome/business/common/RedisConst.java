package com.studyinghome.business.common;

/**
 * Redis常量
 *
 * @author Leslie (panxiang_work@163.com)
 * @website https://studyinghome.com
 * @create 2020-03-10 下午 2:59
 */

public class RedisConst {
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
}
