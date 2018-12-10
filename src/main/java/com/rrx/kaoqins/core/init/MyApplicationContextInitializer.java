package com.rrx.kaoqins.core.init;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author JQQ
 */
/*ConfigurableApplicationContext为接口中传入的参数类型，在initialize中可以通过传入的参数执行所需要的操作*/
public class MyApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        //设置环境变量
        System.setProperty("jssyptpwd","jiqq");
        //解决ES报错
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

}
