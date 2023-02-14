package com.diep.example.test.design;

/**
 * A common solution to resolve race condition is using synchronized to ensure only single thread can execute the
 * critical session at a time.
 *
 * However also due to this, throughput could be affected as other threads are blocked to execute synchronized method
 * or block until the current thread finishes.
 *
 * Thus when using synchronized, design with care to improve throughput
 */
public class CriticalSectionThroughputDemo {
    public static void main(String[] args) {

    }
}

/**
 * This class add() method is thread-safe but not efficient
 * It only allows one thread to execute add() at a time
 */
class NaiveTwoSums {
    private int sum1 = 0;
    private int sum2 = 0;

    public void add(int val1, int val2){
        synchronized(this){
            this.sum1 += val1;
            this.sum2 += val2;
        }
    }
}

/**
 * This class achieves same purpose as the class above but in more efficient manner.
 * It's observed that update on sum1 and sum2 are actually independent
 * Thus we can use a separate lock for different update. Now more than one thread can execute add() at the same time
 */
class EfficientTwoSums {
    private int sum1 = 0;
    private int sum2 = 0;

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void add(int val1, int val2){
        synchronized(lock1){
            this.sum1 += val1;
        }
        synchronized(lock2){
            this.sum2 += val2;
        }
    }
}
