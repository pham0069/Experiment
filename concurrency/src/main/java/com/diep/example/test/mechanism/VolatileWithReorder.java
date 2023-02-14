package com.diep.example.test.mechanism;

/**
 * http://tutorials.jenkov.com/java-concurrency/volatile.html
 *
 * JVM and CPU may reorder the instructions in the program to optimize performance, as long as semantic meaning of the
 * instructions do not change
 *
 * Take a look at Duration class, with update() and reorderUpdate() methods doing the same thing. reorderUpdate() is an
 * example of how JVM may reorder the method update()
 *
 * In the context that days variable is volatile, update() will guarantee full visibility, as when days is written,
 * all other variables visible like years and months are also written with updated value
 *
 * In reorderUpdate(), when days is written, years and months values are also written into main memory, however, their
 * written values are not updated yet, at that moment. Hence reorderUpdate() cannot guarantee visibility as expected
 * ---------------------------------------------------------------------------------------------------------------------
 * To address the instruction reordering challenge, the Java volatile keyword gives a "happens-before" guarantee, in
 * addition to the visibility guarantee as follows:
 *
 * 1. Reads from and writes to other variables cannot be reordered to occur after a write to a volatile variable, if
 * the reads / writes originally occurred before the write to the volatile variable. The reads / writes before a write
 * to a volatile variable are guaranteed to "happen before" the write to the volatile variable. Notice that it is still
 * possible for e.g. reads / writes of other variables located after a write to a volatile to be reordered to occur
 * before that write to the volatile. Just not the other way around. From after to before is allowed, but from before
 * to after is not allowed.
 *
 * 2. Reads from and writes to other variables cannot be reordered to occur before a read of a volatile variable, if the
 * reads / writes originally occurred after the read of the volatile variable. Notice that it is possible for reads of
 * other variables that occur before the read of a volatile variable can be reordered to occur after the read of the
 * volatile. Just not the other way around. From before to after is allowed, but from after to before is not allowed.
 *
 * These happen-before guarantee ensures enforcement of volatile keyword
 */
public class VolatileWithReorder {
    static class Duration {
        private int years;
        private int months;
        private volatile int days;

        public void update(int years, int months, int days){
            this.years  = years;
            this.months = months;
            this.days   = days;
        }

        public void reorderUpdate(int years, int months, int days){
            this.days   = days;
            this.years  = years;
            this.months = months;
        }
    }

}
