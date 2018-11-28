package com.rrx.kaoqins.admin.service;


import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rrx.kaoqins.admin.dao.SysDict2Mapper;
import com.rrx.kaoqins.admin.dao.SysDictMapper;
import com.rrx.kaoqins.admin.model.SysDict;
import com.rrx.kaoqins.admin.param.DictParam;
import com.rrx.kaoqins.core.global.Const;
import com.rrx.kaoqins.core.global.GeneRedisKey;
import com.rrx.kaoqins.core.log.Log;
import com.rrx.kaoqins.core.util.RedisHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@CacheConfig(cacheNames = Const.CacheName.DICT)
public class SysDictService extends ServiceImpl<SysDictMapper,SysDict> {

    @Autowired
    RedisHelper redisHelper;

    @Autowired
    SysDictMapper sysDictMapper;

    @Autowired
    SysDict2Mapper sysDict2Mapper;

    @Cacheable(key = "#p0")
    public SysDict getById(Serializable id) {
        return super.getById(id);
    }

    public IPage page(DictParam dictParam) {
        return super.page(dictParam.getPage(),
               new QueryWrapper<SysDict>().eq("code",dictParam.getCode()));
    }

    public List<SysDict> list(DictParam dictParam) {
        return super.list(new QueryWrapper<SysDict>().eq("code",dictParam.getCode()));
    }

    public void mdb(){
        log.debug("=========切换数据库1==============");
        List<SysDict> allDict = sysDictMapper.getAll();
        allDict.forEach(System.out::println);
        log.debug("=========切换数据库2==============");
        List<SysDict> allDict2 = sysDict2Mapper.selectList(new QueryWrapper<>());
        allDict2.forEach(System.out::println);
    }

    @Transactional
    public boolean save(SysDict dict) {
        super.save(dict);
        return true;
    }

    @CacheEvict(key = "#p0.id")
    @Transactional
    public boolean modify(SysDict dict) {
        return saveOrUpdate(dict);
    }


    @CacheEvict(allEntries = true)
    public boolean deleteBatchIds(List<? extends Serializable> idList){
        List<SysDict> sysDicModelList = new ArrayList<SysDict>();
        idList.forEach(id -> {
            SysDict entity = new SysDict();
            entity.setId((Long) id);
            entity.setIsDel(1);
            entity.setUpdateTime(new Date());
            sysDicModelList.add(entity);
        });
        return true;//super.updateBatchById(sysDicModelList);
    }


    @Log("LOG--redis调用")
    public void redis(String id){
        Dict dict = Dict.create()
                .set("key1", 1)//int
                .set("key2", 1000L);//long
        redisHelper.set(GeneRedisKey.getSysDictKey(id),dict);
        Dict  dict1 = (Dict)redisHelper.get(GeneRedisKey.getSysDictKey(id));
        log.info("redis中存储"+dict1);
    }

}
