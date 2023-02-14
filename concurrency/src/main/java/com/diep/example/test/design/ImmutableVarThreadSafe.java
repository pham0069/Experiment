package com.diep.example.test.design;

import lombok.Data;

/**
 * Even though number is accessed by multiple threads, its use is still thread safe
 * This is because its value is final, i.e. cannot be modified.
 * Its add() method return new number without modifying its own value.
 */
public class ImmutableVarThreadSafe {
    private static ImmutableNumber number = new ImmutableNumber(12);

    public static void main(String[] args){
        Thread t1  = new Thread(() -> System.out.println(add(8)));
        Thread t2  = new Thread(() -> System.out.println(add(12)));

        t1.start();
        t2.start();
    }

    static ImmutableNumber add(int amount) {
        return number.add(amount);
    }

    @Data
    static class ImmutableNumber {
        final int value;
        ImmutableNumber add(int amount) {
            return new ImmutableNumber(this.value + amount);
        }
    }
}
