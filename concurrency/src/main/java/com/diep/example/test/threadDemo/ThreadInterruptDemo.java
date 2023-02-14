package com.diep.example.test.threadDemo;

/**
 * Interrupt is indication that a thread should stop what it is doing and do something else
 * It is developer's decision of how the thread responses to interrupt
 * It is common that thread terminates in such case
 *
 */
public class ThreadInterruptDemo {
    public static void main(String[] ags) {
        String importantInfo[] = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        };

        for (int i = 0; i < importantInfo.length; i++) {
            // Pause for 4 seconds
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                // We've been interrupted: no more messages.
                return;
            }
            // Print a message
            System.out.println(importantInfo[i]);
        }
    }
}
