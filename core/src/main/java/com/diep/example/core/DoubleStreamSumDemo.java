package com.diep.example.core;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DoubleStreamSumDemo {
    public static void main(String[] args) {
        List<Double> list = Arrays.asList(null, Double.valueOf(1), Double.valueOf(2));
        double sum = list.stream()
                .filter(Objects::nonNull)
                .mapToDouble(Double::doubleValue)
                .sum();
        System.out.println(sum);
    }
}
