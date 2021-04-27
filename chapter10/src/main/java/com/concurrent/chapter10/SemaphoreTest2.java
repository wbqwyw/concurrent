package com.concurrent.chapter10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @program: concurrent
 * @description: Semaphore演示示例2
 * @author: Mr.Wang
 * @create: 2021-04-26 15:46
 **/
public class SemaphoreTest2 {
    private static Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ExecutorService executorService2 = Executors.newFixedThreadPool(2);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "task 1 over");
                semaphore.release();
            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "task 1 over");
                semaphore.release();
            }
        });
        semaphore.acquire(2);
        executorService2.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "task 2 over");
                semaphore.release();
            }
        });
        executorService2.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "task 2 over");
                semaphore.release();
            }
        });
        semaphore.acquire(2);
        System.out.println("all child thread over");
        executorService.shutdown();
        executorService2.shutdown();
    }
}
