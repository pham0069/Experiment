package com.diep.example.rxjava.backpressure;

import rx.Observable;

import java.util.List;

public class ComputeFunction {
    public static void compute(Integer i) {
        try {
            System.out.println("Compute integer: " + i + " on thread "  + Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void compute(List<Integer> list) {
        try {
            System.out.println("Compute integers: " + list + " on thread "  + Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void compute(Observable<Integer> observable) {
        observable.subscribe(i -> compute(i));
    }
}
