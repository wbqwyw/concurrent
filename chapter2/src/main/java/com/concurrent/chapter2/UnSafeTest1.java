package com.concurrent.chapter2;

import sun.misc.Unsafe;

/**
 * @ClassName UnSafeTest1
 * @Description TODO
 * @Author wbq
 * @Date 2021/4/17 18:20
 * @Version 1.0
 * ���л��׳��쳣����ΪUnsafe.getUnsafe();
 *      public static Unsafe getUnsafe() {
 *         Class var0 = Reflection.getCallerClass();
 *         if (!VM.isSystemDomainLoader(var0.getClassLoader())) {
 *             throw new SecurityException("Unsafe");
 *         } else {
 *             return theUnsafe;
 *         }
 *     }
 *     ������ļ������ǲ��������������BootStrap
 *     ����˫��ί�ɻ��ƣ���Ȼ˵��ί�и�����������أ�������ΪUNSafe�����ֱ�Ӳ����ڴ�
 *     ����jdk�������û�ʹ�ã���������У��
 *     ����ͨ������ʵ��
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
