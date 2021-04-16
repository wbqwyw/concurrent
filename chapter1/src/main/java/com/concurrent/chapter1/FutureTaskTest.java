package com.concurrent.chapter1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 包名：com.concurrent.chapter1
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-16 上午10:33
 */
public class FutureTaskTest {
    static class CallAbleTest implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "Used CallAbleTest";
        }
    }

    static class RunableTest implements Runnable {
        @Override
        public void run() {
            System.out.println("Used RunableTest");
        }
    }

    public static void main(String[] args) {
        //只有调用get方法的时候才会得到call的结果
        FutureTask<String> futureTask1 = new FutureTask<>(new CallAbleTest());
        //线程执行时，会后台执行run方法，当调用get的时候，返回的是
        //创建FutureTest对象时，传入的第二个参数result的值，而不是run的返回值
        FutureTask<String> futureTask2 = new FutureTask<String>(new RunableTest(), "ok");
//        FutureTask<String> futureTask2 = new FutureTask<String>(new RunableTest(), null);
        new Thread(futureTask1).start();
        new Thread(futureTask2).start();

        try {
            String f1 = futureTask1.get();
            String f2 = futureTask2.get();
            System.out.println("f1:" + f1);
            System.out.println("f2:" + f2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
