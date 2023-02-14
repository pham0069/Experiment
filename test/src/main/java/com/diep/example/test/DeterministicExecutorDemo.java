package com.diep.example.test;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.concurrent.DeterministicExecutor;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.Executor;

/**
 * http://jmock.org/threading-executor.html
 *
 *
 */
public class DeterministicExecutorDemo {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void ringsTheAlarmOnceWhenNoticesABurglar() {
        final Alarm alarm = context.mock(Alarm.class);
        DeterministicExecutor executor = new DeterministicExecutor();
        Guard guard = new Guard(alarm, executor);

        guard.notice(new Burglar());

        context.checking(new Expectations() {{
            //oneOf (alarm).ring();
            exactly(10).of(alarm).ring();
        }});

        executor.runUntilIdle();
    }

    interface Alarm {
        void ring();
    }
    class Burglar {}

    class Guard {
        private Alarm alarm;
        private Executor executor;

        Guard(Alarm alarm, Executor executor) {
            this.alarm = alarm;
            this.executor = executor;
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

            executor.execute(ringAlarmTask);
        }
    }
}
