package com.rrx.kaoqins.admin.service;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Dict;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rrx.kaoqins.admin.dao.SysDict2Mapper;
import com.rrx.kaoqins.admin.dao.SysDictMapper;
import com.rrx.kaoqins.admin.dao.SysLogMapper;
import com.rrx.kaoqins.admin.model.SysDict;
import com.rrx.kaoqins.admin.model.SysLog;
import com.rrx.kaoqins.admin.param.DictParam;
import com.rrx.kaoqins.core.constant.CacheConsts;
import com.rrx.kaoqins.core.global.GeneRedisKey;
import com.rrx.kaoqins.core.log.Log;
import com.rrx.kaoqins.core.util.RedisHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
//@DS("jww2")
public class SysDict2Service extends ServiceImpl<SysDictMapper,SysDict> {

    @Autowired
    SysDict2Mapper sysDict2Mapper;

    //@Transactional
    public boolean save(SysDict dict) {
        sysDict2Mapper.insert(dict);
        return true;
    }

}
