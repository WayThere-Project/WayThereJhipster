package com.mycompany.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DriverTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Driver getDriverSample1() {
        return new Driver().id(1L).driverID(1L).fname("fname1").lname("lname1");
    }

    public static Driver getDriverSample2() {
        return new Driver().id(2L).driverID(2L).fname("fname2").lname("lname2");
    }

    public static Driver getDriverRandomSampleGenerator() {
        return new Driver()
            .id(longCount.incrementAndGet())
            .driverID(longCount.incrementAndGet())
            .fname(UUID.randomUUID().toString())
            .lname(UUID.randomUUID().toString());
    }
}
