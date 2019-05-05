package com.studyinghome.config;

import com.studyinghome.utils.DateTimeUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.*;

/**
 * WebMvcConfig
 *
 * @author Leslie
 * @email panxiang_work@163.com
 * @create 2019/5/5 17:06
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateTimeUtil());
    }

    @Bean
    public ExecutorService executorService() {
        ThreadPoolExecutor thread = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<>());
        return thread;
    }
}
