package com.rrx.kaoqins.core.mybaits;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author JQQ
 */
@Slf4j
@Component
public class DefaultMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        //版本号3.0.6以及之前的版本
        setFieldValByName("updateBy", 1 , metaObject);
        setFieldValByName("updateTime", DateUtil.date(),metaObject);
        setFieldValByName("createBy",11,metaObject);
        setFieldValByName("createTime",DateUtil.date(),metaObject);
        String type =(String)getFieldValByName("type",metaObject);
        if(StrUtil.isBlank(type)){
            setFieldValByName("type","TEST",metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        setFieldValByName("updateBy", 1 , metaObject);
        setFieldValByName("updateTime",DateUtil.date(),metaObject);
        setFieldValByName("createBy",11,metaObject);
        setFieldValByName("createTime",DateUtil.date(),metaObject);
        String type =(String)getFieldValByName("type",metaObject);
        if(StrUtil.isBlank(type)){
            setFieldValByName("type","TEST",metaObject);
        }
    }
}
