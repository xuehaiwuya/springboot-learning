package com.studyinghome.controller;

import com.studyinghome.business.entity.User;
import com.studyinghome.business.service.UserService;
import com.studyinghome.framework.result.CodeMsg;
import com.studyinghome.framework.result.Result;
import com.studyinghome.utils.JsonUtil;
import com.studyinghome.utils.RedisUtil;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 首先从redis缓存中获取user数据，若缓存中没有则从数据库中查询，然后将查询的结果添加到redis中
     *
     * @param id
     * @return
     */
    @GetMapping(value = "{id}")
    public Result getUser(@PathVariable("id") long id) {
        User user = JsonUtil.string2Obj(redisUtil.get("user"), User.class);
        log.info("从缓存获取数据..........");
        if (null == user) {
            log.info("从缓存获取数据失败..........");
            user = userService.getByPrimaryKey(id);
            if (null == user) {
                return Result.error(CodeMsg.ERROR);
            }
            log.info("从数据库获取数据...........");
            redisUtil.set("user", JsonUtil.obj2String(user));
        }
        return Result.success(CodeMsg.SUCCESS, user);
    }
}
