package com.diep.example.rxjava.observable;

import rx.Observable;

public class ObservableDemo {
    public static void main(String[] args) {
        Observable<String> o = Observable.just("hello");
        o.subscribe(s -> System.out.println(s));
    }
}
