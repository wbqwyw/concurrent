package com.concurrent.chapter1;

/**
 * 包名：com.concurrent.chapter1
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-16 下午4:47
 * ThreadLocal没有继承性
 */
public class ThreadLocalTest1 {
    private static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("main set");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread :: " + threadLocal.get());
            }
        });
        //这个是往主线程的threadLocal中放值
        thread.start();
        System.out.println("main :: " + threadLocal.get());
    }
}
