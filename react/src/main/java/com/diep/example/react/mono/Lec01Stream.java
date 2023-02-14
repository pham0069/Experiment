package com.diep.example.react.mono;

import java.util.stream.Stream;

/**
 * Stream is lazy
 */
public class Lec01Stream {
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(1)
                .map((i -> {
                    try {
                        Thread.sleep(1000);
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i*2;
                }));

        // will not trigger the stream pipe due to lazy invocation
        // can see it print  immediately (instead of wait 1 s)
        System.out.println(stream);

        // only invoke actual computation when terminal is called
        // terminal like foreach, collect...
        // can see it delay 1s before printing the value
        stream.forEach(System.out::println);
    }

}
