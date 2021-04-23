package com.concurrent.chapter7;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 包名：com.concurrent.chapter7
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-23 下午4:09
 */
public class DelayQueueTest {
    static class DelayedEle implements Delayed {
        private long delay;
        private long expire;
        private String name;

        DelayedEle(long delay, String name) {
            this.delay = delay;
            this.name = name;
            expire = System.currentTimeMillis() + delay;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb.append("DelayedEle{delay=").append(delay).append(",taskName='")
                    .append(name).append("'}");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        DelayQueue<DelayedEle> delayQueue = new DelayQueue<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            DelayedEle delayedEle = new DelayedEle(random.nextInt(500), "taskName:" + i);
            delayQueue.offer(delayedEle);
        }
        DelayedEle ele = null;
        try {
            for (; ; ) {
                while ((ele = delayQueue.take()) != null && delayQueue.size() > 0) {
                    System.out.println(ele.toString());
                }
                return;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
