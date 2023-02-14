package com.diep.example.rxjava.operator;

import rx.Observable;

public class ScanDemo {
    public static void main(String[] args) {
        scanDemo();
    }

    private static void scanDemo() {
        Integer[] array = {1, 2, 3};
        Observable.from(array)
                //apply function to each emitted item and emit successive value
                .scan(0, (i, j) -> i+j)
                .subscribe(sumSoFar -> System.out.println(sumSoFar));
    }
}
