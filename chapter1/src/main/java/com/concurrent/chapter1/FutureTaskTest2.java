package com.concurrent.chapter1;

import java.util.concurrent.*;

/**
 * @program: concurrent
 * @description: 线程池的方式调用Future接口
 * @author: Mr.Wang
 * @create: 2021-05-14 10:07
 **/
public class FutureTaskTest2 {

    private static class RunnableTest implements Runnable {

        @Override
        public void run() {
            System.out.println(2);
        }
    }

    private static class CallableTest implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            return 3;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        Future<Integer> submit = es.submit(new CallableTest());
//        FutureTask futureTask = new FutureTask<>(new RunnableTest());

        Future submit2 = es.submit(new RunnableTest());

        System.out.println(submit.get());
        System.out.println(submit2.get());

    }

}
