package com.diep.example.test;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.concurrent.DeterministicScheduler;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.ScheduledExecutorService;

/**
 * http://jmock.org/threading-executor.html
 *
 *
 */
public class DeterministicSchedulerDemo {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void ringsTheAlarmOnceWhenNoticesABurglar() {
        final Alarm alarm = context.mock(Alarm.class);
        DeterministicScheduler scheduler = new DeterministicScheduler();
        Guard guard = new Guard(alarm, scheduler);

        guard.notice(new Burglar());

        context.checking(new Expectations() {{
            //oneOf (alarm).ring();
            exactly(10).of(alarm).ring();
        }});

        scheduler.runUntilIdle();
    }

    interface Alarm {
        void ring();
    }
    class Burglar {}

    class Guard {
        private Alarm alarm;
        private ScheduledExecutorService scheduler;

        Guard(Alarm alarm, ScheduledExecutorService scheduler) {
            this.alarm = alarm;
            this.scheduler = scheduler;
        }

        void notice(Burglar burglar) {
            startRingingTheAlarm();
        }

        private void startRingingTheAlarm() {
            Runnable ringAlarmTask = new Runnable() {
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        alarm.ring();
                        System.out.println("Alarm");
                    }
                }
            };

            scheduler.submit(ringAlarmTask);
        }
    }
}
