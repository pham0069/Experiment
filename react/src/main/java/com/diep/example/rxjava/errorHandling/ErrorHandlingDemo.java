package com.diep.example.rxjava.errorHandling;

import rx.Observable;

import java.util.concurrent.atomic.AtomicBoolean;

public class ErrorHandlingDemo {
    public static void main(String[] args) {
        //actionOnError();
        //throwExceptionOnError();
        //resumeWithDefaultItems();
        //resumeWithAnotherSequence();
        handleExceptionOnly();
    }

    /**
     * do something when observable throw error
     */
    private static void actionOnError() {
        AtomicBoolean state = new AtomicBoolean(false);

        System.out.println(state.get());

        Observable
                .error(new TestError())
                .doOnError(throwable -> state.set(true))
                .subscribe(new TestObserver());

        System.out.println(state.get());
    }

    /**
     * if doOnError throws an exception,
     * the observer will receive a CompositeException that contains 2 exceptions
     * - the exception thrown on observable
     * - the exception thrown on doOnError
     */
    private static void throwExceptionOnError() {
        Observable
                .error(new TestError())
                .doOnError(throwable -> { throw new RuntimeException("Uhoh"); })
                .subscribe(new TestObserver());
    }

    private static void resumeWithDefaultItems() {
        Observable
                .error(new TestError())
                .onErrorReturn(throwable -> "defaultOnError")
                .subscribe(new TestObserver());
    }

    private static void resumeWithAnotherSequence() {
        Observable
                .error(new TestError())
                .onErrorResumeNext(Observable.just("one", "two"))
                .subscribe(new TestObserver());

    }

    /**
     * continue the sequence with provided Observable when exception (not error)occurs
     */
    private static void handleExceptionOnly() {
        Observable
                .error(new TestException())     //exception
                .onExceptionResumeNext(Observable.just("exceptionResumed"))
                .subscribe(new TestObserver());

        Observable
                .error(new TestError())         //error
                .onExceptionResumeNext(Observable.just("exceptionResumed"))
                .subscribe(new TestObserver());
    }
}
