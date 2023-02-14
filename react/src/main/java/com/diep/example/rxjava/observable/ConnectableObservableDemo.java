package com.diep.example.rxjava.observable;

import rx.Observable;
import rx.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

public class ConnectableObservableDemo {
    public static void main(String[] args) throws Exception {
        String[] result = {""};
        ConnectableObservable<Long> connectable = Observable
                .interval(200, TimeUnit.MILLISECONDS)
                .publish();
        connectable.subscribe(i -> result[0] += i);

        Thread.sleep(300);
        System.out.println("Result:" + result[0]);  //empty

        //observable does not start emitting item on subscription
        //only starts publishing when connect() is called
        connectable.connect();
        Thread.sleep(250);
        System.out.println("Result:" + result[0]);  //0

        Thread.sleep(250);
        System.out.println("Result:" + result[0]);  //01
    }

}
