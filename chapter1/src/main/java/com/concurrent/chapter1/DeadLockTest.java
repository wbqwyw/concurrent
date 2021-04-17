package com.concurrent.chapter1;

/**
 * 包名：com.concurrent.chapter1
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-16 下午3:18
 */
public class DeadLockTest {
    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread() + " get resourceA lock");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread() + " wating for resourceB ");
                    synchronized (resourceB) {
                        System.out.println(Thread.currentThread() + " get resourceB lock");
                    }
                }
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceB) {
                    System.out.println(Thread.currentThread() + " get resourceB lock");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread() + " wating for resourceA ");
                    synchronized (resourceA) {
                        System.out.println(Thread.currentThread() + " get resourceA lock");
                    }
                }
            }
        });
        threadA.start();
        threadB.start();

    }
}
