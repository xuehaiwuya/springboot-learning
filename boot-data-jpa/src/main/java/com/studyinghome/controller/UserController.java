package com.studyinghome.controller;

import com.studyinghome.entity.User;
import com.studyinghome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leslie (panxiang_work@163.com)
 * @website https://studyinghome.com
 * @create 2020-03-17 下午 2:03
 */
@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public User getUser(@PathVariable("id") int id) {
        return userService.selectById(id);
    }
}
