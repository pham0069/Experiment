package com.diep.example.rxjava.observable;

import rx.Observable;
import rx.Single;

public class SingleDemo {
    public static void main(String[] args) {
        canConvert();
        failToConvert();
    }

    private static void canConvert() {
        convertToSingle(Observable.just("Hello"));
    }

    private static void failToConvert() {
        convertToSingle(Observable.just("Hello", "Hi"));
    }

    private static void convertToSingle(Observable<String> observable) {
        // single only emit 1 value or give an error notification
        Single<String> single = observable
                .toSingle()
                .doOnSuccess(i -> System.out.println(i))
                .doOnError(error -> System.out.println("Single failed!!!"));
        single.subscribe();
    }


}
