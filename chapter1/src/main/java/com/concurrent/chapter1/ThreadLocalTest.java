package com.concurrent.chapter1;

/**
 * 包名：com.concurrent.chapter1
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-16 下午4:22
 */
public class ThreadLocalTest {
    static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    static void print(String str) {
        System.out.println(str + " : " + threadLocal.get());
        threadLocal.remove();
    }

    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set(" threadA set variable");
                //再赋值的话会覆盖，ThreadLocal低层是hashMap，key是该线程
//                threadLocal.set("  set variable");
                print("theadA ");
                System.out.println(" threadA after remove :: " + threadLocal.get());
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set(" threadB set variable");
                print("threadB");
                System.out.println(" threadB after remove :: " + threadLocal.get());
            }
        });
        threadA.start();
        threadB.start();
    }
}
