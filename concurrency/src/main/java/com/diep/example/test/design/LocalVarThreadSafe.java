package com.diep.example.test.design;


import lombok.Setter;

/**
 * localNumber is not returned from method, nor passed to any other objects accessible from outside newNumber() method
 * The use of localNumber is thread-safe here
 */
public class LocalVarThreadSafe {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> newNumber());
        Thread t2 = new Thread(() -> newNumber());

        t1.start();
        t2.start();
    }

    public static void newNumber() {
        Number localNumber = new Number();
        modifyNumber(localNumber);
    }


    public static void modifyNumber(Number n) {
        n.setValue(15);
    }

    static class Number {
        @Setter int value = 0;
    }
}
