package com.rrx.kaoqins;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rrx.kaoqins.admin.service.IProviderService;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.annotation.Reference;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@EnableScheduling
//@EnableEncryptableProperties
//@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
//@ImportResource(locations = "classpath:dubbo/consumer.xml")
//@EnableHystrix
public class ConsumerApplication{

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ConsumerApplication.class, args);
//        ConsumerApplication application = context.getBean(ConsumerApplication.class);
//        String result = application.doSayHello("world");
    }

}
