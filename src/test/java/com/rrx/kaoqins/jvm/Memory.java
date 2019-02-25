package com.rrx.kaoqins.jvm;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Memory {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i <100 ; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("sss");
                }
            });
        }
        executorService.shutdown();
    }
}
