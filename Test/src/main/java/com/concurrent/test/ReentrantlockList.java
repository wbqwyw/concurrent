package com.concurrent.test;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: concurrent
 * @description: 使用可重入锁构建线程安全的List集合
 * @author: Mr.Wang
 * @create: 2021-10-22 10:42
 **/
public class ReentrantlockList {

    private ArrayList list = new ArrayList<Object>();
    private volatile ReentrantLock lock = new ReentrantLock();

    public void add(Object object) {
        lock.lock();
        try {
            list.add(object);
        } finally {
            lock.unlock();
        }
    }

    public void remove(Object object) {
        lock.lock();
        try {
            list.remove(object);
        } finally {
            lock.unlock();
        }
    }

    public Object get(int index) {
        lock.lock();
        try {
            return list.get(index);
        } finally {
            lock.unlock();
        }
    }
}
