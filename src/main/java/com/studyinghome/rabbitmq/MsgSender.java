package com.studyinghome.rabbitmq;

import com.studyinghome.common.Const;
import com.studyinghome.model.Message;
import com.studyinghome.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * MQSender
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019/5/10 17:24
 */
@Slf4j
@Component
public class MsgSender {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendOrder(Message message) {
        String msg = JsonUtil.obj2String(message);
        log.info("send message:" + msg);
        rabbitTemplate.convertAndSend(Const.TOPICNAME, Const.ORDERQUEUE + ".msg", msg);
    }

    public void sendUser(Message message) {
        String msg = JsonUtil.obj2String(message);
        log.info("send message:" + msg);
        //休眠5秒，测试多线程的处理时间
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
        }
        rabbitTemplate.convertAndSend(Const.TOPICNAME, Const.USERQUEUE + ".msg", msg);
    }

}
