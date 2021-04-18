package com.concurrent.chapter2;

/**
 * @ClassName InstructionReorderTest
 * @Description TODO
 * @Author wbq
 * @Date 2021/4/17 19:09
 * @Version 1.0
 * ÷∏¡Ó÷ÿ≈≈–Ú æ¿˝
 */
public class InstructionReorderTest {
    private static int num = 0;
    private static boolean ready = false;

    static class Ready extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                if (ready) {
                    System.out.println(num + num);
                }
                System.out.println("ready thread ...");
            }
        }
    }

    static class Write extends Thread {
        @Override
        public void run() {
            num = 2;
            ready = true;
            System.out.println("write Thread set over!");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Ready ready = new Ready();
        ready.start();
        Write write = new Write();
        write.start();
        Thread.sleep(300);
        ready.interrupt();
        System.out.println("main over");
    }
}
