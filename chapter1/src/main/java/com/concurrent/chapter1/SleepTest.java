package com.concurrent.chapter1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 包名：com.concurrent.chapter1
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-16 下午1:30
 */
public class SleepTest {

    public static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("threadA is sleeping");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                System.out.println("threadA is awaked");
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("threadB is sleeping");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                System.out.println("threadB is awaked");
            }
        });
        threadA.start();
        threadB.start();
    }
}
