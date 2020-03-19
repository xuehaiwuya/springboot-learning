package com.studyinghome.controller;

import com.studyinghome.entity.User;
import com.studyinghome.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leslie (panxiang_work@163.com)
 * @website https://studyinghome.com
 * @create 2020-03-19 下午 1:17
 */
@RestController
@RequestMapping("/user/")
@Slf4j
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("{id}")
    @Cacheable(value = "user")
    public User getUser(@PathVariable("id") int id) {
        log.info("从数据库获取数据,并对数据进行了缓存...");
        return userService.getById(id);
    }

}
