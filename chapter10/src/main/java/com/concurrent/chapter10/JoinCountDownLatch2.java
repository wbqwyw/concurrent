package com.concurrent.chapter10;

import java.util.concurrent.*;

/**
 * 包名：com.concurrent.chapter10
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-26 上午9:30
 *
 * @author wbq
 * 所有子线程有序的完成之后，再执行主线程，通过线程池创建线程
 */
public class JoinCountDownLatch2 {
    //加不加volatile关键字都能得到预期效果
    private static volatile CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
                System.out.println("child one over!");
            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
                System.out.println("child two over!");
            }
        });
        System.out.println("wait for all child over!");
        countDownLatch.await();
        System.out.println("all child over!");
        executorService.shutdown();
    }
}
