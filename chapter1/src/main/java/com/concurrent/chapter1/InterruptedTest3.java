package com.concurrent.chapter1;

/**
 * 包名：com.concurrent.chapter1
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-16 下午2:51
 */
public class InterruptedTest3 {
    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()) {
                }
                System.out.println("isInterrupted :: " + Thread.currentThread() + " ::: " + Thread.currentThread().isInterrupted());
            }
        });
        threadA.start();
        threadA.interrupt();
        threadA.join();
        System.out.println("main is over ");
    }

}
