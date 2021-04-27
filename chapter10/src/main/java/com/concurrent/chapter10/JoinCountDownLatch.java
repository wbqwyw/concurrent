package com.concurrent.chapter10;

import java.util.concurrent.CountDownLatch;

/**
 * 包名：com.concurrent.chapter10
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-26 上午9:46
 *
 * @author wbq
 * 所有子线程有序的完成之后，再执行主线程
 */
public class JoinCountDownLatch {

    private static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
                System.out.println("thread one over!");
            }
        });
        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
                System.out.println("thread two over!");
            }
        });
        System.out.println("wait for all child over!");
        one.start();
        two.start();

        countDownLatch.await();

        System.out.println("all child over!");
    }
}
