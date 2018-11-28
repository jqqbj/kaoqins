package com.rrx.kaoqins;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableEncryptableProperties
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class KaoqinsApplication {

    public static void main(String[] args) {
        SpringApplication.run(KaoqinsApplication.class, args);
    }

}
