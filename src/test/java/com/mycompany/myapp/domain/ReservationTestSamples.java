package com.mycompany.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ReservationTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Reservation getReservationSample1() {
        return new Reservation().id(1L).reservationID(1L).comment("comment1");
    }

    public static Reservation getReservationSample2() {
        return new Reservation().id(2L).reservationID(2L).comment("comment2");
    }

    public static Reservation getReservationRandomSampleGenerator() {
        return new Reservation()
            .id(longCount.incrementAndGet())
            .reservationID(longCount.incrementAndGet())
            .comment(UUID.randomUUID().toString());
    }
}
