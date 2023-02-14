package com.diep.example.rxjava.operator;

import rx.Observable;

import java.util.stream.IntStream;

public class FilterDemo {
    public static void main(String[] args) {
        filterDemo();
    }

    private static void filterDemo() {
        Integer[] numbers = IntStream
                .range(0, 10)       //IntStream
                .boxed()            //Stream<Integer>
                .toArray(Integer[]::new);

        Observable.from(numbers)
                .filter(i -> (i % 2 == 1))
                .subscribe(i -> System.out.println(i));
    }
}
