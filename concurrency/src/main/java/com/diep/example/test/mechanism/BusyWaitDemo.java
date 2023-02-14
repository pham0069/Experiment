package com.diep.example.test.mechanism;

import lombok.AllArgsConstructor;

/**
 * A simple way for threads to send signals to each other is by setting the signal values in some shared object variable.
 * In this demo, Signal object is shared between 2 threads, 1 updating signal, 1 checking when signal is ready.
 * The checking thread has a while loop keeps executing until signal is ready. That is called busy waiting, i.e. though
 * thread does nothing in the loop, but CPU has to keep running while loop.
 *
 * Busy waiting is not a very efficient utilization of the CPU in the computer running the waiting thread, except if the
 * average waiting time is very small. Else, it would be smarter if the waiting thread could somehow sleep or become
 * inactive until it receives the signal it is waiting for.
 */
public class BusyWaitDemo {

    public static void main(String[] args){
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
                signal.setReady(true);
            } catch (InterruptedException e) {}
        }
    }

    @AllArgsConstructor
    static class Checker extends Thread {
        private final Signal signal;
        @Override
        public void run() {
            System.out.println("Start checking for ready signal @" + System.currentTimeMillis());
            while (!signal.isReady()) {
                //do nothing... busy waiting
            }
            System.out.println("Done checking for ready signal @" + System.currentTimeMillis());
        }
    }
}
