package com.diep.example.rxjava.operator;

import rx.Observable;

public class FlatMapDemo {
    private static final String[] letters = {"a", "b", "c"};

    public static void main(String[] args) {
        flatMap();
    }

    private static void flatMap() {
        Observable.from(letters)
                .flatMap(i -> Observable.just(i.toUpperCase()+"*", i+"?"))
                // flatten observable of observables into single observable
                // [A*, a?], [B*, b?], [C*, c?] => [A*, a?, B*, b?, C*, c?]
                .subscribe(i -> System.out.print(i + " "));

    }
}
