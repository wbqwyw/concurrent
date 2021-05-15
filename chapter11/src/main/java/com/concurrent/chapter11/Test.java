package com.concurrent.chapter11;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: concurrent
 * @description: 测试类
 * @author: Mr.Wang
 * @create: 2021-04-27 09:39
 **/
public class Test {
    @org.junit.Test
    public void test1() {
        System.out.println(1 << 30);
        System.out.println(30 >> 1);
    }

    @org.junit.Test
    public void test2(){
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("a","b");
    }
}
