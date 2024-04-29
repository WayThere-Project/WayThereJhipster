package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.DriverTestSamples.*;
import static com.mycompany.myapp.domain.ReservationTestSamples.*;
import static com.mycompany.myapp.domain.VehicleTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class DriverTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Driver.class);
        Driver driver1 = getDriverSample1();
        Driver driver2 = new Driver();
        assertThat(driver1).isNotEqualTo(driver2);

        driver2.setId(driver1.getId());
        assertThat(driver1).isEqualTo(driver2);

        driver2 = getDriverSample2();
        assertThat(driver1).isNotEqualTo(driver2);
    }

    @Test
    void vehicleTest() throws Exception {
        Driver driver = getDriverRandomSampleGenerator();
        Vehicle vehicleBack = getVehicleRandomSampleGenerator();

        driver.setVehicle(vehicleBack);
        assertThat(driver.getVehicle()).isEqualTo(vehicleBack);

        driver.vehicle(null);
        assertThat(driver.getVehicle()).isNull();
    }

    @Test
    void reservationsTest() throws Exception {
        Driver driver = getDriverRandomSampleGenerator();
        Reservation reservationBack = getReservationRandomSampleGenerator();

        driver.addReservations(reservationBack);
        assertThat(driver.getReservations()).containsOnly(reservationBack);
        assertThat(reservationBack.getDriver()).isEqualTo(driver);

        driver.removeReservations(reservationBack);
        assertThat(driver.getReservations()).doesNotContain(reservationBack);
        assertThat(reservationBack.getDriver()).isNull();

        driver.reservations(new HashSet<>(Set.of(reservationBack)));
        assertThat(driver.getReservations()).containsOnly(reservationBack);
        assertThat(reservationBack.getDriver()).isEqualTo(driver);

        driver.setReservations(new HashSet<>());
        assertThat(driver.getReservations()).doesNotContain(reservationBack);
        assertThat(reservationBack.getDriver()).isNull();
    }
}
