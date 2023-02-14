package com.diep.example.rxjava.operator;

import rx.Observable;

public class ConditionalOperatorDemo {
    public static void main(String[] args) {
        defaultIfEmptyDemo();
        firstDemo();
        takeWhileDemo();
    }

    private static void defaultIfEmptyDemo() {
        Observable.empty()
                .defaultIfEmpty("Observable is empty")
                .subscribe(s -> System.out.println(s));
    }

    private static void firstDemo() {
        String[] letters = { "a", "b", "c", "d"};
        Observable.from(letters)
                .defaultIfEmpty("Observable is empty")
                // emit  the first item only, i.e. a
                .first()
                .subscribe(s -> System.out.println(s));
    }

    private static void takeWhileDemo() {
        Integer[] numbers = {2, 4, 6, 8};
        Observable.from(numbers)
                .takeWhile(i -> i < 5)
                .subscribe(s -> System.out.println(s));
    }
}
