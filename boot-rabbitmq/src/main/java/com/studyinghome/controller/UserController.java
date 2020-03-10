package com.studyinghome.controller;

import com.studyinghome.business.entity.User;
import com.studyinghome.business.service.UserService;
import com.studyinghome.framework.result.CodeMsg;
import com.studyinghome.framework.result.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @ApiOperation(value = "use id get user information",notes = "query user")
    @ApiImplicitParam(name = "id",value = "user id")
    @GetMapping(value = "{id}")
    public Result getUser(@PathVariable("id") long id) {
        User user = userService.getByPrimaryKey(id);
        if (null == user) {
            return Result.error(CodeMsg.ERROR);
        }
        return Result.success(CodeMsg.SUCCESS, user);
    }
}
