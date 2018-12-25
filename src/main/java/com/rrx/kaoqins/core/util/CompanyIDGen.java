package com.rrx.kaoqins.core.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 公司统一的ID生成器
 */
@Slf4j
public class CompanyIDGen {
	
    /**
     * 存放一批ID
     */
    private static ConcurrentLinkedQueue<Long> iDQueue = new ConcurrentLinkedQueue();

    public static synchronized long getNextId() {
        if(iDQueue.size() == 0){
            load();
        }
        //获取并移除此队列的头，如果此队列为空，则返回 null。
        Long id = iDQueue.poll();
        if(id == null || id.longValue() == 0){
            log.info("get next id is empty");
        }
        return id;
    }

    private static void load(){
        List<Long> idList = Collections.emptyList(); //CompanyCommIDApi.batchID();
        if(idList == null || idList.size() == 0){
            return;
        }
        for(Long id : idList){
            //将指定元素插入此队列的尾部。
            iDQueue.add(id);
        }
    }
}