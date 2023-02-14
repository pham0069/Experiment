package com.diep.example.rxjava.operator;

import rx.Observable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * compose(Transformer) work on the observable itself
 *
 * lift(Operator) operates on observable's subscribers
 *
 * -> choose Transformer - to change the observable as a whole
 * choose Operator - to change the item emitted to observer
 */
public class CustomTransformerDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("john", "tom");
        List<Integer> results = new ArrayList<>();
        Observable<Integer> observable = Observable
                .from(list)
                .compose(ToLength.toLength());
        observable.subscribe(results::add);

    }
}

class ToLength implements Observable.Transformer<String, Integer> {

    public static ToLength toLength() {
        return new ToLength();
    }

    private ToLength() {
        super();
    }

    @Override
    public Observable<Integer> call(Observable<String> source) {
        return source.map(String::length);
    }
}
