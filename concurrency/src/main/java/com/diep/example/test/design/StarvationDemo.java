package com.diep.example.test.design;

/**
 * Causes of starvation in Java:
 *
 * 1. Threads with high priority swallow all CPU time from threads with lower priority.
 * 2. Threads are blocked indefinitely waiting to enter a synchronized block, because other threads are constantly
 * allowed access before it.
 * 3. Threads waiting on an object (called wait() on it) remain waiting indefinitely because other threads are constantly
 * awakened instead of it.
 *
 * One way to prevent starvation is implementing custom lock instead of relying on synchronized block
 * The lock below is still not enough to ensure fairness as only using notify() which does not guarantee which thread
 * would be awakened
 */
public class StarvationDemo {
    class Lock{
        private boolean isLocked = false;
        private Thread lockingThread = null;

        public synchronized void lock() throws InterruptedException{
            while(isLocked){
                wait();
            }
            isLocked = true;
            lockingThread = Thread.currentThread();
        }

        public synchronized void unlock(){
            if(this.lockingThread != Thread.currentThread()){
                throw new IllegalMonitorStateException(
                        "Calling thread has not locked this lock");
            }
            isLocked = false;
            lockingThread = null;
            notify();
        }
    }
}
