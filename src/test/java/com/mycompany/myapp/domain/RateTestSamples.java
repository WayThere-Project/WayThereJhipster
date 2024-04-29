package com.mycompany.myapp.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class RateTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Rate getRateSample1() {
        return new Rate().id(1L).rateID(1L).timeOfDay(1).vehicleSize(1).baseRate(1);
    }

    public static Rate getRateSample2() {
        return new Rate().id(2L).rateID(2L).timeOfDay(2).vehicleSize(2).baseRate(2);
    }

    public static Rate getRateRandomSampleGenerator() {
        return new Rate()
            .id(longCount.incrementAndGet())
            .rateID(longCount.incrementAndGet())
            .timeOfDay(intCount.incrementAndGet())
            .vehicleSize(intCount.incrementAndGet())
            .baseRate(intCount.incrementAndGet());
    }
}
