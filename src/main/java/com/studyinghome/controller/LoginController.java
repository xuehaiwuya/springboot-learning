package com.studyinghome.controller;

import com.studyinghome.model.Message;
import com.studyinghome.model.User;
import com.studyinghome.rabbitmq.MsgSender;
import com.studyinghome.result.CodeMsg;
import com.studyinghome.result.Result;
import com.studyinghome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping(value = "/auth")
    public Result login() {
        return Result.error(CodeMsg.NEED_LOGIN);
    }

    @RequestMapping("get/user/{id}")
    public Result queryUser(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        if (null == user) {
            return Result.error(CodeMsg.ERROR);
        }
        //多线程处理消息发送，不需要返回结果时可以避免等待
        long start = System.currentTimeMillis();
        //使用线程池方式创建线程
        executorService.execute(
                () -> msgSender.sendUser(Message.getMessage(user))
        );
        long end = System.currentTimeMillis();
        System.out.println("总共耗时：" + (end - start));
        return Result.success(CodeMsg.SUCCESS, user);
    }
}
