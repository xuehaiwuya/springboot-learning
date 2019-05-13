package com.studyinghome.rabbitmq;

import com.studyinghome.common.Const;
import com.studyinghome.model.Message;
import com.studyinghome.model.User;
import com.studyinghome.utils.DateTimeUtil;
import com.studyinghome.utils.JsonUtil;
import com.studyinghome.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * MQReceiver
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019/5/10 17:17
 */
@Slf4j
@Component
public class MsgReceiver {
    @Autowired
    private RedisUtil redisUtil;

    @RabbitListener(queues = Const.ORDERQUEUE)
    public void orderReceive(String message) {
        log.info("receive order message:" + message);
//        Message msg = JsonUtil.string2Obj(message, Message.class);
//        if (msg == null) {
//            return;
//        }
//        redisService.set(Key.userKey, msg.getId(), msg.getUserList());
//        log.info("---------插入数据到redis-----------");
    }

    @RabbitListener(queues = Const.USERQUEUE)
    public void userReceive(String message) {
        Message msg = JsonUtil.string2Obj(message, Message.class);
        User user = JsonUtil.string2Obj(msg.getMessage(), User.class);
        log.info("receive " + user.getName() + " message");
    }

    @RabbitListener(queues = Const.LOGQUEUE)
    public void logReceive(String message) {
        Message msg = JsonUtil.string2Obj(message, Message.class);
        if (!StringUtils.isBlank(msg.getOperate()) && msg.getOperate().equals("login")) {
            User user = JsonUtil.string2Obj(msg.getMessage(), User.class);
            log.info("用户" + user.getName() + "于" + DateTimeUtil.dateToStr(new Date(), DateTimeUtil.DAY_FORMAT) + "登录");
        }
        if (!StringUtils.isBlank(msg.getOperate()) && msg.getOperate().equals("logout")) {
            log.info("用户" + msg.getMessage() + "于" + DateTimeUtil.dateToStr(new Date(), DateTimeUtil.DAY_FORMAT) + "退出登录");
        }
    }
}
