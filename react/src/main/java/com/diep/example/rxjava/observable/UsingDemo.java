package com.diep.example.rxjava.observable;

import rx.Observable;

public class UsingDemo {
    public static void main(String[] args) {
        String[] result = {""};
        Observable<Character> values = Observable.using(
                () -> "MyResource",                     // resource Factory
                r -> {                                  // observable Factory
                    return Observable.create(o -> {
                        for (Character c : r.toCharArray()) {
                            o.onNext(c);
                        }
                        o.onCompleted();
                    });
                },
                r -> System.out.println("Disposed: " + r)// dispose action
        );
        values.subscribe(
                v -> result[0] += v,
                e -> result[0] += e
        );

        System.out.println(result[0]);
    }
}
