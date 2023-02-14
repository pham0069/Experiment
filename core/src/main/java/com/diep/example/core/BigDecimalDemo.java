package com.diep.example.core;

import java.math.BigDecimal;

public class BigDecimalDemo {
    public static void main(String[] args) {
        double n = 0.1;
        // Use canonical String representation of d

        BigDecimal a = BigDecimal.valueOf(n);

        // Represent double value as accurately as possible, usually in a lot more digits
        // it is more correct but less intuitive than valueOf
        BigDecimal b = new BigDecimal(n);

        // valueOf do caching of common value, preferred than new
        BigDecimal five = BigDecimal.valueOf(5);
        BigDecimal cinco = BigDecimal.valueOf(5);

        System.out.println("n =" + n);
        System.out.println("a = " + a.toString());
        System.out.println("b = " + b.toString());
        System.out.println("Five and cinco same object?" +(five == cinco));

    }
}
