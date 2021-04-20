package com.concurrent.chapter5;

import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 包名：com.concurrent.chapter5
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-20 下午1:37
 * CopyOnWriteArrayList 类的弱一致性演示
 * 因为CopyOnWriteArrayList的原理是在快照上操作，迭代器也是一个快照
 */
public class CopyOnWriteArrayListTest {
    private static volatile CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        copyOnWriteArrayList.add(" welcome ");
        copyOnWriteArrayList.add(" to ");
        copyOnWriteArrayList.add(" alibaba ");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                copyOnWriteArrayList.remove(2);
            }
        });

        ListIterator<String> itr = copyOnWriteArrayList.listIterator();
        thread.start();
        thread.join();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

    }
}
