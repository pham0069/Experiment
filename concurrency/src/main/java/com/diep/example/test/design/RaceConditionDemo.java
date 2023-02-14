package com.diep.example.test.design;

import lombok.AllArgsConstructor;

/**
 *
 * http://tutorials.jenkov.com/java-concurrency/race-conditions-and-critical-sections.html
 *
 * A critical section is a section of code that is executed by multiple threads and where the sequence of execution for
 * the threads makes a difference in the result of the concurrent execution of the critical section.
 *
 * When the result of multiple threads executing a critical section may differ depending on the sequence in which the
 * threads execute, the critical section is said to contain a race condition.
 *
 * The program below illustrates a scenario of race condition
 * Mutithreading that com.diep.concurrency.design.Counter::add() method is not executed as a single atomic instruction by JVM. Rather it is executed as a set
 * of smaller instructions like:
 * 1. Read this.count from memory into register.
 * 2. Add value to register.
 * 3. Write register to memory.
 *
 * When two threads execute add() at the same time, the instructions could be executed in interleaving manner. E.g.
 * 1. A reads this.count into a register (0)
 * 2. B reads this.count into a register (0)
 * 3. B adds value 3 to register
 * 4. B writes register value (2) back to memory. this.count now equals 3
 * 5. A adds value 2 to register
 * 6. A writes register value (2) back to memory. this.count now equals 2
 * The final result is 2, instead of 5 as desired
 *
 * The order of executions could change, affecting the final result
 *
 */
public class RaceConditionDemo {
    public static void main(String[] args) {
        // Create two threads that would try to update the same counter
        Counter counter = new Counter();
        Thread a = new Thread(new UpdateCounter(counter, 2));
        Thread b = new Thread(new UpdateCounter(counter, 3));

        // Start two threads
        a.start();
        b.start();

        // Verify that result may not be 5
        System.out.println("com.diep.concurrency.design.Counter = " + counter.count);
    }
}

@AllArgsConstructor
class UpdateCounter implements Runnable {
    private final Counter counter;
    private final int amount;

    @Override
    public void run() {
        counter.add(amount);
    }
}
class Counter {
    protected long count = 0;

    /**
     * Critical section
     * @param value
     */
    public void add(int value){
        this.count += value;
    }
}
