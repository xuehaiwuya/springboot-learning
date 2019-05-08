package com.studyinghome.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.TimeUnit;

/**
 * ${redis操作工具类}
 *
 * @author leslie panxiang_work@163.com
 * @create 2018-11-08 15:39
 */
@Slf4j
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 指定缓存失效时间
     *
     * @param key     键
     * @param timeout 超时时间(秒)
     * @return
     */
    public Boolean expire(String key, long timeout) {
        boolean result = false;
        if (timeout > 0) {
            redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            result = true;
        }
        return result;
    }

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    public Long del(String... key) {
        return redisTemplate.delete(CollectionUtils.arrayToList(key));

    }

    /**
     * exTime的单位是秒
     */
    public void setEx(String key, String value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 只有key不存在时才会执行插入操作，否则返回 false
     *
     * @param key
     * @param value
     * @return
     */
    public Boolean setNx(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * 添加操作
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 数据递增
     *
     * @param key
     * @return
     */
    public Long incr(String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    /**
     * 数据递减
     *
     * @param key
     * @return
     */
    public Long decr(String key) {
        return redisTemplate.opsForValue().decrement(key);
    }

}
