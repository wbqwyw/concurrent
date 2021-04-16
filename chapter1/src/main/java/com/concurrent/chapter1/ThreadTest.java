package com.concurrent.chapter1;


/**
 * 包名：com.concurrent.chapter1
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-16 上午9:49
 */
public class ThreadTest extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " : ThreadTest");
    }

    ThreadTest(String name) {
        Thread.currentThread().setName(name);
    }

    ThreadTest() {

    }

    public static void main(String[] args) {
        new ThreadTest().start();
        new ThreadTest().start();

    }


}
