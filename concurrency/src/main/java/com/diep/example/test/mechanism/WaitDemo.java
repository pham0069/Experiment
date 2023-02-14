package com.diep.example.test.mechanism;

import lombok.AllArgsConstructor;

/**
 * A thread calling wait() on any object becomes inactive until another thread calls notify on that object
 * It is mandatory that thread obtains the object lock (with synchronized) before calling wait() or notify() on that
 * object. Otherwise IllegalMonitorStateException will be thrown.
 *
 * Note on sync lock during wait and notify:
 * 1. After a thread calls wait(), it releases the lock it holds on object, allowing other threads to call wait(), notify()
 * 2. One a waiting thread is awakened, it must reobtain the object lock to exit wait().
 * 3. If multiple threads are awakened using notifyAll(), only one awakened thread at a time can exit the wait() method
 *
 * In this demo, checker thread use if to check if signal is ready; if it is not, checker call wait() on signal
 * Updater thread not only updates signal but also calls notify() to wake up any thread that called wait() on signal
 *
 *
 */
public class WaitDemo {
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
                 * synchronized to call notify()
                 */
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
                 * synchronized to call wait()
                 * Try removing synchronized, IllegalMonitorStateException will be thrown
                 * After calling wait(), lock is released; when awakened, lock needs to be obtained again to exit wait()
                 */
                synchronized (signal) {
                    if (!signal.isReady())
                        signal.wait();
                }
            } catch (InterruptedException e) {}
            System.out.println("Done checking for ready signal @" + System.currentTimeMillis());
        }
    }
}
