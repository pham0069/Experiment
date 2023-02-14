package com.diep.example.test.mechanism;

import java.util.LinkedList;
import java.util.List;

public class BlockingQueueImplementation {
    private List queue = new LinkedList();
    private Integer limit = null;

    public BlockingQueueImplementation() {}
    public BlockingQueueImplementation(int limit){
        this.limit = limit;
    }

    public synchronized void put(Object item) throws InterruptedException  {
        if (limit != null) {
            while (this.queue.size() == this.limit) {
                wait();
            }
        }
        this.queue.add(item);
        if(this.queue.size() == 1) {
            notifyAll();
        }
    }

    public synchronized Object take() throws InterruptedException{
        while(this.queue.size() == 0){
            wait();
        }
        if (limit != null) {
            if (this.queue.size() == this.limit) {
                notifyAll();
            }
        }

        return this.queue.remove(0);
    }
}
