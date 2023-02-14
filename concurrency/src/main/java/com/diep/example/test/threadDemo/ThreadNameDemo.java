package com.diep.example.test.threadDemo;

/**
 * It's useful to give a name to thread to differentiate threads, for logging purpose
 *
 */
public class ThreadNameDemo {
    public static void main(String[] args){
        // use Thread.currentThread() to get the executing thread instance
        System.out.println(Thread.currentThread().getName());
        for(int i=0; i<10; i++){
            // use index as thread name
            new Thread("" + i){
                public void run(){
                    System.out.println("Thread: " + getName() + " running");
                }
            }.start();
        }
    }
}
