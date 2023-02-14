package com.diep.example.test.utilities;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Cyclic barrier allows a number of threads to wait for each other
 *
 * It is a barrier that all threads must wait at, until all threads reach it,
 * before any of the threads can continue
 * The threads wait for each other by calling the await() method on the CyclicBarrier.
 * Once N threads are waiting at the CyclicBarrier, all threads are released and can continue running
 *
 * await(): waiting thread waits at CyclicBarrier
 * It is called cyclic because it can be reused
 *
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(4);

        Party first = new Party(1000, barrier, "PARTY-1");
        Party second = new Party(2000, barrier, "PARTY-2");
        Party third = new Party(3000, barrier, "PARTY-3");
        Party fourth = new Party(4000, barrier, "PARTY-4");


        first.start();
        second.start();
        third.start();
        fourth.start();


        System.out.println(Thread.currentThread().getName() + " has finished");


    }

    static class Party extends Thread {
        private int duration;
        private CyclicBarrier barrier;

        public Party(int duration, CyclicBarrier barrier, String name) {
            super(name);
            this.duration = duration;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(duration);
                System.out.println(Thread.currentThread().getName() + " is calling await()");
                barrier.await();
                System.out.println(Thread.currentThread().getName() + " has started running again");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}

