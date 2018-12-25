package com.rrx.kaoqins.core.log;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogService {

    /* 异步线程调用，通过线程池实现 */
    @Async
    public void saveLog(Log log2) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug(Thread.currentThread().getName()+"拦截日志:{}", log2.value());
    }

}
