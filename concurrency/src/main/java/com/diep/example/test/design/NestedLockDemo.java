package com.diep.example.test.design;

import java.util.ArrayList;
import java.util.List;

/**
 * Beware some nested lock is hard to identify
 * Both lock() and unlock() methods first obtain lock on Lock class then obtain lock on  queueObject
 * If a thread already enters lock and get the lock on this, another thread cannot enter unlock() to notify() the
 * waiting thread
 */
public class NestedLockDemo {
    class FairNestedLock {
        private boolean isLocked = false;
        private Thread lockingThread = null;
        private List<QueueObject> waitingThreads = new ArrayList<>();

        public void lock() throws InterruptedException {
            QueueObject queueObject = new QueueObject();

            synchronized (this) {
                waitingThreads.add(queueObject);

                while (isLocked || waitingThreads.get(0) != queueObject) {

                    synchronized (queueObject) {
                        try {
                            queueObject.wait();
                        } catch (InterruptedException e) {
                            waitingThreads.remove(queueObject);
                            throw e;
                        }
                    }
                }
                waitingThreads.remove(queueObject);
                isLocked = true;
                lockingThread = Thread.currentThread();
            }
        }

        public synchronized void unlock() {
            if (this.lockingThread != Thread.currentThread()) {
                throw new IllegalMonitorStateException(
                        "Calling thread has not locked this lock");
            }
            isLocked = false;
            lockingThread = null;
            if (waitingThreads.size() > 0) {
                QueueObject queueObject = waitingThreads.get(0);
                synchronized (queueObject) {
                    queueObject.notify();
                }
            }
        }
    }
}
