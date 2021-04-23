package com.concurrent.chapter7;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 包名：com.concurrent.chapter7
 * 工程：concurrent
 * 作者: wangbq
 * 时间:  2021-04-23 下午2:53
 */
public class PrioprityBlockingQueueTest2 {
    static class Task implements Comparable<Task> {

        private int prioprity;
        private String taskName;

        public Task(int prioprity, String taskName) {
            this.prioprity = prioprity;
            this.taskName = taskName;
        }

        @Override
        public int compareTo(Task o) {
            return this.prioprity >= o.prioprity ? 1 : -1;
        }

        @Override
        public String toString() {
            return "[" + taskName + prioprity + "]";
        }
    }

    public static void main(String[] args) {
        PriorityBlockingQueue<Task> queue = new PriorityBlockingQueue<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Task task = new Task(random.nextInt(10), "taskName" + i + " : ");
            queue.offer(task);
        }
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }

    }
}
