package com.diep.example.test.utilities;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Similar to CountdownLatch with more flexibility
 * Allow threads to dynamically join, no need to specify number of tasks to wait from the beginning
 *
 * A thread calls register() to increase the number of registered parties
 * The thread signals that it arrived at the barrier by calling the arriveAndAwaitAdvance(), which is a blocking method
 * When the number of arrived parties is equal to the number of registered parties, the execution of the program will continue
 * When the thread finishes its job, we should call the arriveAndDeregister() method
 * to signal that the current thread should no longer be accounted for in this particular phase.
 */
public class PhaserDemo {
    static Phaser phaser = new Phaser();
    public static void main(String[] args) {
        phaser.register();

        Thread one = new Party(1000, "One");
        Thread two = new Party(2000, "Two");
        Thread three = new Party(3000, "Three");

        one.start();
        two.start();
        three.start();

        try {
            Thread.sleep(2000);
            System.out.println("");
            phaser.arrive();
            phaser.awaitAdvanceInterruptibly(0, 1, TimeUnit.SECONDS);//fail
            //phaser.awaitAdvanceInterruptibly(0, 5, TimeUnit.SECONDS);//success
            System.out.println("Done waiting for phaser to finish");
        } catch (InterruptedException | TimeoutException e) {
            System.out.println("Failed to wait for phaser to finish " + getPhaserState());
        }


    }

    static String getPhaserState() {
        return String.format("%d / %d / %d", phaser.getRegisteredParties(),
                phaser.getUnarrivedParties(), phaser.getPhase());
    }
    static class Party extends Thread {
        private int duration;

        public Party(int duration, String name) {
            super(name);
            this.duration = duration;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(duration);
                System.out.println(Thread.currentThread().getName() + " is registering " + getPhaserState());
                phaser.register();
                Thread.sleep(duration);
                phaser.arriveAndDeregister();
                System.out.println(Thread.currentThread().getName() + " has finished and countdown" + getPhaserState());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
