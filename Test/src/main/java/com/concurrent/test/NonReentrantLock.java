package com.concurrent.test;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @program: concurrent
 * @description: 自定义非重入锁
 * @author: Mr.Wang
 * @create: 2021-10-18 15:29
 **/
public class NonReentrantLock implements Lock, Serializable {

    private static class Sysnc extends AbstractQueuedSynchronizer {

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        @Override
        protected boolean tryAcquire(int arg) {
            assert arg == 1;
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            assert arg == 0;
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        ConditionObject newCondition() {
            return new ConditionObject();
        }

    }

    private final Sysnc sysnc = new Sysnc();

    @Override
    public void lock() {
        sysnc.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sysnc.acquireInterruptibly(1);
    }

    public boolean isLocked() {
        return sysnc.isHeldExclusively();
    }


    @Override
    public boolean tryLock() {
        return sysnc.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sysnc.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sysnc.release(1);
    }


    @Override
    public Condition newCondition() {
        return sysnc.newCondition();
    }
}
