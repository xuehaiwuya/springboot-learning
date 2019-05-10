package com.studyinghome.rabbitmq;

import com.studyinghome.common.Const;
import com.studyinghome.model.Message;
import com.studyinghome.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public void userReceive(Message message) {
        log.info("receive user message:" + message);
    }
}
