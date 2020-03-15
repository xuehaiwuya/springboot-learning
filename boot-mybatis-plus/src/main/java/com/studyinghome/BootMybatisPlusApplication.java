package com.studyinghome;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Leslie (panxiang_work@163.com)
 * @website https://studyinghome.com
 * @create 2020/3/15 下午 12:41
 */
@SpringBootApplication
@MapperScan("com.studyinghome.mapper")
public class BootMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootMybatisPlusApplication.class, args);
    }

}
