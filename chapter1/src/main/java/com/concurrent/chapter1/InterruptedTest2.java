package com.concurrent.chapter1;

/**
 * 包名：com.concurrent.chapter1
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-16 下午2:19
 */
public class InterruptedTest2 {
    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                }
            }
        });
        threadA.start();
        threadA.interrupt();
        System.out.println("isInterrupted:: " + threadA.isInterrupted());
        System.out.println("isInterrupted:: " + Thread.interrupted());
        System.out.println("isInterrupted:: " + threadA.isInterrupted());
        threadA.join();
        System.out.println("main is over");
    }
}
