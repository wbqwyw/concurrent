package com.concurrent.chapter11;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: concurrent
 * @description: 使用加锁的方式解决线程不安全的问题
 * @author: Mr.Wang
 * @create: 2021-04-27 11:31
 **/
public class SimpleDateFormatTest3 {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            es.submit(new Runnable() {
                @Override
                public void run() {
                    synchronized (sdf) {
                        try {
                            System.out.println(Thread.currentThread().getName() + "{  }" + sdf.parse("2020-3-4 12:3:2"));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        es.shutdown();
    }
}
