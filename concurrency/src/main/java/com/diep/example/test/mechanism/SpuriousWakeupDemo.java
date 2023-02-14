package com.diep.example.test.mechanism;

import lombok.AllArgsConstructor;

/**
 * It's possible that threads wake up even if notify() and notifyAll() have not been called, without any reason
 * This is dangerous violation of logic.
 * Need to guard against spurious wakeup with while loop, instead of if statement
 * The thread awakened spins around until the condition in the spin lock (while loop) becomes false.
 *
 * In this demo, the modification is in the Checker code.
 */
public class SpuriousWakeupDemo {
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
                synchronized (signal){
                    signal.setReady(true);
                    signal.notify();
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
                 * Use while (!signal.isReady()) instead of if(!signal.isReady())
                 */
                synchronized (signal) {
                    while (!signal.isReady())
                        signal.wait();
                }
            } catch (InterruptedException e) {}
            System.out.println("Done checking for ready signal @" + System.currentTimeMillis());
        }
    }
}
