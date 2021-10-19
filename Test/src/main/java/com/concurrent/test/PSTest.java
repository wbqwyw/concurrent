package com.concurrent.test;

import java.sql.Connection;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: concurrent
 * @description: 使用自定义的非重入锁实现简单生产者消费者模型
 * @author: Mr.Wang
 * @create: 2021-10-18 16:48
 **/
public class PSTest {
    // final static NonReentrantLock lock = new NonReentrantLock();
    final static ReentrantLock lock = new ReentrantLock();
    final static Condition notEmpty = lock.newCondition();
    final static Condition notFull = lock.newCondition();
    final static Queue<String> queue = new LinkedBlockingQueue<>();
    final static int queueSize = 10;

    public static void main(String[] args) {

        Thread productor = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    lock.lock();
                    try {
                        while (queue.size() == queueSize) {
                            notEmpty.await();
                        }
                        queue.add("yuyuy");
                        System.out.println("productor-----");
                        notFull.signalAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        });

        Thread customer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    lock.lock();
                    try {
                        while (0 == queue.size()) {
                            notFull.await();
                        }
                        queue.poll();
                        System.out.println("customer-----");
                        notEmpty.signalAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        });

        productor.start();
        customer.start();

    }
}
