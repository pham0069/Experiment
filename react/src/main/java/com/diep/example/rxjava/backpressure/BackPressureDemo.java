package com.diep.example.rxjava.backpressure;

import rx.BackpressureOverflow;
import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class BackPressureDemo {
    public static void main(String[] args) throws Exception {
        PublishSubject<Integer> source = PublishSubject.create();

        //buffering(source);
        //batching(source);
        //skipping(source);
        //handlingFilledBuffer(source);
        droppingOverproduced(source);

        IntStream.range(1, 1000).forEach(source::onNext);

        Thread.currentThread().sleep(10_000);
    }

    /**
     * define a buffer to give Observer time to catch up with overproducing source
     *
     * buffering may provide a temporary fix only
     * as the overflow can still happen if the source overproduces the predicted buffer size
     */
    public static void buffering(Observable<Integer> source)  {
        source.buffer(100)
                .observeOn(Schedulers.computation()) // pass a List<Integer> to observer instead of single integer
                .subscribe(ComputeFunction::compute, Throwable::printStackTrace);
    }

    /**
     * group produced element together and send a batch of elements to Observer
     * instead of element one by one
     */
    public static void batching(Observable<Integer> source)  {

        source.window(100)
                .observeOn(Schedulers.computation()) // pass a Observable<Integer> to observer instead of single integer
                .subscribe(ComputeFunction::compute, Throwable::printStackTrace);
    }

    /**
     * If some of the values produced by Observable can be safely ignored,
     * we can use the sampling within a specific time and throttling operators.
     *
     * - The sample(duration) method periodically looks into the sequence of elements
     * and emits the last item that was produced within the duration specified as a parameter
     *
     * - The throttleFirst(duration) method emits the first item that was produced
     * after the duration specified as a parameter
     *
     * These operators only reduce the rate of value reception by the downstream Observer
     * and thus they may still lead to MissingBackpressureException.
     */
    public static void skipping(Observable<Integer> source) {
        source.sample(1, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.computation())
                .subscribe(ComputeFunction::compute, Throwable::printStackTrace);
    }

    /**
     *
     * onBackpressureBuffer(
     *      int bufferCapacity,
     *      method to invoke when buffer fills up,
     *      a strategy to handle elements that need discarding from buffer)
     *
     * There are 4 types of actions that can be executed when the buffer fills up:
     *
     * ON_OVERFLOW_ERROR – this is the default behavior signaling a BufferOverflowException when the buffer is full
     * ON_OVERFLOW_DEFAULT – currently it is the same as ON_OVERFLOW_ERROR
     * ON_OVERFLOW_DROP_LATEST – if an overflow would happen, the current value will be simply ignored and only the old values will be delivered once the downstream Observer requests
     * ON_OVERFLOW_DROP_OLDEST – drops the oldest element in the buffer and adds the current value to it
     *
     * The last 2 actions cause discontinuity in the stream as they drop out elements
     * They wont signal BufferOverflowException
     */
    public static void handlingFilledBuffer(Observable<Integer> source) {
        source
                .onBackpressureBuffer(5, () -> System.out.println("Buffer full "), BackpressureOverflow.ON_OVERFLOW_DROP_LATEST)
                .observeOn(Schedulers.computation())
                .subscribe(ComputeFunction::compute, Throwable::printStackTrace);
    }

    /* Whenever the downstream Observer is not ready to receive an element,
     * we can use an onBackpressureDrop() method to drop that element from the sequence.
     * We can think of that method as an onBackpressureBuffer() method
     * with a capacity of a buffer set to zero with a strategy ON_OVERFLOW_DROP_LATEST.
     */
    public static void droppingOverproduced(Observable<Integer> source) {
        source
                .onBackpressureDrop()
                .observeOn(Schedulers.computation())
                .doOnNext(ComputeFunction::compute)
                .subscribe(v -> {}, Throwable::printStackTrace);
    }
}
