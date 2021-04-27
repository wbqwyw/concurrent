package com.concurrent.chapter7;

/**
 * 包名：com.concurrent.chapter7
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-25 下午2:37
 */
public class Test {
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY = (1 << COUNT_BITS) - 1;

    private static final int RUNNING = -1 << COUNT_BITS;

    @org.junit.Test
    public void test1() {
        System.out.println(RUNNING | 0);
    }
}
