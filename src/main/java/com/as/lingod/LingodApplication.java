package com.as.lingod;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.as.lingod"})
@MapperScan("com.as.lingod.dao")
public class LingodApplication {

    public static void main(String[] args) {
        SpringApplication.run(LingodApplication.class, args);
    }

}
