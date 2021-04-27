package com.concurrent.chapter11;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @program: concurrent
 * @description: 每个线程创建一个SimpleDateFormat对象解决线程不安全的问题
 * @author: Mr.Wang
 * @create: 2021-04-27 11:26
 **/
public class SimpleDateFormatTest2 {

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        System.out.println(Thread.currentThread().getName() + "「   」" + sdf.parse("2020-3-4 12:3:2"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
