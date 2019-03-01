package com.rrx.kaoqins.admin.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rrx.kaoqins.admin.dao.SysDict2Mapper;
import com.rrx.kaoqins.admin.dao.SysDictMapper;
import com.rrx.kaoqins.admin.dao.SysLogMapper;
import com.rrx.kaoqins.admin.model.SysDict;
import com.rrx.kaoqins.admin.model.SysLog;
import com.rrx.kaoqins.core.util.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SysLogService extends ServiceImpl<SysLogMapper,SysLog> {

    @Autowired
    SysLogMapper sysLogMapper;

    public boolean save() {
        try {
            List<String> sqlList = ThreadLocalUtil.get("logsql");
            sqlList.forEach(System.out::println);
            // sysLogMapper.insert(sysLog);
        }finally {
            ThreadLocalUtil.remove();
        }
        return true;
    }

}
