package com.rrx.kaoqins.admin.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@com.alibaba.dubbo.config.annotation.Service
public class ProviderService implements IProviderService {

    public String testDubbo() {
        return "测试DUBBO";
    }

}
