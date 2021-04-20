package com.concurrent.chapter6;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * 包名：com.concurrent.chapter6
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-20 下午3:43
 */
public class FIFOMutex {
    private final AtomicBoolean locked = new AtomicBoolean();
    private final Queue<Thread> waiters = new ConcurrentLinkedDeque<>();

    public void run() {
        boolean wasInterrupted = false;
        Thread concurrent = Thread.currentThread();
        waiters.add(concurrent);
        if (waiters.peek() != concurrent || !locked.compareAndSet(false, true)) {
            LockSupport.park(this);
            if (Thread.interrupted()) {
                wasInterrupted = true;
            }
        }
        waiters.remove();
        if (wasInterrupted) {
            concurrent.interrupt();
        }
    }

    public void unLock() {
        locked.set(false);
        LockSupport.unpark(waiters.peek());
    }
}
