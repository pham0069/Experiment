package com.diep.example.rxjava.operator;


import rx.Observable;

public class MapDemo {
    private static final String[] letters = {"a", "b", "c", "d", "e", "f", "g"};

    public static void main(String[] args) {
        map();
    }

    private static void map() {
        Observable.from(letters)
                .map(String::toUpperCase)
                .subscribe(i -> System.out.println(i));
    }

}
