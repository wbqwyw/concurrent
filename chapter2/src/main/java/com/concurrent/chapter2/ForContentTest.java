package com.concurrent.chapter2;

/**
 * 包名：com.concurrent.chapter2
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-19 上午8:50
 * 伪共享演示
 */
public class ForContentTest {
    public static final int LINE_NUM = 1024;
    public static final int COLUM_NUM = 1024;

    public static void main(String[] args) {
        long[][] array = new long[LINE_NUM][COLUM_NUM];
        long start = System.currentTimeMillis();
        for (int i = 0; i < LINE_NUM; i++) {
            for (int j = 0; j < COLUM_NUM; j++) {
                array[i][j] = i * 2 + j;//9~16
//                array[j][i] = i * 2 + j;//14~18
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("用时：" + (end - start));
    }
}
