package com.as.lingod;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication(scanBasePackages = {"com.as.lingod"})
@MapperScan("com.as.lingod.dao")
@EnableScheduling
public class LingodApplication {

    public static void main(String[] args) {
        SpringApplication.run(LingodApplication.class, args);
    }


    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(4);//我这里设置的线程数是2,可以根据需求调整
        return taskScheduler;
    }

}
