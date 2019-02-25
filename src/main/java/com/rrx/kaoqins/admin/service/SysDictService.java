package com.rrx.kaoqins.admin.service;


import cn.hutool.core.lang.Dict;
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
@CacheConfig(cacheNames = CacheConsts.CACHE_DICT)
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

    //AR方式
    public SysDict getById2(Serializable id) {
        SysDict dict = new  SysDict();
        dict.setId((Long)id);
        return dict.selectById();
    }


    public IPage page(DictParam dictParam) {
        //XML方式
        //return sysDictMapper.pageXML((Page) dictParam.getPage(),dictParam);
        //Wrapper方式
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


    /*
      添加Transactional注解，无法实现切换数据库源，切换数据源与事务二选一，或采用分布式事务处理
      如果您只是几个数据库但是有强烈的需求分布式事物，建议还是使用传统方式自己构建多套环境集成atomic这类
     */
    //@Transactional
    public boolean save(SysDict dict) {
        sysDictMapper.insert(dict);
        sysDict2Mapper.insert(dict);
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

    /*
    * 分片查询
     */
    public void shardQuery(DictParam dictParam) {
        List<SysDict> all = new ArrayList<>();
        Wrapper<SysDict> wrapper = new QueryWrapper<SysDict>().eq("code",dictParam.getCode());
        int pageNo = 1;
        int pageSize = 2;
        int totalRow =super.count(wrapper);
        int totalPage = (totalRow+pageSize-1)/pageSize;
        while (pageNo<=totalPage){
            IPage<SysDict> page =  super.page(new Page<SysDict>(pageNo,pageSize),wrapper);
            log.debug("当前页码{}",pageNo);
            page.getRecords().forEach(e -> System.out.println(e.toString()));
            pageNo++;
            all.addAll(page.getRecords());
        }
        log.debug("查询总记录数{},集合返回{}条",totalRow,all.size());
    }


    @Autowired
    SysLogMapper sysLogMapper;

    /*
     * 分表查询
     */
    public void queryLog() {
        //全表查询
        List<SysLog> list =  sysLogMapper.selectList(null);
        list.forEach(System.out::println);
        //单个查询
        sysLogMapper.selectById(1);
    }

    /*
     * 分表插入
     */
    @Transactional
    public void saveLog(SysLog sysLog) {
        sysLogMapper.insert(sysLog);
    }

}
