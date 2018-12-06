package com.rrx.kaoqins.core.config.shardingjdbc;

import com.baomidou.dynamic.datasource.DynamicDataSourceCreator;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.google.common.collect.Maps;
import io.shardingjdbc.core.api.ShardingDataSourceFactory;
import io.shardingjdbc.core.api.config.ShardingRuleConfiguration;
import io.shardingjdbc.core.api.config.TableRuleConfiguration;
import io.shardingjdbc.core.api.config.strategy.StandardShardingStrategyConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

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
                    try {
                        Properties prop = new Properties();
                        prop.setProperty("sql.show","true");
                        dataSourceMap.put(pollName,ShardingDataSourceFactory.createDataSource(shardingDataSourceMaps,getOrderTableRuleConfiguration(), new HashMap<>(1), prop));
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
        private ShardingRuleConfiguration getOrderTableRuleConfiguration() {
            TableRuleConfiguration rule = new TableRuleConfiguration();
            rule.setLogicTable("sys_log");
            rule.setActualDataNodes("jww.sys_log_${0..1}");
            // 配置分表策略
            rule.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("id",MyPreciseShardingAlgorithm.class.getName()));
            // 配置分片规则
            rule.setKeyGeneratorColumnName("id");
            ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
            shardingRuleConfig.getTableRuleConfigs().add(rule);
            return shardingRuleConfig;
        }

    }
