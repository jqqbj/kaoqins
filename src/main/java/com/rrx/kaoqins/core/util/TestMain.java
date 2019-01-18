package com.rrx.kaoqins.core.util;

import cn.hutool.core.thread.ThreadUtil;

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

    }





}
