package com.diep.example.rxjava.backpressure;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * A cold Observable emits a particular sequence of items
 * but can begin emitting this sequence when its Observer finds it to be convenient,
 * and at whatever rate the Observer desires,
 * without disrupting the integrity of the sequence.
 *
 * Cold Observable is providing items in a lazy way.
 *
 * The Observer is taking elements only when it is ready to process that item,
 * and items do not need to be buffered in an Observable
 * because they are requested in a pull fashion.
 *
 * Cold Observables do not need to have any form of a backpressure
 * because they work in a pull fashion
 */
public class ColdObservableDemo {
    public static void main(String[] args) throws Exception {
        Observable.range(1, 1_000)
                //run Observer within a computation thread pool in RxJava.
                .subscribeOn(Schedulers.computation())
                .subscribe(ComputeFunction::compute);

        Thread.sleep(10_000);
        System.out.println("The end");
    }


}
