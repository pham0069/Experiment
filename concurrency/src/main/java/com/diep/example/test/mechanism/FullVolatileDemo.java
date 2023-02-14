package com.diep.example.test.mechanism;

import lombok.Data;

/**
 * The visibility guarantee goes beyond the volatile variable itself:
 *
 * 1. If Thread A writes to a volatile variable and Thread B subsequently reads the same volatile variable, then all
 * variables visible to Thread A before writing the volatile variable, will also be visible to Thread B after it has
 * read the volatile variable.
 * 2. If Thread A reads a volatile variable, then all all variables visible to Thread A when reading the volatile
 * variable will also be re-read from main memory.
 *
 * In this demo, class Duration only has volatile days variable. This could work properly if read and write methods have
 * a proper sequence.
 * In update() method, when new value is written to days, all values before it, i.e. years and months are also written
 * to main memory (even though they are not volatile). Thus, days should be updated last in the method
 *
 * In getTotalDays() method, when days value is read, the values of years and months are also read into main memory.
 * Thus, days should be read first in the method
 */
public class FullVolatileDemo {
    public static void main(String[] args) {
        runReaderWriter();
    }

    static void runReaderWriter() {
        Duration d = new Duration();
        Thread writer = new Thread(new Writer(d));
        Thread reader = new Thread(new Reader(d));

        writer.start();
        reader.start();
    }


    @Data
    static class Reader implements Runnable {
        private final Duration duration;

        @Override
        public void run() {
            System.out.println(duration.getTotalDays());
        }
    }

    @Data
    static class Writer implements Runnable {
        private final Duration duration;

        @Override
        public void run() {
            duration.update(duration.years + 1, duration.months + 2, duration.days + 3);
        }
    }

    static class Duration {
        private int years;
        private int months;
        private volatile int days;


        public int getTotalDays() {
            int total = this.days;
            total += months * 30;
            total += years * 365;
            return total;
        }

        public void update(int years, int months, int days) {
            this.years = years;
            this.months = months;
            this.days = days;
        }
    }

}