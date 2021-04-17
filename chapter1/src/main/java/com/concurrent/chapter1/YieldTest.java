package com.concurrent.chapter1;

/**
 * 包名：com.concurrent.chapter1
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-16 下午1:50
 */
public class YieldTest implements Runnable {
    YieldTest() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            if (i % 5 == 0) {
                System.out.println(Thread.currentThread() + " yield CPU");
                Thread.yield();
            }
        }
        System.out.println(Thread.currentThread() + "over!");
    }

    public static void main(String[] args) {
        new YieldTest();
        new YieldTest();
        new YieldTest();
    }
}
