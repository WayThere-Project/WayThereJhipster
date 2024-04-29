package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.DriverTestSamples.*;
import static com.mycompany.myapp.domain.ReservationTestSamples.*;
import static com.mycompany.myapp.domain.VehicleTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class VehicleTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Vehicle.class);
        Vehicle vehicle1 = getVehicleSample1();
        Vehicle vehicle2 = new Vehicle();
        assertThat(vehicle1).isNotEqualTo(vehicle2);

        vehicle2.setId(vehicle1.getId());
        assertThat(vehicle1).isEqualTo(vehicle2);

        vehicle2 = getVehicleSample2();
        assertThat(vehicle1).isNotEqualTo(vehicle2);
    }

    @Test
    void reservationsTest() throws Exception {
        Vehicle vehicle = getVehicleRandomSampleGenerator();
        Reservation reservationBack = getReservationRandomSampleGenerator();

        vehicle.addReservations(reservationBack);
        assertThat(vehicle.getReservations()).containsOnly(reservationBack);
        assertThat(reservationBack.getVehicle()).isEqualTo(vehicle);

        vehicle.removeReservations(reservationBack);
        assertThat(vehicle.getReservations()).doesNotContain(reservationBack);
        assertThat(reservationBack.getVehicle()).isNull();

        vehicle.reservations(new HashSet<>(Set.of(reservationBack)));
        assertThat(vehicle.getReservations()).containsOnly(reservationBack);
        assertThat(reservationBack.getVehicle()).isEqualTo(vehicle);

        vehicle.setReservations(new HashSet<>());
        assertThat(vehicle.getReservations()).doesNotContain(reservationBack);
        assertThat(reservationBack.getVehicle()).isNull();
    }

    @Test
    void driverTest() throws Exception {
        Vehicle vehicle = getVehicleRandomSampleGenerator();
        Driver driverBack = getDriverRandomSampleGenerator();

        vehicle.setDriver(driverBack);
        assertThat(vehicle.getDriver()).isEqualTo(driverBack);
        assertThat(driverBack.getVehicle()).isEqualTo(vehicle);

        vehicle.driver(null);
        assertThat(vehicle.getDriver()).isNull();
        assertThat(driverBack.getVehicle()).isNull();
    }
}
