package com.studyinghome.controller;

import com.studyinghome.model.User;
import com.studyinghome.result.CodeMsg;
import com.studyinghome.result.Result;
import com.studyinghome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019-04-26 15:38
 */
@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "{id}")
    public Result getUser(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        if (null == user) {
            return Result.error(CodeMsg.ERROR);
        }
        return Result.success(CodeMsg.SUCCESS, user);
    }
}
