package com.studyinghome;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SpringbootApplication
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019/4/26 13:46
 */
@SpringBootApplication
@EnableSwagger2
@MapperScan("com.studyinghome.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

