package com.studyinghome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author Leslie (panxiang_work@163.com)
 * @website https://studyinghome.com
 * @create 2020/3/19 下午 1:36
 */
@SpringBootApplication
@EnableCaching
public class BootCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootCacheApplication.class, args);
    }

}
