package com.concurrent.chapter6;

import java.util.concurrent.locks.LockSupport;

/**
 * 包名：com.concurrent.chapter6
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-20 下午3:23
 */
public class ParkTest {
    public void testPark() {
        //使用LockSupport.park(Object blocker)方法可以获得更多的堆栈阻塞信息
        LockSupport.park(this);
    }

    public static void main(String[] args) {
        ParkTest parkTest = new ParkTest();
        parkTest.testPark();
    }
}
