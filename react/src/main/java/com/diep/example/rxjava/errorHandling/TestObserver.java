package com.diep.example.rxjava.errorHandling;

import rx.Observer;
import rx.exceptions.CompositeException;

public class TestObserver implements Observer {
    @Override
    public void onNext(Object o) {
        System.out.println("Received: " + o);
    }

    @Override
    public void onError(Throwable e) {
        System.out.println("Error: " + e);
        if (e instanceof CompositeException){
            ((CompositeException) e).getExceptions()
                    .forEach(i -> System.out.println("* " +  i.getMessage()));
        }
    }
    @Override
    public void onCompleted() {
        System.out.println("Completed!");
    }
}
