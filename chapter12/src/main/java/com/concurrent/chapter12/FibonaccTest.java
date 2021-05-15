package com.concurrent.chapter12;

/**
 * @program: concurrent
 * @description: 斐波那契数列求和测试
 * @author: Mr.Wang
 * @create: 2021-05-15 12:59
 **/
public class FibonaccTest {

    public static int compute(int n) {
        if (n == 1 || n == 2) {
            return 1;
        } else {
            int first = 1;
            int second = 1;
            int third = 0;
            for (int i = 3; i <= n; i++) {
                third = first + second;
                first = second;
                second = third;
            }
            return third;
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int result = FibonaccTest.compute(40);
        long end = System.currentTimeMillis();
        System.out.println(result);
        System.out.println(end - start);
    }

}
