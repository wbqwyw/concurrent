package com.concurrent.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: concurrent
 * @description: 可重入读写锁测试，写锁需要等到读锁释放才能获取
 * @author: Mr.Wang
 * @create: 2021-11-11 11:05
 **/
public class ReentrantReadWriteLockTest {

    public static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public static ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    public static ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            System.out.println("a线程开始获取读锁" + readWriteLock.getReadLockCount());
            readLock.lock();
            System.out.println("a线程获取到读锁" + readWriteLock.getReadLockCount());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("a线程尝试获取写锁" + readWriteLock.getWriteHoldCount());
            writeLock.lock();
            System.out.println("a获取到写锁" + readWriteLock.getWriteHoldCount());
        });
        t1.setName("t1");
        t1.start();
        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("b线程开始获取读锁" + readWriteLock.getReadLockCount());
            readLock.lock();
            System.out.println("b线程获取到读锁" + readWriteLock.getReadLockCount());
            try {
                TimeUnit.SECONDS.sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            readLock.unlock();
            System.out.println("b线程释放读锁" + readWriteLock.getReadLockCount());
        });
        t2.setName("t2");
        t2.start();
        Thread t3 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("c线程开始获取读锁" + readWriteLock.getReadLockCount());
            readLock.lock();
            System.out.println("c线程获取到读锁" + readWriteLock.getReadLockCount());
            try {
                TimeUnit.SECONDS.sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            readLock.unlock();
            System.out.println("c线程释放读锁" + readWriteLock.getReadLockCount());
        });
        t3.setName("t3");
        t3.start();

    }
}
