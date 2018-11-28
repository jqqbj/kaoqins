package com.rrx.kaoqins;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StaterRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        //System.out.println("CommandLineRunner...run..."+ Arrays.asList(args));
        System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作<<<<<<<<<<<<<");
    }

}
