package com.concurrent.chapter1;

/**
 * 包名：com.concurrent.chapter1
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-16 下午1:41
 * 线程阻塞状态下，调用中断方法interrupt 则抛出异常InterruptedException
 */
public class SleepInterruptTest {
    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("threadA is sleeping");
                    Thread.sleep(10000);
//                    System.out.println("threadA is awaked");
                } catch (InterruptedException e) {
                    System.out.println("threadA is interrupted awaked");
                    return;
                }
                System.out.println("threadA is normally awaked");
            }
        });

        threadA.start();
        Thread.sleep(2000);
        //主线程中断子线程
        threadA.interrupt();
        threadA.join();
        System.out.println("main is over");
    }
}
