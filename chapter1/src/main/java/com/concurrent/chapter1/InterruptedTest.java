package com.concurrent.chapter1;

/**
 * 包名：com.concurrent.chapter1
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-16 下午1:22
 */
public class InterruptedTest {
    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadA begin run");
                for (; ; ) {
                }
            }
        });

        final Thread mainThread = Thread.currentThread();
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mainThread.interrupt();
            }
        });
        threadA.start();
        threadB.start();

        try {
            threadA.join();
        } catch (InterruptedException e) {
            System.out.println("main thread : " + e);
        }

    }
}
