package com.diep.example.test.mechanism;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

/**
 * http://tutorials.jenkov.com/java-concurrency/threadlocal.html
 *
 * ThreadLocal allows creating variables that can only be read or written by same thread
 * Even if two threads are executing the same code, and the code has a reference to a ThreadLocal variable, then the two
 * threads cannot see each other's ThreadLocal variables.
 *
 * Each thread holds an implicit reference to its copy of a thread-local variable as long as the thread is alive and
 * the ThreadLocal instance is accessible; after a thread goes away, all of its copies of thread-local instances are
 * subject to garbage collection (unless other references to these copies exist).
 *
 * ---------------------------------------------------------------------------------------------------------------------
 * In this demo, 2 threads access same Formatter which has a ThreadLocal variable numberFormat
 * Each thread sets its own pattern for numberFormat, and when they execute at the same time, the numbers are formatted
 * in correct pattern they set. This proves that each thread actually holds a copy of NumberFormat instance, which is
 * obtained from same ThreadLocal var.
 *
 * Side note:
 * 1. NumberFormat is not a thread-safe object, hence put it in ThreadLocal is a way to ensure its safe use
 * 2. In Java, memory address of object cannot be printed.
 * Object.toString() by default print out hashcode of object instead of memory address
 * public String toString(Object o) {
 *     return o.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(o));
 * }
 * It maybe impossible to differentiate objects by printing them
 * 3. When you want to give an initial value of ThreadLocal object for all accessing threads, override method initialValue()
 * ---------------------------------------------------------------------------------------------------------------------
 * InheritableThreadLocal is an extension of ThreadLocal.
 * Instead of each thread having its own value inside a ThreadLocal, the InheritableThreadLocal grants access to values
 * to a thread and all child threads created by that thread.
 */
public class ThreadLocalDemo {
    private static Formatter formatter = new Formatter();
    public static void main(String[] args) {
        Thread t1 = new Thread(new FormatRunnable("0.00"), "1");
        Thread t2 = new Thread(new FormatRunnable("0.0000"), "2");

        t1.start();
        t2.start();
    }

    private static class FormatRunnable implements Runnable {
        private final double[] numbers = {1.23, 1.234, 1.2345, 1.23456, 1.234567};
        private final String pattern;
        FormatRunnable(String pattern) {
            this.pattern = pattern;
        }
        @Override
        public void run() {
            try {
                formatter.setFormat(pattern);
                for (double n: numbers) {
                    Thread.sleep(new Random().nextInt(1000));
                    System.out.println(Thread.currentThread().getName() + " formats "+ n + " to " + formatter.format(n));
                }
            } catch (InterruptedException e) { }
        }
    }
    private static class Formatter {
        private final ThreadLocal<NumberFormat> numberFormat;
        Formatter() {
             numberFormat = new ThreadLocal<NumberFormat>() {
                 /**
                  * Proper way to initialize value for all threads that access this ThreadLocal var
                  */
                @Override
                protected NumberFormat initialValue() {
                    return new DecimalFormat("0.00###");
                }
             };
            /**
             * Note that numberFormat value cannot be initialized with this set method
             * This will only set the value for the copy held by the thread calling this Formatter constructor
             */
            //numberFormat.set(new DecimalFormat("#"));
        }

        void setFormat(String pattern) {
            numberFormat.set(new DecimalFormat(pattern));
        }

        String format(Object number) {
            // Printing not works since different NumberFormat obj still may return same hashcode
            //System.out.println(Thread.currentThread() + ": " + numberFormat.get());
            return numberFormat.get().format(number);
        }
    }
}
