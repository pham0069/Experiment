package com.diep.example.test.mechanism;

/**
 * There are 4 ways to mark synchronized
 * 1. Instance method:
 * Sync on the instance (object) owning the method
 * Only 1 thread can execute this method per instance
 *
 * 2. Static method
 * Sync on the class object owning this method
 * Since only 1 class object exists per class, only 1 thread can execute this static method at a time
 *
 * 3. Code block inside instance method
 * Require an object to be used as monitor object.
 * Only 1 thread can execute the method per monitor object
 *
 * 4. Code block inside static methods
 * Same as code block inside instance method
 **/
public class SynchronizedDemo {
    private static int count = 0;
    private static Object lock = new Object();
    public static void main(String[] args) throws InterruptedException{
        //staticCount();
        instanceCount();
    }

    static void staticCount() throws InterruptedException{
        Thread t1 = new Thread(() -> add(2));
        Thread t2 = new Thread(() -> add(10));

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(count);
    }

    static void instanceCount() throws InterruptedException{
        Counter c = new Counter();
        Thread t1 = new Thread(() -> c.add(2));
        Thread t2 = new Thread(() -> c.add(10));

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(c.count);
    }

    /**
     * Synchronized static method
     */
    static synchronized void add(int value) {
        count += value;
    }

    /**
     * Synchronized block inside static method
     * When locking on SynchronizedDemo.class object, it is equivalent to synchronized static method
     */
    static void extract(int value) {
        synchronized (SynchronizedDemo.class) {
            count -= value;
        }
    }

    /**
     * Synchronized block inside static method
     * Using a static var lock as monitor object
     */
    static void increment() {
        synchronized (lock) {
            count ++;
        }
    }

    static class Counter {
        int count = 0;
        Object lock = new Object();

        /**
         * Synchronized instance method
         */
        synchronized void add(int value) {
            this.count += value;
        }

        /**
         * Synchronized code block inside instance method
         * In this case, use this instance as lock
         */
        void extract(int value) {
            synchronized (this) {
                this.count -= value;
            }
        }

        /**
         * Synchronized code block inside instance method
         * Use a local lock as monitor object
         */
        void increment() {
            synchronized (lock) {
                this.count ++;
            }
        }
    }
}

