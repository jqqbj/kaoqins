package com.rrx.kaoqins.admin.job;

import cn.hutool.core.date.DateUtil;
import com.rrx.kaoqins.admin.model.SysDict;
import com.rrx.kaoqins.admin.service.SysDictService;
import com.rrx.kaoqins.core.config.zk.DistributedLockByCurator;
import com.rrx.kaoqins.core.web.util.ResultUtil;
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
public class MySpringJob {
    @Autowired
    SysDictService sysDictService;
    @Autowired
    DistributedLockByCurator distributedLockByCurator;

    String path ="SYNC_KAOQIN_DB";

    //每分钟启动
    @Scheduled(cron = "0/30 * * * * ?")
    public void timerToNow() {
        try {
            /*
             * ZK分布式锁 ，也可以采用Redis锁，RedisHelper.lock，
             * 使用场景，如：多台机器执行定时任务，争抢资源
             */
            if(distributedLockByCurator.acquireLock(path)){
                log.debug("上次任务正在执行");
                return;
            }
            log.debug("任务已获取锁");
            //Thread.sleep(10000);
            SysDict sysDict = sysDictService.getById(1);
            log.debug("定时查询数据库：" + sysDict.toString());
            distributedLockByCurator.releaseLock(path);
            log.debug("任务已经释放锁");
        }catch (Exception ex){
            log.debug("程序执行异常"+ex.getMessage());
            distributedLockByCurator.releaseLock(path);
        }
    }

}