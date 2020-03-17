package com.studyinghome.service;

import com.studyinghome.entity.User;

/**
 * @author Leslie (panxiang_work@163.com)
 * @website https://studyinghome.com
 * @create 2020-03-17 下午 2:00
 */
public interface UserService {
    /**
     * 查询单个
     */
    public User selectById(long id);
}
