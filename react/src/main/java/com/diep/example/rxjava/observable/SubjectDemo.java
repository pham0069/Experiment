package com.diep.example.rxjava.observable;

import lombok.Getter;
import rx.Observer;
import rx.subjects.PublishSubject;

public class SubjectDemo {

    static class MyObserver implements Observer<Integer> {
        MyObserver(String id) {
            this.id = id;
        }
        private final String id;
        @Getter private int sum = 0;

        @Override
        public void onNext(Integer value) {
            sum += value;
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("Error");
        }

        @Override
        public void onCompleted() {
            System.out.println("Subscriber " + id + " completed");
        }
    }


    public static void main(String[] args) {
        PublishSubject<Integer> subject = PublishSubject.create();
        MyObserver orange = new MyObserver("orange");
        MyObserver melon = new MyObserver("melon");

        subject.subscribe(orange);
        subject.onNext(1);
        subject.onNext(2);
        subject.onNext(3);
        subject.subscribe(melon);
        subject.onNext(4);
        subject.onCompleted();

        System.out.println(orange.getSum());
        System.out.println(melon.getSum());
    }

}
