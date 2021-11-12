package com.concurrent.test;

import java.util.concurrent.locks.StampedLock;

/**
 * @program: concurrent
 * @description: 以二维坐标，演示StampedLock使用
 * @author: Mr.Wang
 * @create: 2021-11-12 15:51
 **/
public class Point {
    private double x, y;
    private StampedLock sl = new StampedLock();

    public void move(double newX, double newY) {
        long stamp = sl.writeLock();
        try {
            x += newX;
            y += newY;
        } finally {
            sl.unlockWrite(stamp);
        }
    }

    public double distanceFromOrigin() {
        long stamp = sl.tryOptimisticRead();
        double currentX = x;
        double currentY = y;
        if (!sl.validate(stamp)) {
            stamp = sl.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                sl.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

    public void moveIfAtOrigin(double newX, double newY) {
        long stamp = sl.readLock();
        try {
            while (x == 0.0 && y == 0.0) {
                long ws = sl.tryConvertToWriteLock(stamp);
                if (ws != 0) {
                    stamp = ws;
                    x += newX;
                    y += newY;
                    break;
                } else {
                    sl.unlockRead(stamp);
                    stamp = sl.writeLock();
                }
            }
        } finally {
            sl.unlockRead(stamp);
        }
    }
}
