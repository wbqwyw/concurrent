package com.concurrent.chapter6;

import java.util.concurrent.locks.LockSupport;

/**
 * 包名：com.concurrent.chapter6
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-20 下午3:10
 * 为了说明调用park方法的线程被中断后会返回
 * 如下代码，之后调用interrupt()方法才会让子线程返回，否则即使调用unpark()方法，也不会让子线程返回的。
 */
public class LockSupportTest3 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(" thread begin!");
                while (!Thread.currentThread().isInterrupted()) {
                    LockSupport.park();
                }
                System.out.println("thread unpark!");
            }
        });
        thread.start();
        Thread.sleep(1000);
        System.out.println("main thread begin unpark!");
        thread.interrupt();
    }
}
