package com.concurrent.chapter1;

/**
 * 包名：com.concurrent.chapter1
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-16 下午1:15
 */
public class JoinTest {
    public static void main(String[] args) throws InterruptedException {


        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("threadA over!");
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("threadB over!");
            }
        });
        threadA.start();
        threadB.start();
        System.out.println("wait all child is over!");
        threadA.join();
        threadB.join();
        System.out.println("All child over!");

    }

}
