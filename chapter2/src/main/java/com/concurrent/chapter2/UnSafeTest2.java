package com.concurrent.chapter2;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @ClassName UnSafeTest2
 * @Description TODO
 * @Author wbq
 * @Date 2021/4/17 18:32
 * @Version 1.0
 * 使用反射的方式调用UnSafe类
 */
public class UnSafeTest2 {
    static Unsafe unsafe;
    static long stateOffset;
    private volatile long state;

    static {
        try {
            //获取UnSafe的成员变量theUnsafe
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
//            Field field = Unsafe.class.getDeclaredFields()[0];
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            stateOffset = unsafe.objectFieldOffset(UnSafeTest2.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UnSafeTest2 unSafeTest2 = new UnSafeTest2();
        boolean success = unsafe.compareAndSwapInt(unSafeTest2, stateOffset, 0, 1);
        System.out.println(success);
    }
}
