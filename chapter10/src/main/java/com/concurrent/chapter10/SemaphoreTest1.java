package com.concurrent.chapter10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @program: concurrent
 * @description: Semaphore演示示例
 * @author: Mr.Wang
 * @create: 2021-04-26 15:40
 **/
public class SemaphoreTest1 {
    private static Semaphore semaphore = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "over");
                semaphore.release();
            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "over");
                semaphore.release();
            }
        });
        semaphore.acquire(2);
        System.out.println("all child thread over");
        executorService.shutdown();
    }
}
