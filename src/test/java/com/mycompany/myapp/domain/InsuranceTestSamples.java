package com.mycompany.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class InsuranceTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Insurance getInsuranceSample1() {
        return new Insurance().id(1L).insID(1L).insDescription("insDescription1");
    }

    public static Insurance getInsuranceSample2() {
        return new Insurance().id(2L).insID(2L).insDescription("insDescription2");
    }

    public static Insurance getInsuranceRandomSampleGenerator() {
        return new Insurance()
            .id(longCount.incrementAndGet())
            .insID(longCount.incrementAndGet())
            .insDescription(UUID.randomUUID().toString());
    }
}
