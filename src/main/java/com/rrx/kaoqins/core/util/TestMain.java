package com.rrx.kaoqins.core.util;

import cn.hutool.core.convert.Convert;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author JQQ
 * @Date 2018/12/3 16:22
 */
public class TestMain {
    public static void main(String[] args) throws Exception {
//        int s = 32%3;
//        System.out.println(s);
//        try {
//            int s =  4/4;
//            System.out.println("---");
//            throw new Exception("xxxxxxxxxxxxxx");
//        }  finally {
//            System.out.println("finally");
//        }
        boolean isAdmin = "Administrator".equals("MBR_ADMIN4")||"MBR_ADMIN".equals("MBR_ADMIN4")?true:false;
        System.out.println(isAdmin);

//        ThreadUtil.createThreadLocal(true).

        long s = Convert.toLong("100");
        System.out.println(s);


        String url  = "1+1?2";

        System.out.println(URLEncoder.encode(url));

        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);

        for (Object o : list) {
            for (Object o1 : list) {
                System.out.println(o+" "+o1);
            }
        }


    }





}
