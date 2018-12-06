package com.rrx.kaoqins.admin.job;

import com.cxytiandi.elasticjob.annotation.ElasticJobConf;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ElasticJobConf(name = "MySimpleJob")
public class MySimpleJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println(String.format("------Thread ID: %s, 任务总片数: %s, 当前分片项: %s",
                Thread.currentThread().getId(), shardingContext.getShardingTotalCount(), shardingContext.getShardingItem()));
        /*
          场景一：取余数,分片执行
         */
        int sharding = shardingContext.getShardingTotalCount(); //总分片
        int total = 5000+20; //总记录数
        int pageSize = 500; //每页条数
        int totalPage = (total+pageSize-1)/pageSize; // 总页数
        int currentItem = shardingContext.getShardingItem(); //当前分片值
        log.info("总页数"+totalPage);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= totalPage; i++) {
            if(i%sharding==currentItem){
                list.add(i);
            }
        }
        list.forEach(e->{
            log.info(e+"-> limit "+e*500+ ","+pageSize);
        });

        /**
         *  场景二：
         *  实际开发中，有了任务总片数和当前分片项，就可以对任务进行分片执行了
         *  比如 SELECT * FROM user WHERE status = 0 AND MOD(id, shardingTotalCount) = shardingItem
         */

    }

}