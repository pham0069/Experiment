package com.diep.example.test.utilities;

import java.util.concurrent.CountDownLatch;

/**
 * Countdown latch allows 1 or more threads to wait for a number of tasks to complete
 *
 */
public class CountdownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);
        Party one = new Party(1000, latch, "One");
        Party two = new Party(2000, latch, "Two");
        Party three = new Party(3000, latch, "Three");

        one.start();
        two.start();
        three.start();

        try {
            latch.await();
            System.out.println(Thread.currentThread().getName() + " done waiting for latch to complete.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static class Party extends Thread {
        private int duration;
        private CountDownLatch latch;

        public Party(int duration, CountDownLatch latch, String name) {
            super(name);
            this.duration = duration;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(duration);
                latch.countDown();
                System.out.println(Thread.currentThread().getName() + " has finished and countdown");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
