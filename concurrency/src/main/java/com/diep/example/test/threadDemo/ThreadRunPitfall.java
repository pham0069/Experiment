package com.diep.example.test.threadDemo;

/**
 * Calling run() and start() on thread, both execute the task defined by run()
 * run() executes it on the thread calling the method
 * start() executes it on the thread we create
 *
 * Dont be confused, use start()
 */
public class ThreadRunPitfall {
    public static void main(String[] args) {
        Thread newThread = new Thread(new HelloRunnable());
        newThread.run();  //execute on main thread if called in main method
        newThread.start();//execute on this newThread
    }
}
