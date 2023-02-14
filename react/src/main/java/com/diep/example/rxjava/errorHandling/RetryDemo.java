package com.diep.example.rxjava.errorHandling;

import rx.Observable;

import java.util.concurrent.atomic.AtomicInteger;

public class RetryDemo {
    public static void main(String[] args) {
        //retry();
        //retryOnCondition();
        retryWhen();
    }

    /**
     * observable will be re-subscribed until when there's no error
     * or when number of retries has been reached
     */
    private static void retry() {
        TestObserver testObserver = new TestObserver();
        AtomicInteger counter = new AtomicInteger(0);
        Observable
                .error(new TestError())
                .doOnError(throwable -> { counter.incrementAndGet(); })
                .retry(3)
                .subscribe(testObserver);

        // first subscription + 3 retries -> 4 times of encountering error
        System.out.println(counter.get()); // 4
    }

    private static void retryOnCondition() {
        TestObserver testObserver = new TestObserver();
        AtomicInteger counter = new AtomicInteger(0);
        Observable
                .error(new TestError())
                .doOnError(throwable -> { counter.incrementAndGet(); })
                .retry((integer, throwable) -> integer < 4) // same as above
                .subscribe(testObserver);

        // first subscription + 3 retries -> 4 times of encountering error
        System.out.println(counter.get()); // 4
    }

    private static void retryWhen() {
        TestObserver testObserver = new TestObserver();
        Exception noRetryException = new Exception("Don't retry");
        AtomicInteger counter = new AtomicInteger(0);
        Observable
                .error(new TestError())
                .doOnError(throwable -> { counter.incrementAndGet(); })
                .retryWhen(throwableObservable -> Observable.error(noRetryException))
                .subscribe(testObserver);

        System.out.println(counter.get());
    }
}
