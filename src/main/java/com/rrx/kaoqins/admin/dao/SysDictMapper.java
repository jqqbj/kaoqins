package com.rrx.kaoqins.admin.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rrx.kaoqins.admin.model.SysDict;

import java.util.List;

// @DS("jww")
public interface SysDictMapper extends BaseMapper<SysDict> {

    List<SysDict> getAll();

}
