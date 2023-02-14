package com.diep.example.test;

import org.jmock.Expectations;
import org.junit.Rule;
import org.junit.Test;
import org.jmock.integration.junit4.JUnitRuleMockery;

/**
 * http://jmock.org/threading-executor.html
 *
 * How to use mock objects to test a class that spawns new threads?
 *
 * The problem is that jMock appears to ignore unexpected calls if they are made on a different thread than that which
 * runs the test itself. The mock object actually does throw an AssertionFailedError when it receives an unexpected call,
 * but the error terminates the concurrent thread instead of the test runner's thread.
 *
 * Example:
 * We have a guard and an alarm. When the guard notices a burglar, he should ring the alarm once only.
 * Although that implementation is incorrect, the test will appear to pass because the mock Alarm will throw an
 * AssertionFailedError on the ringAlarmThread, not the test runner's thread.
 *
 * Problem:
 * The root of the problem is trying to use mock objects for integration testing.
 * Mock objects are used for unit testing in the traditional sense: to test units in isolation from other parts of the
 * system. Threads, however, by their very nature, require some kind of integration test. Concurrency and
 * synchronisation are system-wide concerns and code that creates threads must make use of operating system facilities
 * to do so.
 *
 * Solution:
 * A solution is to separate the object that needs to run tasks from the details of how tasks are run and define an
 * interface between the two. We can test the object that needs to run tasks by mocking the task runner, and test the
 * implementation of the task runner in integration tests.
 * Java's standard concurrency library defines just such an interface: java.util.concurrent.Executor. In our example,
 * we can pass an Executor to the Guard and change the Guard so that it asks the executor to run tasks instead of
 * explicitly creating new threads.
 */
public class MutithreadTestProblem {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void ringsTheAlarmOnceWhenNoticesABurglar() {
        final Alarm alarm = context.mock(Alarm.class);
        Guard guard = new Guard(alarm);

        context.checking(new Expectations() {{
            oneOf (alarm).ring();
        }});

        guard.notice(new Burglar());
    }


    interface Alarm {
        void ring();
    }
    class Burglar {}

    class Guard {
        private Alarm alarm;

        Guard(Alarm alarm) {
            this.alarm = alarm;
        }

        void notice(Burglar burglar) {
            startRingingTheAlarm();
        }

        private void startRingingTheAlarm() {
            Runnable ringAlarmTask = new Runnable() {
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        alarm.ring();
                    }
                }
            };

            Thread ringAlarmThread = new Thread(ringAlarmTask);
            ringAlarmThread.start();

        }
    }
}
