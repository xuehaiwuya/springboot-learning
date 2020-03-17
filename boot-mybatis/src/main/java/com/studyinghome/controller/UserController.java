package com.studyinghome.controller;

import com.studyinghome.entity.SysUser;
import com.studyinghome.framework.result.CodeMsg;
import com.studyinghome.framework.result.Result;
import com.studyinghome.service.UserService;
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

    /**
     * swagger文档 访问地址为：http://ip:port/swagger-ui.html
     * <p>
     * swagger的具体使用方式请参考swagger官网：https://swagger.io/
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "use id get user information", notes = "query user")
    @ApiImplicitParam(name = "id", value = "user id")
    @GetMapping(value = "{id}")
    public Result getUser(@PathVariable("id") long id) {
        SysUser user = userService.getByPrimaryKey(id);
        if (null == user) {
            return Result.error(CodeMsg.ERROR);
        }
        return Result.success(CodeMsg.SUCCESS, user);
    }
}
