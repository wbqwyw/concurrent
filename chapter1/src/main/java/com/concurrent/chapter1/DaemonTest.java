package com.concurrent.chapter1;

/**
 * 包名：com.concurrent.chapter1
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-16 下午3:42
 * 设置主线程示例
 */
public class DaemonTest {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("跑起来了");
            }
        });
        //必须启动之前设置为守护线程
        thread.setDaemon(true);
        thread.start();
    }
}
