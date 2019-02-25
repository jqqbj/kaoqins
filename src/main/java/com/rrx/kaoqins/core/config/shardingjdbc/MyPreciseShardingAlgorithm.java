package com.rrx.kaoqins.core.config.shardingjdbc;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

/**
 * @Author JQQ
 */
@Slf4j
public class MyPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Integer> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Integer> preciseShardingValue) {
        Integer value = preciseShardingValue.getValue();
        for (String name : collection) {
            if (name.endsWith(Integer.valueOf(value) % collection.size() + "")) {
                log.info("return table_name:"+name);
                return name;
            }
        }
        return null;
    }
}
