package com.rrx.kaoqins.admin.web;


import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rrx.kaoqins.admin.dto.SysDictDto;
import com.rrx.kaoqins.admin.model.SysDict;
import com.rrx.kaoqins.admin.param.DictParam;
import com.rrx.kaoqins.admin.service.SysDictService;
import com.rrx.kaoqins.core.web.model.ResultModel;
import com.rrx.kaoqins.core.web.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dict")
public class DictController {

    @Autowired
    SysDictService sysDictService;

    /**
     * 根据字典ID查询字典
     */
    @GetMapping("/query/{id}")
    public ResultModel query(@PathVariable("id")Integer id) {
        Assert.isTrue(Validator.isNotEmpty(id),"ID不可以为空");
        SysDict sysDic = sysDictService.getById(id);
        return ResultUtil.ok(SysDictDto.model2dto(sysDic));
    }

    /**
     * 分页查询
     */
    @PostMapping("/page")
    public ResultModel page(DictParam param) {
        IPage page = sysDictService.page(param);
        page.setRecords(SysDictDto.model2dto(page.getRecords()));
        return ResultUtil.ok(page);
    }

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResultModel list(DictParam param) {
        List<SysDict> list = sysDictService.list(param);
        return ResultUtil.ok(SysDictDto.model2dto(list));
    }

    /**
     * 添加
     */
    @PostMapping("/add")
    public ResultModel add(SysDict sysDict){
        Assert.isTrue(Validator.isNumber(sysDict.getCode()),"code必须为数字");
        Assert.isTrue(Validator.isNotEmpty(sysDict.getCodeText()),"codeText不可以为空");
        sysDictService.save(sysDict);
        return ResultUtil.ok(sysDict.getId());
    }

    /**
     * 修改
     */
    @PostMapping("/modify")
    public ResultModel modify(SysDict sysDict){
        sysDictService.modify(sysDict);
        return ResultUtil.ok(sysDict.getId());
    }

    /**
     * 多数据源
     */
    @GetMapping("/mdb")
    public ResultModel mdb(){
        sysDictService.mdb();
        return ResultUtil.ok();
    }

    /**
     * 批量删除
     * 提交方式为application/json,如 [10,20]
     */
    @PostMapping("/del")
    public ResultModel delBatchByIds(@RequestBody List<Long> ids) {
        Assert.isTrue(ids.size()!=0,"字典ID集合不能为空");
        return ResultUtil.ok(sysDictService.deleteBatchIds(ids));
    }

    /**
     * Redis
     */
    @GetMapping("/redis")
    public ResultModel redis(String id){
        sysDictService.redis(id);
        return ResultUtil.ok();
    }

    /**
     * 测试分布式session
     */
    @GetMapping("/session")
    public ResultModel session(HttpSession session){
        session.setAttribute("loginname",UUID.randomUUID());
        return ResultUtil.ok(session.getId());
    }

}
