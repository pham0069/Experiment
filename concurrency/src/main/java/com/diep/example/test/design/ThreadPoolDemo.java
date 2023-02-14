package com.diep.example.test.design;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ThreadPoolDemo {
    private static Random random = new Random();
    public static void main(String[] args) throws Exception {
        ThreadPool threadPool = new ThreadPool(5, 10);

        for (int i = 0; i < 20; i++) {
            threadPool.execute(new Task(i));
        }

        //Thread.sleep(3000);
        threadPool.stop();
    }

    static class Task implements Runnable {
        int index;
        Task(int index) {
            this.index =index;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                System.out.println("Task " + index + " interrupted");
            }
        }
    }

    static class ThreadPool {
        private BlockingQueue<Task> taskQueue;
        private List<PoolThread> threads = new ArrayList<>();
        private boolean isStopped = false;

        public ThreadPool(int noOfThreads, int maxNoOfTasks){
            taskQueue = new LinkedBlockingDeque(maxNoOfTasks);

            for(int i=0; i<noOfThreads; i++){
                threads.add(new PoolThread(Integer.toString(i), taskQueue));
            }
            for(PoolThread thread : threads){
                thread.start();
            }
        }

        public synchronized void  execute(Task task) throws Exception{
            if(this.isStopped) throw
                    new IllegalStateException("ThreadPool is stopped");

            this.taskQueue.put(task);
        }

        public synchronized void stop(){
            this.isStopped = true;
            System.out.println("Stopping thread pool");
            for(PoolThread thread : threads){
                thread.doStop();
            }
        }
    }

    static class PoolThread extends Thread {
        private BlockingQueue<Task> taskQueue = null;
        private boolean isStopped = false;

        PoolThread(String name, BlockingQueue<Task> queue) {
            super(name);
            taskQueue = queue;
        }

        @Override
        public void run() {
            while (!isStopped()) {
                try {
                    Task task = taskQueue.take();
                    task.run();
                    System.out.println("Thread " + getName() + " completed task " + task.index);
                } catch (Exception e) {
                    System.out.println("Thread " + getName() + " encountered " + e.toString());
                    //log or otherwise report exception,
                    //but keep pool thread alive.
                    break;
                }
            }
        }

        synchronized void doStop() {
            isStopped = true;
            System.out.println("Stop thread " + getName());
            this.interrupt(); //break pool thread out of dequeue() call.
        }

        synchronized boolean isStopped() {
            return isStopped;
        }
    }
}
