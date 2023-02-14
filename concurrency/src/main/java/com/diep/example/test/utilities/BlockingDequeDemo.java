package com.diep.example.test.utilities;

import lombok.Data;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * BlockingDeque is a deque which is thread-safe to put into and take instances from both end of the queue
 * LinkedBlockingDeque is an implementation of BlockingDeque
 */
public class BlockingDequeDemo {
    public static void main(String[] args) {
        BlockingDeque deque = new LinkedBlockingDeque();
        Thread producer = new Thread(new Producer(deque));
        Thread consumer = new Thread(new Consumer(deque));

        producer.start();
        consumer.start();
    }

    @Data
    static class Producer implements Runnable{
        private final BlockingDeque deque;

        public void run() {
            try {
                deque.addFirst("1");
                Thread.sleep(1000);
                deque.addLast("2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Data
    static class Consumer implements Runnable{
        private final BlockingDeque deque;

        public void run() {
            try {
                System.out.println(deque.takeLast());
                System.out.println(deque.takeFirst());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


