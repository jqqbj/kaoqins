package com.rrx.kaoqins.admin.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProviderService implements IProviderService {

    //@Reference()
    @HystrixCommand
    public String sayHello(String msg) {
        return msg;
    }

}
