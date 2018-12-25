package com.rrx.kaoqins;

import com.rrx.kaoqins.admin.dao.SysDictMapper;
import com.rrx.kaoqins.admin.model.SysDict;
import com.rrx.kaoqins.admin.service.SysDictService;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author JQQ
 * @Date 2018/11/27 10:12
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTest {

    @Autowired
    SysDictMapper sysDictMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<SysDict> userList = sysDictMapper.selectList(null);
        //Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }


    @Autowired
    SysDictService sysDictService;

    @Test
    public void shardQuery() {
        sysDictService.shardQuery(null);
    }


    @Autowired
    StringEncryptor stringEncryptor;

    @Test
    public void encryptPwd() {
        String result = stringEncryptor.encrypt("root");
        System.out.println(result);
    }


    //引入 ContiPerf 进行性能测试
    @Rule
    public ContiPerfRule contiPerfRule = new ContiPerfRule();


    @Test
    //@Required(max = 1200, average = 250, totalTime = 60000)
    @PerfTest(invocations = 50,threads = 5)  //5个线程 执行50次
    public void testMultiThread() {
        String msg = "this is a test";
        List<SysDict> userList = sysDictMapper.selectList(null);
        System.out.println("线程："+Thread.currentThread().getName()+"，size："+userList.size());
    }

}
