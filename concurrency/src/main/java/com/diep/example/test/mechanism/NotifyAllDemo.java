package com.diep.example.test.mechanism;

import lombok.AllArgsConstructor;

/**
 * notifyAll() wakes up all the waiting threads, but they can only exit wait() method one after another
 * In this demo, we have 2 checkers and 1 updater
 * Updater is modified to notifyAll() after setReady()
 *
 * If updater only calls notify(), only 1 checker thread would be awake and ultimately exit wait() method.
 * The other will be blocked until some thread call notify().
 * It depends on the purpose of the program to call notify() or notifyAll(). Is the signal intended for a single waiting
 * thread or all waiting threads ?
 */
public class NotifyAllDemo {
    public static void main(String[] args) {
        Signal signal = new Signal();

        new WaitDemo.Checker(signal).start();
        new WaitDemo.Checker(signal).start();
        new Updater(signal).start();
    }

    @AllArgsConstructor
    static class Updater extends Thread {
        private final Signal signal;
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                synchronized (signal){
                    signal.setReady(true);
                    signal.notifyAll();
                    //signal.notify();
                }
            } catch (InterruptedException e) {}
        }
    }
}
