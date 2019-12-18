package com.dream.week;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.dream.week.mapper")
public class WeekApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeekApplication.class, args);
    }
}
