package com.concurrent.chapter1;

/**
 * 包名：com.concurrent.chapter1
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-16 下午2:09
 */
public class IsInterruptTest {

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread() + "::: hello");
                }
            }
        });
        threadA.start();
        Thread.sleep(1000);
        threadA.interrupt();
        threadA.join();
        System.out.println("main over!");
    }

}
