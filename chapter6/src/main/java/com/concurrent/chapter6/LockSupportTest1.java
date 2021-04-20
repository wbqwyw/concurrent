package com.concurrent.chapter6;

import java.util.concurrent.locks.LockSupport;

/**
 * 包名：com.concurrent.chapter6
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-20 下午2:57
 * LockSupport演示线程与许可的关系
 * 调用unpark时，如果线程没有持有与LockSupport的关联许可时，则让线程持有
 * 当unpark时，如果没park过，则调用unpark后，再调用park会立刻返回
 * 当unpark时，如果park过，则调用unpark后，该线程会被唤醒
 */
public class LockSupportTest1 {
    public static void main(String[] args) {
        System.out.println("begin");
        LockSupport.unpark(Thread.currentThread());
        LockSupport.park();
        System.out.println("end");
    }
}
