package com.mycompany.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class VehicleTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Vehicle getVehicleSample1() {
        return new Vehicle().id(1L).vehicleID(1L).vehicleSize(1).make("make1").model("model1");
    }

    public static Vehicle getVehicleSample2() {
        return new Vehicle().id(2L).vehicleID(2L).vehicleSize(2).make("make2").model("model2");
    }

    public static Vehicle getVehicleRandomSampleGenerator() {
        return new Vehicle()
            .id(longCount.incrementAndGet())
            .vehicleID(longCount.incrementAndGet())
            .vehicleSize(intCount.incrementAndGet())
            .make(UUID.randomUUID().toString())
            .model(UUID.randomUUID().toString());
    }
}
