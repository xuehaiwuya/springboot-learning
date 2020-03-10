package com.studyinghome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Leslie (panxiang_work@163.com)
 * @website https://studyinghome.com
 * @create 2020/3/10 下午 11:29
 */
@SpringBootApplication
@EnableSwagger2
public class BootMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootMybatisApplication.class, args);
    }

}
