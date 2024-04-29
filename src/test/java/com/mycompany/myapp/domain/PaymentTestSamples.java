package com.mycompany.myapp.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class PaymentTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Payment getPaymentSample1() {
        return new Payment().id(1L).paymentID(1L).paymentMode(1);
    }

    public static Payment getPaymentSample2() {
        return new Payment().id(2L).paymentID(2L).paymentMode(2);
    }

    public static Payment getPaymentRandomSampleGenerator() {
        return new Payment().id(longCount.incrementAndGet()).paymentID(longCount.incrementAndGet()).paymentMode(intCount.incrementAndGet());
    }
}
