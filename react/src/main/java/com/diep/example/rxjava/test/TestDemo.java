package com.diep.example.rxjava.test;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestDemo {
    public static void main(String[] args) {
        //testSubscriber();
        testTimeBasedObservable();
    }

    private static void testSubscriber() {
        List<String> letters = Arrays.asList("A", "B", "C", "D", "E");
        TestSubscriber<String> subscriber = new TestSubscriber<>();

        Observable<String> observable = Observable
                .from(letters)
                .zipWith(
                        Observable.range(1, Integer.MAX_VALUE),
                        ((string, index) -> index + "-" + string));

        observable.subscribe(subscriber);

        subscriber.assertCompleted();
        subscriber.assertNoErrors();
        subscriber.assertValueCount(5);
        System.out.println(subscriber.getOnNextEvents());
    }

    private static void testExpectedException() {
        List<String> letters = Arrays.asList("A", "B", "C", "D", "E");
        TestSubscriber<String> subscriber = new TestSubscriber<>();

        Observable<String> observable = Observable
                .from(letters)
                .zipWith(Observable.range(1, Integer.MAX_VALUE), ((string, index) -> index + "-" + string))
                .concatWith(Observable.error(new RuntimeException("error in Observable")));

        observable.subscribe(subscriber);

        subscriber.assertError(RuntimeException.class);
        subscriber.assertNotCompleted();
    }

    private static void testTimeBasedObservable() {
        List<String> letters = Arrays.asList("A", "B", "C", "D", "E");
        TestScheduler scheduler = new TestScheduler();
        TestSubscriber<String> subscriber = new TestSubscriber<>();
        Observable<Long> tick = Observable.interval(1, TimeUnit.SECONDS, scheduler);

        Observable<String> observable = Observable.from(letters)
                .zipWith(tick, (string, index) -> index + "-" + string);

        observable.subscribeOn(scheduler)
                .subscribe(subscriber);

        scheduler.advanceTimeBy(1, TimeUnit.SECONDS);
        subscriber.assertNotCompleted();
        System.out.println(subscriber.getOnNextEvents());

        scheduler.advanceTimeTo(6, TimeUnit.SECONDS);
        subscriber.assertCompleted();
        subscriber.assertNoErrors();
        subscriber.assertValueCount(5);
        System.out.println(subscriber.getOnNextEvents());
    }
}
