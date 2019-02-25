package com.rrx.kaoqins;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableScheduling
@EnableEncryptableProperties
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
//@EnableDubbo //开启基于注解的
@ImportResource(locations = "classpath:dubbo/provider.xml")
@EnableHystrix
@RefreshScope
@EnableSwagger2
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
