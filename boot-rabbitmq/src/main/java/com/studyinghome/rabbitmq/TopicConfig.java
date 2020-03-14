package com.studyinghome.rabbitmq;

import com.studyinghome.business.common.RabbitConst;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Topic模式
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019/5/10 17:16
 */
@Configuration
public class TopicConfig {


    /**
     * 创建一个TopicExchange，三个参数分别表示创建TopicExchange的名字、重启后是否依然有效、长期未使用是否删除
     *
     * @return
     */
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(RabbitConst.TOPICNAME, true, false);
    }

    /**
     * 用来处理order相关的消息
     */
    @Bean
    public Queue order() {
        return new Queue(RabbitConst.ORDERQUEUE);
    }

    /**
     * 处理用户相关的消息
     */
    @Bean
    public Queue user() {
        return new Queue(RabbitConst.USERQUEUE);
    }

    @Bean
    public Queue log() {
        return new Queue(RabbitConst.LOGQUEUE);
    }

    @Bean
    Binding orderBinding() {
        return BindingBuilder.bind(order()).to(topicExchange()).with(RabbitConst.ORDERQUEUE + ".#");
    }

    @Bean
    Binding userBinding() {
        return BindingBuilder.bind(user()).to(topicExchange()).with(RabbitConst.USERQUEUE + ".#");
    }

    @Bean
    Binding logBinding() {
        return BindingBuilder.bind(log()).to(topicExchange()).with(RabbitConst.LOGQUEUE + ".#");
    }

}
