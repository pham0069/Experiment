package com.diep.example.annotation;

import java.util.Date;

public class AnnotationReflectionDemo {


    class Car {}

    @interface Identification {
        String name() default "Unknown";
        int id() default 0;
    }

}
