package com.concurrent.chapter6;

import java.util.concurrent.locks.LockSupport;

/**
 * 包名：com.concurrent.chapter6
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-20 下午3:04
 * park方法返回时，是不会告诉是什么原因返回的
 */
public class LockSupportTest2 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread begin !");
                LockSupport.park();
                System.out.println("thread end !");
            }
        });
        thread.start();
        Thread.sleep(1000);
        System.out.println("main is beging");
        LockSupport.unpark(thread);
    }
}
