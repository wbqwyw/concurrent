package com.concurrent.test;

import java.util.concurrent.locks.LockSupport;

/**
 * @program: concurrent
 * @description: LockSupportlistTest
 * @author: Mr.Wang
 * @create: 2021-10-14 17:18
 **/
public class LockSupportlistTest {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            System.out.println(" child begin lock!");
            while (!Thread.currentThread().isInterrupted()){
                LockSupport.park();
            }
            System.out.println(" child end lock!");
        });

        thread.start();

        Thread.sleep(1000);
        System.out.println(" main begin");
        // LockSupport.unpark(thread);
        thread.interrupt();

    }
}
