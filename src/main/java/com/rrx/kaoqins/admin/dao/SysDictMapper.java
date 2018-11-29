package com.rrx.kaoqins.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rrx.kaoqins.admin.model.SysDict;
import com.rrx.kaoqins.admin.param.DictParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// @DS("jww")
public interface SysDictMapper extends BaseMapper<SysDict> {

    List<SysDict> getAll();

    IPage pageXML(Page page, @Param("dictParam") DictParam dictParam);
}
