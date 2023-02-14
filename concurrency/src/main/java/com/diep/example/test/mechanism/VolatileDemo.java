package com.diep.example.test.mechanism;

import lombok.Data;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * http://tutorials.jenkov.com/java-concurrency/volatile.html
 *
 * Given 2 threads access same variable and run on 2 CPUs, they may keep a copy of the value in CPU cache and read/write
 * on cached value (for performance improvement). This prevents multiple threads from getting the updated value of the
 * shared variable. Volatile keyword is used to ensure a variable will be read and written directly to main memory
 *
 * In the demo, we have class counter with volatile value variable, to address the visibility problem among multiple
 * threads. It will work properly when single writer updates counter and multiple reader get counter
 *
 * In case we have multiple writers, volatile is not enough to prevent the race condition in update operation like
 * increment()
 *
 * Note that volatile comes with a cost since reading from main memory is more expensive than cache. So use volatile only
 * when needed.
 */
public class VolatileDemo {
    public static void main(String[] args) {
        singleWriter();
        multipleWriters();
    }

    static void readAndWrite(int w, int r) {
        Counter c = new Counter();
        Thread[] writers = new Thread[w];
        Thread[] readers = new Thread[r];
        IntStream.range(0, w).forEach(i -> writers[i] = new Thread(new Writer(c)));
        IntStream.range(0, r).forEach(i -> readers[i] = new Thread(new Reader(c)));

        IntStream.range(0, w).forEach(i -> writers[i].start());
        IntStream.range(0, r).forEach(i -> readers[i].start());
    }

    static void singleWriter() {
        readAndWrite(1, 10);
    }

    static void multipleWriters() {
        readAndWrite(2, 10);
    }

    @Data
    static class Reader implements Runnable {
        private final Counter counter;
        @Override
        public void run() {
            try {
                Thread.sleep(new Random().nextInt(1000));
                System.out.println("Current counter is " + counter.value);
            } catch (InterruptedException e) {}
        }
    }

    @Data
    static class Writer implements Runnable {
        private final Counter counter;
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(new Random().nextInt(200));
                    counter.increment();
                    System.out.println("Incremented counter to " + counter.value);
                } catch (InterruptedException e) {}
            }
        }
    }

    static class Counter{
        private volatile int value = 0;
        //private int value = 0;
        void increment() {
            value++;
        }
    }
}
