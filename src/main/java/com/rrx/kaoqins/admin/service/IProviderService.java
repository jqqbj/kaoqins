package com.rrx.kaoqins.admin.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


//实际开发中接口和实体会单独抽到一个maven模块，提供者、消费者之间都引用这个模块
public interface IProviderService {

    public String testDubbo();

}
