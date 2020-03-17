package com.studyinghome.rabbitmq;

import com.studyinghome.business.common.RabbitConst;
import com.studyinghome.business.entity.User;
import com.studyinghome.entity.Message;
import com.studyinghome.utils.DateTimeUtil;
import com.studyinghome.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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

    @RabbitListener(queues = RabbitConst.ORDERQUEUE)
    public void orderReceive(String message) {
        log.info("receive order message:" + message);
//        Message msg = JsonUtil.string2Obj(message, Message.class);
//        if (msg == null) {
//            return;
//        }
//        redisService.set(Key.userKey, msg.getId(), msg.getUserList());
//        log.info("---------插入数据到redis-----------");
    }

    @RabbitListener(queues = RabbitConst.USERQUEUE)
    public void userReceive(String message) {
        Message msg = JsonUtil.string2Obj(message, Message.class);
        User user = JsonUtil.string2Obj(msg.getMessage(), User.class);
        log.info("receive " + user.getName() + " message");
    }

    @RabbitListener(queues = RabbitConst.LOGQUEUE)
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
