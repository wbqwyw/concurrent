package com.concurrent.chapter10;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 包名：com.concurrent.chapter10
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-26 下午2:08
 *
 * @author wbq
 * 创建回环屏障时，如果传参线程。
 * 那么当所有子线程都到达一个屏障点的之后，会执行这个参数线程。
 * 每次到达屏障点都会执行，直至线程终止
 */
public class CyclicBarrierTest2 {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread() + "task1 merge result");
        }
    });

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread() + "step1");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread() + "step2");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread() + "step3");
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
                try {
                    System.out.println(Thread.currentThread() + "step1");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread() + "step2");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread() + "step3");
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
