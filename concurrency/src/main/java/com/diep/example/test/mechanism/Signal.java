package com.diep.example.test.mechanism;

public class Signal {
    private boolean ready = false;

    public synchronized boolean isReady() {
        return this.ready;
    }

    public synchronized void setReady(boolean ready) {
        this.ready = ready;
    }
}
