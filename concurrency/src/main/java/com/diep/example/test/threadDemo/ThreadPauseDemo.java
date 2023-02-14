package com.diep.example.test.threadDemo;

/**
 * We can pause a thread with Thread.sleep()
 * Thread.sleep() causes the current thread ot suspend execution for a specified period
 * This is efficient means to give processor time to other threads that might be running on system
 *
 * Sleep time is not guaranteed to be precise, as it depends on the facilities provided by system
 * Sleep can be interrupted, thus it might throw InterruptedException
 */
public class ThreadPauseDemo {
    public static void main(String args[]) throws InterruptedException {
        String importantInfo[] = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        };

        for (int i = 0; i < importantInfo.length; i++) {
            //Pause for 4 seconds
            Thread.sleep(4000);
            //Print a message
            System.out.println(importantInfo[i]);
        }
    }
}