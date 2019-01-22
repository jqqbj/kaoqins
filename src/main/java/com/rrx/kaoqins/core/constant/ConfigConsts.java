package com.rrx.kaoqins.core.constant;

import cn.hutool.core.lang.Dict;

import java.util.Map;

public class ConfigConsts {

//    public static List<String> weakliest = Collections.synchronizedList(new ArrayList<String>());
//    public static ConcurrentHashMap map = new ConcurrentHashMap();
//    static {
//        weakliest.add("星期一");
//        weakliest.add("星期二");
//    }

    public static Map<String, Object> getWeek() {
        return Dict.create()
                .set("1", "星期一")
                .set("2", "星期二")
                .set("3", "星期三")
                .set("4", "星期四")
                .set("5", "星期五")
                .set("6", "星期六")
                .set("7", "星期日");
    }

}
