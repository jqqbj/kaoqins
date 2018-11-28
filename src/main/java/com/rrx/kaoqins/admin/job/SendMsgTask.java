package com.rrx.kaoqins.admin.job;

import cn.hutool.core.date.DateUtil;
import com.rrx.kaoqins.admin.model.SysDict;
import com.rrx.kaoqins.admin.service.SysDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author JQQ
 * @Date 2018/11/27 15:58
 */

@Slf4j
@Component
public class SendMsgTask {
    @Autowired
    SysDictService sysDictService;

    //    每分钟启动
    @Scheduled(cron = "0/10 * * * * ?")
    public void timerToNow() {
        SysDict sysDict = sysDictService.getById(1);
        log.debug("定时查询数据库：" +sysDict.toString());
    }

}