package com.concurrent.chapter4;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 包名：com.concurrent.chapter4
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-19 上午9:45
 * 原子操作数演示示例
 */
public class AtomicLongTest {
    private static AtomicLong atomicLong = new AtomicLong();
    private static int[] one = {0, 1, 2, 3, 4, 5, 1, 0, 12, 0};
    private static Integer[] two = {8, 2, 37, 28, 0, 6, 2, 0, 37, 1, 0, 8, 3, 0};

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                int size = one.length;
                for (int i = 0; i < size; i++) {
                    if (one[i] == 0) {
                        atomicLong.incrementAndGet();
                    }
                }
            }
        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                int size = two.length;
                for (int i = 0; i < size; i++) {
                    if (two[i].intValue() == 0) {
                        atomicLong.incrementAndGet();
                    }
                }
            }
        });

        threadOne.start();
        threadTwo.start();

        threadOne.join();
        threadTwo.join();
        System.out.println("统计0值为：" + atomicLong.get());
    }
}
