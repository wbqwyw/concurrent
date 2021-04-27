package com.concurrent.chapter11;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @program: concurrent
 * @description: 线程不安全演示
 * @author: Mr.Wang
 * @create: 2021-04-27 10:26
 * 抛出异常java.lang.NumberFormatException
 * 之所以抛出异常，是因为parse方法中存在的操作都是非原子操作，非线程安全
 * 要想解决：
 * 1.每个线程new一个SimpleDateFormat对象，但是创建，销毁，回收等开销大
 * 2.保证操作的原子性，加锁进行同步操作
 * 3.使用ThreadLocal，让每个线程独有一份
 **/
public class SimpleDateFormatTest1 {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(sdf.parse("2021-4-3 12:3:2"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
