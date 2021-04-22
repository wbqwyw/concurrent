package com.concurrent.chapter6;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;

/**
 * 包名：com.concurrent.chapter6
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-21 上午8:58
 * 使用自定义的不可重入锁实现简单生产-消费模型
 */
public class ProductConsumerMode {
    final static NonReentratLock lock = new NonReentratLock();
    final static Condition notFull = lock.newCondition();
    final static Condition notEmpty = lock.newCondition();
    final static Queue<String> queue = new LinkedBlockingQueue<>();
    final static int size = 10;

    public static void main(String[] args) {
        Thread product = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while (queue.size() == size) {
                        notEmpty.await();
                    }
                    queue.add("hello");
                    notFull.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while (queue.size() == 0) {
                        notFull.await();
                    }
                    System.out.println(queue.poll());
                    notEmpty.signalAll();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        product.start();
        consumer.start();
    }
}
