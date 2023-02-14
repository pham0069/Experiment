package com.diep.example.rxjava.backpressure;

import com.diep.example.rxjava.backpressure.ComputeFunction;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

import java.util.stream.IntStream;

/**
 * A hot Observable begins generating items and emits them immediately when they are created.
 * Hot Observable emits items at its own pace, and it is up to its observers to keep up.
 *
 * When the Observer is not able to consume items as quickly as they are produced by an Observable,
 * they need to be buffered or handled in some other way, as they will fill up the memory,
 * finally causing OutOfMemoryException.
 */
public class HotObservableDemo {
    public static void main(String[] args) throws Exception {
        PublishSubject<Integer> source = PublishSubject.create();

        source.observeOn(Schedulers.computation())
                .subscribe(ComputeFunction::compute, Throwable::printStackTrace);


        //produceWithAcceptableRate(source);
        overproduceWithoutBackPressure(source);

        Thread.currentThread().sleep(10_000);
    }

    /* This  publish  1000 items at a time
    * will throw MissingBackPressureException
    * as we dont define how to handle overproducing Observable
    */
    private static void overproduceWithoutBackPressure(PublishSubject<Integer> source) {
        IntStream.range(1, 1000).forEach(source::onNext);
    }

    private static void produceWithAcceptableRate(PublishSubject<Integer> source) {
        IntStream.range(1, 100).forEach(source::onNext);
    }
}
