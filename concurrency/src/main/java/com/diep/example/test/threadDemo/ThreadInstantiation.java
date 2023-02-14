package com.diep.example.test.threadDemo;

/**
 * Two ways to create a thread
 * 1. Provide a Runnable object
 * 2. Subclass thread
 *
 * The first idiom is more general since Runnable object can subclass a class other than Thread
 * The second idiom is easier to use in simple apps, but restricted that your task class must be descendant of Thread
 *
 * The first way is preferred due to flexibility and applicable to high-level thread management
 */
public class ThreadInstantiation {
    public static void main(String args[]) {
        createThread();
    }

    static void createThread() {
        // new thread from a concrete implementation of runnable
        (new Thread(new HelloRunnable())).start();

        // new thread from a thread subclass
        (new HelloThread()).start();

        // anonymous implementation of runnable
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from anonymous");
            }
        }).start();

        // lambda implementation of runnable
        new Thread(() -> System.out.println("Hello from lambda")).start();
    }
}

class HelloRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Hello from a runnable!");
    }
}

class HelloThread extends Thread {
    @Override
    public void run() {
        System.out.println("Hello from a thread!");
    }
}
