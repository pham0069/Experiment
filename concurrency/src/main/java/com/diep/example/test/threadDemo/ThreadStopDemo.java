package com.diep.example.test.threadDemo;

/**
 * The Java Thread class contains a stop() method, but it is deprecated. The original stop() method would not provide
 * any guarantees about in what state the thread was stopped. That means, that all Java objects the thread had access
 * to during execution would be left in an unknown state. If other threads in your application also has access to the
 * same objects, your application could fail unexpectedly and unpredictably.
 *
 * Instead of calling the stop() method, you will have to implement your thread code so it can be stopped. Here is an
 * example of a class that implements Runnable which contains an extra method called doStop() which signals to the
 * Runnable to stop. The Runnable will check this signal and stop when it is ready to do so.
 */
public class ThreadStopDemo {
    public static void main(String[] args) throws InterruptedException {
        // Create and start a thread
        StoppableThread thread = new StoppableThread();
        thread.start();

        // Sleep in main thread for the other thread to execute for some time
        Thread.sleep(8000);

        // Stop that thread
        // thread.stop(); // dont do this due to unexpected behavior
        thread.doStop();
    }
}

/**
 * Mutithreading that doStop var is accessed from multiple threads
 * It access needs putting in synchronized method to ensure the safety
 */
class StoppableThread extends Thread {

    private boolean doStop = false;

    synchronized void doStop() {
        this.doStop = true;
    }

    private synchronized boolean keepRunning() {
        return this.doStop == false;
    }

    @Override
    public void run() {
        while(keepRunning()) {
            // keep doing what this thread should do.
            System.out.println("Running");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}