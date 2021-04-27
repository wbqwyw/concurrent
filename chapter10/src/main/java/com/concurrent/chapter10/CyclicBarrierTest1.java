package com.concurrent.chapter10;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 包名：com.concurrent.chapter10
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-26 下午1:51
 *
 * @author wbq
 * 回环屏障示例演示，
 * 屏障点await()
 * 在屏障点之前是按照线程先后顺序执行完之后，才会继续执行
 */
public class CyclicBarrierTest1 {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread() + " task1 merge result");
        }
    });

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "task1-1");
                System.out.println(Thread.currentThread() + " enter in barrier");
                try {
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread() + " out barrier ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "task1-2");
                System.out.println(Thread.currentThread() + "enter in barrier");
                try {
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread() + "out barrier");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.shutdown();
    }
}
