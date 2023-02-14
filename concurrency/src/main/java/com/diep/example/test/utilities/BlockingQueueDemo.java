package com.diep.example.test.utilities;

import lombok.Data;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueue is thread-safe DS to put into and take instances from
 *
 * Scenario:
 * 1. Producing threads keep producing new objects and inserts them into queue, util queue reaches some upper-bound limit
 * If queue is full, the producing thread is blocked until a consuming thread takes an object from queue
 *
 * 2. Consuming threads keep taking objects out of the queue, and process them
 * If queue is empty, the consuming thread is blocked until a producing thread puts smt to the queue
 *
 * Some blocking queue implementation:
 * 1. ArrayBlockingQueue: underlying is array, hence fixed upper-bounded, FIFO
 * 2. DelayQueue: block elements internally until a certain delay, queue element must implement Delayed interface
 * 3. LinkedBlockingQueue: underlying is linked list, upper bound is optional, FIFO
 * 4. PriorityBlockingQueue: underlying is priority queue, unbounded, priority order
 * 5. SynchronousQueue: can only contain single element internally
 *
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws Exception {

        BlockingQueue queue = new ArrayBlockingQueue(1024);

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();

        Thread.sleep(4000);
    }

    @Data
    static class Producer implements Runnable{
        private final BlockingQueue queue;

        public void run() {
            try {
                queue.put("1");
                Thread.sleep(1000);
                queue.put("2");
                Thread.sleep(1000);
                queue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Data
    static class Consumer implements Runnable{
        private final BlockingQueue queue;

        public void run() {
            try {
                System.out.println(queue.take());
                System.out.println(queue.take());
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



