package com.diep.example.rxjava.operator;

import rx.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class GroupByDemo {
    public static void main(String[] args) {
        groupByDemo();
    }

    private static void  groupByDemo() {
        Integer[] numbers = IntStream
                .range(0, 10)       //IntStream
                .boxed()            //Stream<Integer>
                .toArray(Integer[]::new);

        List<Integer> even = new ArrayList<>();
        List<Integer> odd = new ArrayList<>();

        Observable.from(numbers)
                .groupBy(i -> 0 == (i % 2) ? "EVEN" : "ODD")
                .subscribe(group ->
                        group.subscribe((number) -> {
                            if (group.getKey().equals("EVEN")) {
                                even.add(number);
                            } else {
                                odd.add(number);
                            }
                        })
                );

        System.out.println(even);
        System.out.println(odd);
    }
}
