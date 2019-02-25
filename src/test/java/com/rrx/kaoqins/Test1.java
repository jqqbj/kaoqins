package com.rrx.kaoqins;

import com.alibaba.druid.support.json.JSONUtils;
import com.rrx.kaoqins.admin.dao.SysDictMapper;
import com.rrx.kaoqins.admin.model.SysDict;
import com.rrx.kaoqins.admin.service.SysDictService;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


public class Test1 {

    public static void main(String[] args) {
        List<ReportGroupDto> list = new ArrayList<>();

//        list.add(new ReportGroupDto("1",null,"ROOT"));

//        list.add(new ReportGroupDto("QY",null,"企业"));
//        list.add(new ReportGroupDto("GR",null,"个人"));



        list.add(new ReportGroupDto("QY","DH","企业"));
        list.add(new ReportGroupDto("GR","DH","个人"));
        list.add(new ReportGroupDto("DH","1","贷后"));


//        list.add(new ReportGroupDto("100","1","学校A"));
//
//        list.add(new ReportGroupDto("1001","100","班级A1"));
//        list.add(new ReportGroupDto("1001001","1001","学生A1-1"));
//        list.add(new ReportGroupDto("1001002","1001","学生A1-2"));
//
//        list.add(new ReportGroupDto("1002","100","班级A2"));
//        list.add(new ReportGroupDto("1002001","1002","学生A2-1"));
//        list.add(new ReportGroupDto("1002001","1002","学生A2-2"));
//
//
//        list.add(new ReportGroupDto("200","1","学校B"));
//        list.add(new ReportGroupDto("2002","200","班级B1"));
//
//        list.add(new ReportGroupDto("2002001","200","班级B1"));
//
//
//        list.add(new ReportGroupDto("300","1","学校C"));

        List<ReportGroupDto> list2 = convertForTree(list);
        String json = JSONUtils.toJSONString(convertForTree(list2));
        System.out.println(json);
    }

    /*
     * 转换Tree
     */
    private static List<ReportGroupDto> convertForTree(List<ReportGroupDto> list) {
        List<ReportGroupDto> treeList = new ArrayList<ReportGroupDto>();
        for (ReportGroupDto tree : list) {
            //找到根节点
            if (tree.getLow() == null) {
                treeList.add(tree);
            }
            //找到子节点
            for (ReportGroupDto treeNode : list) {
                if (treeNode.getLow() == null || tree.getId() == null) {
                    continue;
                }
                if (treeNode.getLow().equals(tree.getId())) {
                    if (tree.getChildren() == null) {
                        tree.setChildren(new ArrayList<ReportGroupDto>());
                    }
                    tree.getChildren().add(treeNode);
                }
            }
        }
        return treeList;
    }

}
