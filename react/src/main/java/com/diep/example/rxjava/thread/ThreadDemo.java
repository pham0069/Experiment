package com.diep.example.rxjava.thread;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * https://er-abhishek-luthra.medium.com/rxjava-subscribeon-vs-observeon-d57703dcc349
 *
 */
public class ThreadDemo {
    public static void main(String[] args) throws Exception {
        //noThreadSpecification();
        //subscribeOnOnly();
        //observeOnOnly();
        subscribeOnBeforeObserveOn();
        observeOnBeforeSubscribeOn();
        Thread.currentThread().sleep(5_000);
    }

    private static void print(int length) {
        System.out.println("item length " + length + " on thread "  + Thread.currentThread().getName());
    }

    /**
     * without thread specification by subcribeOn/observeOn
     * the execution is on the current thread (in this case main thread)
     */
    private static void noThreadSpecification() {
        Observable
                .just("long", "longer", "longest")
                .map(String::length)
                .filter(i -> i > 6)
                .subscribe(length -> print(length));
    }

    /**
     * subscribeOn affects upstream operators (those above subscribeOn)
     *
     * if only subscribeOn is specified, all operators will be be executed on that thread
     * i.e. just, map, filter, subscribe are all executed  onn io scheduler
     */
    private static void subscribeOnOnly() {
        Observable
                .just("long", "longer", "longest")
                .subscribeOn(Schedulers.io())
                .map(String::length)
                .filter(i -> i > 6)
                .subscribe(length -> print(length));
    }

    /**
     * observeOn affects downstream operators (those below observeOn)
     *
     * if only observeOn is specified, all operators will be executed on the current thread
     * and only operators below the observeOn will be switched to thread specified by the observeOn
     * i.e.  just and map are executed on current thread (in  this case main)
     * filter and subscribe  are executed on computation scheduler
     */
    private static void observeOnOnly() {
        Observable
                .just("long", "longer", "longest")
                .map(String::length)
                .observeOn(Schedulers.computation())
                .filter(i ->  i > 6)
                .subscribe(length -> print(length));
    }

    /**
     * all the operators below the observeOn will be switched to thread specified by the observeOn
     * and rest all operators above observeOn are switched to thread specified by subscribeOn
     * i.e. just and map executed on io thread specified by subscribeOn
     * filter and subscribe on computation thread specified by observeOn
     */
    private static void subscribeOnBeforeObserveOn() {
        Observable
                .just("long", "longer", "longest")  //io
                .subscribeOn(Schedulers.io())
                .map(String::length)                //io
                .observeOn(Schedulers.computation())
                .filter(i ->  i > 6)                 //comp
                .subscribe(length -> print(length)); //comp
    }

    /**
     * The above holds true even if observeOn comes before subscribeOn
     * i.e. filter and subscribe (come after observeOn) are executed on thread specified by observeOn
     * just and map (come before subscribeOn) are executed on thread specified by subscribeOn
     *
     */
    private static void observeOnBeforeSubscribeOn() {
        Observable
                .just("long", "longer", "longest")  //io
                .map(String::length)                //io
                .observeOn(Schedulers.computation())
                .filter(i ->  i > 6)                //comp
                .subscribeOn(Schedulers.io())
                .subscribe(length -> print(length)); //comp
    }
}
