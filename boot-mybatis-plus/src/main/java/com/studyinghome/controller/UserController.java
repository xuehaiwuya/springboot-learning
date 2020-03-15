package com.studyinghome.controller;

import com.studyinghome.beans.User;
import com.studyinghome.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leslie (panxiang_work@163.com)
 * @website https://studyinghome.com
 * @create 2020-03-14 下午 2:54
 */
@RestController
@RequestMapping("/user/")
@Slf4j
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("{id}")
    public User getUser(@PathVariable("id") int id) {
        return userService.getById(id);
    }
}
