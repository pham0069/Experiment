package com.diep.swing.concurrency;

import javax.swing.SwingWorker;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * In this demo, EvenChecker is a task to check a collection of numbers, publish the even number found and return true
 * when task done.
 */
public class SwingWorkerDemo {
    public static void main(String[] args) {
        /*
        SwingWorker work = new EvenChecker();
        work.execute();
        while (!work.isDone()) {
            try {
                int iProgress = work.getProgress();
                System.out.println("Progress %" + iProgress);
                Thread.sleep(500);
            }
            catch (Exception ex) {
                System.err.println(ex);
            }
        }*/

        System.out.println(IntStream.range(225,249).boxed()
                .map(i -> i.toString())
                .collect(Collectors.joining(",")));
        Integer a = new Integer(10);
        double b = (double)a;
        System.out.println(b);
    }

    /**
     * Return Boolean when done
     * Publish Integer when even number found
     */
    static class EvenChecker extends SwingWorker<Boolean, Integer> {
        @Override
        protected Boolean doInBackground() {
            setProgress(0);
            waitFor(500);

            for (int iCount = 1; iCount <= 20; iCount++) {
                // Publish even numbers
                if (iCount % 2 == 0) {
                    publish(iCount);
                }
                setProgress((iCount * 100) / 20);
                waitFor(250);
            }

            return true;
        }

        @Override
        protected void process(List<Integer> chunks) {
            // Get Info
            for (int number : chunks) {
                System.out.println(Thread.currentThread() + "found even number: " + number);
            }
        }

        @Override
        protected void done() {
            boolean bStatus = false;
            try {
                bStatus = get();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " finished with status " + bStatus);
        }

        private void waitFor (int iMillis) {
            try {
                Thread.sleep(iMillis);
            }
            catch (Exception ex) {
                System.err.println(ex);
            }
        }
    }
}
