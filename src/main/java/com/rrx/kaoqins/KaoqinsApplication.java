package com.rrx.kaoqins;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableEncryptableProperties
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@EnableDubbo //开启基于注解的
public class KaoqinsApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(KaoqinsApplication.class, args);
    }

    /*
      打war包，需继承SpringBootServletInitializer，重新configure方法
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

}
