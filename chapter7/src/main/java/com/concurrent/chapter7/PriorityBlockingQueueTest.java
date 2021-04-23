package com.concurrent.chapter7;

import org.junit.Test;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 包名：com.concurrent.chapter7
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-22 下午6:20
 */
public class PriorityBlockingQueueTest {
    @Test
    public void test() {
        System.out.println(0 << 1);
        System.out.println(1 << 1);
    }

    @Test
    public void test2() {
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<Integer>();
        queue.add(2);
        queue.add(4);
        queue.add(6);
        queue.add(8);
        queue.add(10);
        queue.add(11);
        System.out.println(queue.poll().intValue());
        System.out.println(queue.toString());
    }
}
