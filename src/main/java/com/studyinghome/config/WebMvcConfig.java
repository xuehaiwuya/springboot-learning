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

    /**
     * 初始化线程池
     */
    @Bean
    public ExecutorService executorService() {
        //自定义线程名
        ThreadFactory threadFactory = runnable -> new Thread(runnable, "init-threadPool");
        ThreadPoolExecutor thread = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<>(), threadFactory);
        return thread;
    }
}
