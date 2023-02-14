package com.diep.example.test.design;

import java.util.ArrayList;
import java.util.List;

/**
 * Notify the first waiting thread in the queue
 */
public class FairLockDemo {
    public static void main(String[] args) {
        double d = 10.2;
        long l = 0l;
        l += d;
        System.out.println(l);

    }
    class FairLock {
        private boolean isLocked = false;
        private Thread lockingThread = null;
        private List<QueueObject> waitingThreads = new ArrayList<>();

        public void lock() throws InterruptedException {
            QueueObject queueObject = new QueueObject();
            boolean isLockedForThisThread = true;
            synchronized (this) {
                waitingThreads.add(queueObject);
            }

            while (isLockedForThisThread) {
                synchronized (this) {
                    isLockedForThisThread =
                            isLocked || waitingThreads.get(0) != queueObject;
                    if (!isLockedForThisThread) {
                        isLocked = true;
                        waitingThreads.remove(queueObject);
                        lockingThread = Thread.currentThread();
                        return;
                    }
                }
                try {
                    queueObject.doWait();
                } catch (InterruptedException e) {
                    synchronized (this) {
                        waitingThreads.remove(queueObject);
                    }
                    throw e;
                }
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
                waitingThreads.get(0).doNotify();
            }
        }
    }
}

class QueueObject {
    private boolean isNotified = false;

    synchronized void doWait() throws InterruptedException {
        while(!isNotified){
            this.wait();
        }
        this.isNotified = false;
    }

    synchronized void doNotify() {
        this.isNotified = true;
        this.notify();
    }
}

