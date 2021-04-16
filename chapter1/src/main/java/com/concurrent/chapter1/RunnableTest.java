package com.concurrent.chapter1;

/**
 * 包名：com.concurrent.chapter1
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-16 上午9:58
 */
public class RunnableTest implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " : RunnableTest");
    }

    public static void main(String[] args) {
        RunnableTest runnableTest = new RunnableTest();
        new Thread(runnableTest).start();
        new Thread(runnableTest).start();

    }
}
