package com.concurrent.chapter11;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: concurrent
 * @description: ConcurrentHashMap并发编程注意事项
 * @author: Mr.Wang
 * @create: 2021-04-27 10:12
 * 结果：
 * {tip1=[one1, one2]}
 * {tip1=[one1, one2, two1, two2]}
 * {tip1=[one1, one2, two1, two2], tip2=[three1, three2]}
 **/
public class ConcurrentHashMapTest2 {
    private static ConcurrentHashMap<String, List<String>> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> li = new ArrayList<>();
                li.add("one1");
                li.add("one2");
                List<String> ol = map.putIfAbsent("tip1", li);
                if (ol != null) {
                    ol.addAll(li);
                }
                System.out.println(map);
            }
        });

        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> li = new ArrayList<>();
                li.add("two1");
                li.add("two2");
                List<String> ol = map.putIfAbsent("tip1", li);
                if (ol != null) {
                    ol.addAll(li);
                }
                System.out.println(map);
            }
        });

        Thread three = new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> li = new ArrayList<>();
                li.add("three1");
                li.add("three2");
                List<String> ol = map.putIfAbsent("tip2", li);
                if (ol != null) {
                    ol.addAll(li);
                }
                System.out.println(map);
            }
        });
        one.start();
        two.start();
        three.start();
    }
}
