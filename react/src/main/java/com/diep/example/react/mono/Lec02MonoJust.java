package com.diep.example.react.mono;

import com.diep.example.react.util.Util;
import reactor.core.publisher.Mono;

/**
 * Mono is a publisher implementation
 *
 */
public class Lec02MonoJust {
    public static void main(String[] args) {
        // publisher that produce just the specified item
        Mono<Integer> mono = Mono.just(1);

        System.out.println(mono);

        // only produce upon subscribe
        mono.subscribe(i -> System.out.println("Received : " + i));

        mono.subscribe(i -> System.out.println("Also received : " + i));
    }
}
