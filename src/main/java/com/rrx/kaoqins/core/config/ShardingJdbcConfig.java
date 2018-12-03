package com.rrx.kaoqins.core.config;

import com.baomidou.dynamic.datasource.DynamicDataSourceCreator;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.google.common.collect.Maps;
import io.shardingjdbc.core.api.ShardingDataSourceFactory;
import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

@Configuration
public class ShardingJdbcConfig implements DynamicDataSourceProvider {
        private static final Logger log = LoggerFactory.getLogger(com.baomidou.dynamic.datasource.provider.YmlDynamicDataSourceProvider.class);
        private DynamicDataSourceProperties properties;
        private DynamicDataSourceCreator dynamicDataSourceCreator;

        public ShardingJdbcConfig(DynamicDataSourceProperties properties, DynamicDataSourceCreator dynamicDataSourceCreator) {
            this.properties = properties;
            this.dynamicDataSourceCreator = dynamicDataSourceCreator;
        }

        public Map<String, DataSource> loadDataSources() {
            Map<String, DataSourceProperty> dataSourcePropertiesMap = this.properties.getDatasource();
            Map<String, DataSource> dataSourceMap = new HashMap(dataSourcePropertiesMap.size());
            Iterator var3 = dataSourcePropertiesMap.entrySet().iterator();

            while(var3.hasNext()) {
                Map.Entry<String, DataSourceProperty> item = (Map.Entry)var3.next();
                String pollName = (String)item.getKey();
                DataSourceProperty dataSourceProperty = (DataSourceProperty)item.getValue();
                dataSourceProperty.setPollName(pollName);
                //对JWW数据库采用sharingjdbc驱动
                if(pollName.equals("jww")){
                    Map<String, DataSource> shardingDataSourceMaps = Maps.newHashMap();
                    shardingDataSourceMaps.put(pollName,this.dynamicDataSourceCreator.createDataSource(dataSourceProperty));
                    ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
                    //shardingRuleConfig.setTableRuleConfig(getOrderTableRuleConfiguration());
                    try {
                        dataSourceMap.put(pollName,ShardingDataSourceFactory.createDataSource(shardingDataSourceMaps,shardingRuleConfig, new HashMap<>(1), new Properties()));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }else{
                    dataSourceMap.put(pollName, this.dynamicDataSourceCreator.createDataSource(dataSourceProperty));
                }
            }
            return dataSourceMap;
        }

        /**
         * 分表策略
         * @return
         */
        private TableRuleConfiguration getOrderTableRuleConfiguration() {
            TableRuleConfiguration rule = new TableRuleConfiguration();
            //逻辑表名称
            rule.setLogicTable("T_ORDER");
            //源名 + 表名
            rule.setActualDataNodes("ds0.T_ORDER_$->{2018..2019}_$->{['01','08','12']}");
            // 表分片策略
    //        StandardShardingStrategyConfiguration strategyConfiguration =
    //                new StandardShardingStrategyConfiguration("month", new MonthTableShardingAlgorithm());
    //        rule.setTableShardingStrategyConfig(strategyConfiguration);
            //自增列名称
            rule.setKeyGeneratorColumnName("id");
            return rule;
        }


    }
