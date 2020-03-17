package com.studyinghome.controller;

import com.studyinghome.business.entity.User;
import com.studyinghome.entity.Message;
import com.studyinghome.entity.SysUser;
import com.studyinghome.framework.result.CodeMsg;
import com.studyinghome.framework.result.Result;
import com.studyinghome.rabbitmq.MsgSender;
import com.studyinghome.service.UserService;
import com.studyinghome.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;

/**
 * ${DESCRIPTION}
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019-05-01 21:45
 */
@RestController
public class LoginController {
    @Autowired
    ExecutorService executorService;
    @Autowired
    UserService userService;
    @Autowired
    MsgSender msgSender;

    @GetMapping(value = "/auth")
    public Result login() {
        return Result.error(CodeMsg.NEED_LOGIN);
    }

    @GetMapping("get/user/{id}")
    public Result queryUser(@PathVariable("id") long id) {
        SysUser user = userService.getByPrimaryKey(id);
        if (null == user) {
            return Result.error(CodeMsg.ERROR);
        }
        //多线程处理消息发送，不需要返回结果时可以避免等待
        long start = System.currentTimeMillis();
        //使用线程池方式创建线程
        executorService.execute(() -> msgSender.sendUser(Message.getMessage("query", JsonUtil.obj2String(user))));
        long end = System.currentTimeMillis();
        System.out.println("总共耗时：" + (end - start));
        return Result.success(CodeMsg.SUCCESS, user);
    }
}
