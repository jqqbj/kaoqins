package com.rrx.kaoqins;

import java.util.Arrays;
import java.util.List;

/**
 * @Author JQQ
 * @Date 2019/3/1 16:01
 */
public class stream {
    public static void main(String[] args) {
        //判断流中是否含有>10的项
        List<String> list = Arrays.asList("a","b");
        boolean result = list.stream().anyMatch(x->x.equals("ac"));
        System.out.println(result);
    }
}
