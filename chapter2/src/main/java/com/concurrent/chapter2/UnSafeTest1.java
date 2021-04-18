package com.concurrent.chapter2;

import sun.misc.Unsafe;

/**
 * @ClassName UnSafeTest1
 * @Description TODO
 * @Author wbq
 * @Date 2021/4/17 18:20
 * @Version 1.0
 * 运行会抛出异常，因为Unsafe.getUnsafe();
 *      public static Unsafe getUnsafe() {
 *         Class var0 = Reflection.getCallerClass();
 *         if (!VM.isSystemDomainLoader(var0.getClassLoader())) {
 *             throw new SecurityException("Unsafe");
 *         } else {
 *             return theUnsafe;
 *         }
 *     }
 *     会检查类的加载器是不是启动类加载器BootStrap
 *     按照双亲委派机制，虽然说会委托父类加载器加载，但是因为UNSafe类可以直接操作内存
 *     所以jdk不允许用户使用，会添加这个校验
 *     可以通过反射实现
 */
public class UnSafeTest1 {
    static final Unsafe unsafe = Unsafe.getUnsafe();
    static long statusOffset = 0;
    private volatile long state;

    static {
        try {
            statusOffset = unsafe.objectFieldOffset(UnSafeTest1.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UnSafeTest1 unSafeTest1 = new UnSafeTest1();
        boolean success = unsafe.compareAndSwapInt(unSafeTest1, statusOffset, 0, 1);
        System.out.println(success);
    }
}
