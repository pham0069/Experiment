package com.diep.example.annotation;

import java.lang.annotation.Repeatable;

public class RepeatingAnnotationDemo {
    /**
     * Declare a repeatable annotation type
     *
     * Use @Repeatable annotation for that purpose
     * Specify the class used as container annotation that Java compiler generates to store repeating annotation
     * In this example, container annotation type is Schedules.class,
     * so repeating Schedule annotations are stored in an @Schedules annotation
     */
    @Repeatable(Schedules.class)
    public @interface Schedule {
        String dayOfMonth() default "first";
        String dayOfWeek() default "Mon";
        int hour() default 12;
    }

    /**
     *  Declare the containing annotation type
     *
     *  The containing annotation type must have a value element with array type
     */
    public @interface Schedules {
        Schedule[] value();
    }

    /**
     * Demo using repeatable annotation
     * Use a timer service that enables running a method at a given time or on a certain schedule
     *
     */
    @Schedule(dayOfMonth="last")
    @Schedule(dayOfWeek="Fri", hour=23)
    public void doPeriodicCleanup() {

    }

    public interface House {
        @Deprecated
        void open();
        void openFrontDoor();
        void openBackDoor();
    }
}
