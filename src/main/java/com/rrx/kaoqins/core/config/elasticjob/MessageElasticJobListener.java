package com.rrx.kaoqins.core.config.elasticjob;

import com.cxytiandi.elasticjob.dynamic.util.JsonUtils;
import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作业监听器, 执行前后
 */
public class MessageElasticJobListener implements ElasticJobListener {

    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String msg = date + " 【JOB-" + shardingContexts.getJobName() + "】任务开始执行====" + JsonUtils.toJson(shardingContexts);
        //DingDingMessageUtil.sendTextMessage(msg);
    }

    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String msg = date + " 【JOB-" + shardingContexts.getJobName() + "】任务执行结束====" + JsonUtils.toJson(shardingContexts);
        //DingDingMessageUtil.sendTextMessage(msg);
    }

}