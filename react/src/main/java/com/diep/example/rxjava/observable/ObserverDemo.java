package com.diep.example.rxjava.observable;


import rx.Observable;

public class ObserverDemo {
    public static void main(String[] args) {
        String[] letters = {"a", "b", "c", "d", "e", "f", "g"};
        Observable<String> o = Observable.from(letters);
        o.subscribe(
                i -> System.out.print(i),               //onNext
                Throwable::printStackTrace,             //onError
                () -> System.out.println("\nCompleted") //onCompleted
        );
    }
}
