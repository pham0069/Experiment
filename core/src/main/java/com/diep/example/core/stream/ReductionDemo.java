package com.diep.example.core.stream;

import java.util.Collection;

public class ReductionDemo {
    public static void main(String[] args) {
    }

    static void concatenate(Collection<String> strings) {
        //concat entails copying whole string
        //complexity is O(n^2) as some string has to be copied many times
        strings.stream().reduce("", String::concat);

        //use string builder to reduce complexity to O(n)
        strings.stream()
                .collect(() -> new StringBuilder(), //build StringBuilder
                        (sb, s) -> sb.append(s),
                        (sb, sb2) -> sb.append(sb2))
                .toString();                        //build String
    }

    static void sum(Collection<Integer> ints) {
        ints.stream().mapToInt(Integer::intValue).sum();
        ints.stream().reduce(0, (x, y) -> x+y);
    }
}
