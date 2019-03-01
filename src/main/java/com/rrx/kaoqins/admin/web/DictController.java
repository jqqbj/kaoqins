package com.rrx.kaoqins.admin.web;


import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Validator;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rrx.kaoqins.admin.dto.SysDictDto;
import com.rrx.kaoqins.admin.model.SysDict;
import com.rrx.kaoqins.admin.model.SysLog;
import com.rrx.kaoqins.admin.param.DictParam;
import com.rrx.kaoqins.admin.service.IProviderService;
import com.rrx.kaoqins.admin.service.SysDictService;
import com.rrx.kaoqins.core.web.model.ResultModel;
import com.rrx.kaoqins.core.web.util.ResultUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@Slf4j
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
        Assert.isTrue(Validator.isNotEmpty(id),"ID不可以为 空");
        SysDict sysDic = sysDictService.getById(id);
        return ResultUtil.ok(SysDictDto.model2dto(sysDic));
    }

    /**
     * 分页查询
     */
    @ApiOperation(value="获取字典列表", notes="只是测试")
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
        //List<SysDict> list = sysDictService.list(param);
        List<SysDict> list = sysDictService.listCache();
        return ResultUtil.ok(SysDictDto.model2dto(list));
    }


    @GetMapping("/clear")
    public ResultModel clear() {
        sysDictService.clearCache();
        return ResultUtil.ok();
    }

    /**
     * 添加
     */
    @PostMapping("/add")
    @ApiOperation(value="添加字典", notes="创建字典")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "类型", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "code", value = "编号", required = true,dataType = "string", paramType = "query")
    })
    public ResultModel add(SysDict sysDict){
        Assert.isTrue(Validator.isNumber(sysDict.getCode()),"code必须为数字");
        Assert.isTrue(Validator.isNotEmpty(sysDict.getCodeText()),"codeText不可以为空");
        sysDictService.save(sysDict);
        return ResultUtil.ok(sysDict.getId());
    }

    @PostMapping("/valid")
    @ApiOperation(value="验证字典", notes="验证字典")
    public ResultModel valid(@Valid DictParam dictParam){
        return ResultUtil.ok(dictParam);
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
    @RequestMapping("/redis")
    public ResultModel redis(String id){
        sysDictService.redis(id);
        return ResultUtil.ok();
    }

    /**
     * 测试分布式session
     */
    @GetMapping("/session")
    public ResultModel session(HttpSession session){
        System.out.println("xx666x----5555-");
        session.setAttribute("loginname",UUID.randomUUID());
        return ResultUtil.ok(session.getId());
    }

    //-----------------------------sharding jdbc -------------------------
    /**
     * 分表查询
     */
    @GetMapping("/log/list")
    public ResultModel queryLog(){
        sysDictService.queryLog();
        return ResultUtil.ok();
    }

    /**
     * 分表插入
     */
    @PostMapping("/log/save")
    public ResultModel saveLog(SysLog sysLog){
        sysDictService.saveLog(sysLog);
        return ResultUtil.ok();
    }

    /**
     * 资源监控
     */

    /**
     * 调用Dubbo服务端
     */
    @Autowired
    private IProviderService providerService2;

    @GetMapping("/say")
    @HystrixCommand(fallbackMethod = "reliable")
    public String doSayHello(String name) {
        return providerService2.sayHello(name);
    }

    public String reliable(String name) {
        return "hystrix fallback value";
    }


    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime().maxMemory()/1024/1024);
        System.out.println(Runtime.getRuntime().freeMemory()/1024/1024);
        System.out.println(Runtime.getRuntime().totalMemory()/1024/1024);
        List<String> names = new ArrayList<String>() {
            {
                for (int i = 0; i < 10; i++) {
                    add("A" + i);
                }
            }
        };
        System.out.println(names.toString());

        Map<String, String> map = new HashMap<String, String>() {{
            put("name", "test");
            put("age", "20");
        }};

    }

}
