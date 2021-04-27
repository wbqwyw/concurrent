package com.concurrent.chapter11;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: concurrent
 * @description: ConcurrentHashMap并发编程注意事项
 * @author: Mr.Wang
 * @create: 2021-04-27 09:56
 * <p>
 * 结果:
 * {topc1=[one1, one2]}
 * {topc1=[two1, two2]}
 * {topc2=[three1, three2], topc1=[two1, two2]}
 * <p>
 * 相同的key会覆盖value
 * 我们想达到的目的是累计相同的key对应的value
 **/
public class ConcurrentHashMapTest1 {
    private static ConcurrentHashMap<String, List<String>> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> list = new ArrayList<>();
                list.add("one1");
                list.add("one2");
                map.put("topc1", list);
                System.out.println(map);
            }
        });
        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> list = new ArrayList<>();
                list.add("two1");
                list.add("two2");
                map.put("topc1", list);
                System.out.println(map);
            }
        });
        Thread three = new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> list = new ArrayList<>();
                list.add("three1");
                list.add("three2");
                map.put("topc2", list);
                System.out.println(map);
            }
        });
        one.start();
        two.start();
        three.start();
    }
}
