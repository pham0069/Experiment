package com.diep.example.rxjava.operator;

import rx.Observable;
import rx.Subscriber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomOperatorDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("john_1", "tom-3");
        List<String> results = new ArrayList<>();
        Observable<String> observable = Observable
                .from(list)
                .lift(ToCleanString.toCleanString()); // use lift to apply custom operator
        observable.subscribe(results::add);

        System.out.println(results);// john1, tom3
    }
}

class ToCleanString implements Observable.Operator<String, String> {

    public static ToCleanString toCleanString() {
        return new ToCleanString();
    }

    private ToCleanString() {
        super();
    }

    @Override
    public Subscriber<? super String> call(final Subscriber<? super String> subscriber) {
        return new Subscriber<String>(subscriber) {
            @Override
            public void onCompleted() {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
            }

            @Override
            public void onError(Throwable t) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onError(t);
                }
            }

            // clean up non-alphabet, non-digit character
            @Override
            public void onNext(String item) {
                if (!subscriber.isUnsubscribed()) {
                    final String result = item.replaceAll("[^A-Za-z0-9]", "");
                    subscriber.onNext(result);
                }
            }
        };
    }
}
