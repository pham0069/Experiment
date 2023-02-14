package com.diep.example.test.mechanism;

import lombok.AllArgsConstructor;

/**
 * The methods notify() and notifyAll() do not save the method calls to them in case no threads are waiting when they
 * are called. The notify signal is then just lost. Therefore, if a thread calls notify() before the other thread
 * has called wait(), the signal will be missed by the waiting thread. When it happens, the waiting thread waiting
 * forever, never waking up, because the signal to wake up was missed.
 *
 * In this demo, updater did not execute setReady() and notify() in atomic way, under same synchronized block. Checker
 * did not execute isReady() and wait() in atomic way either. Will it work, given that both getReady() and setReady()
 * are already synchronized methods?
 *
 * Missed signal issue occurs when the order of execution is as follows
 * 1. Checker calls isReady()
 * 2. Updater calls setReady()
 * 3. Updater calls notify()
 * 4. Checker calls wait()
 *
 * This prevents this program from ending.
 */
public class MissedSignalDemo {
    public static void main(String[] args) {
        Signal signal = new Signal();

        new Checker(signal).start();
        new Updater(signal).start();
    }

    @AllArgsConstructor
    static class Updater extends Thread {
        private final Signal signal;
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                /**
                 * setReady() and notify() are not called in atomic way
                 */
                signal.setReady(true);
                synchronized (signal){
                    signal.notify();
                    System.out.println("Done notify");
                }


            } catch (InterruptedException e) {}
        }
    }

    @AllArgsConstructor
    static class Checker extends Thread {
        private final Signal signal;
        @Override
        public void run() {
            System.out.println("Start checking for ready signal @" + System.currentTimeMillis());
            try {
                /**
                 * isReady() and wait() are not called in atomic way
                 */
                if (!signal.isReady()) {
                    Thread.sleep(5000);
                    synchronized (signal) {
                        System.out.println("Entering wait");
                        signal.wait();
                    }
                }
            } catch (InterruptedException e) {}
            System.out.println("Done checking for ready signal @" + System.currentTimeMillis());
        }
    }
}
