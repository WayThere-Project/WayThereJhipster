package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.CustomerTestSamples.*;
import static com.mycompany.myapp.domain.DriverTestSamples.*;
import static com.mycompany.myapp.domain.InsuranceTestSamples.*;
import static com.mycompany.myapp.domain.LocationTestSamples.*;
import static com.mycompany.myapp.domain.PaymentTestSamples.*;
import static com.mycompany.myapp.domain.RateTestSamples.*;
import static com.mycompany.myapp.domain.ReservationTestSamples.*;
import static com.mycompany.myapp.domain.VehicleTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ReservationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Reservation.class);
        Reservation reservation1 = getReservationSample1();
        Reservation reservation2 = new Reservation();
        assertThat(reservation1).isNotEqualTo(reservation2);

        reservation2.setId(reservation1.getId());
        assertThat(reservation1).isEqualTo(reservation2);

        reservation2 = getReservationSample2();
        assertThat(reservation1).isNotEqualTo(reservation2);
    }

    @Test
    void insuranceTest() throws Exception {
        Reservation reservation = getReservationRandomSampleGenerator();
        Insurance insuranceBack = getInsuranceRandomSampleGenerator();

        reservation.setInsurance(insuranceBack);
        assertThat(reservation.getInsurance()).isEqualTo(insuranceBack);

        reservation.insurance(null);
        assertThat(reservation.getInsurance()).isNull();
    }

    @Test
    void pickupLocationTest() throws Exception {
        Reservation reservation = getReservationRandomSampleGenerator();
        Location locationBack = getLocationRandomSampleGenerator();

        reservation.setPickupLocation(locationBack);
        assertThat(reservation.getPickupLocation()).isEqualTo(locationBack);

        reservation.pickupLocation(null);
        assertThat(reservation.getPickupLocation()).isNull();
    }

    @Test
    void dropoffLocationTest() throws Exception {
        Reservation reservation = getReservationRandomSampleGenerator();
        Location locationBack = getLocationRandomSampleGenerator();

        reservation.setDropoffLocation(locationBack);
        assertThat(reservation.getDropoffLocation()).isEqualTo(locationBack);

        reservation.dropoffLocation(null);
        assertThat(reservation.getDropoffLocation()).isNull();
    }

    @Test
    void customerTest() throws Exception {
        Reservation reservation = getReservationRandomSampleGenerator();
        Customer customerBack = getCustomerRandomSampleGenerator();

        reservation.setCustomer(customerBack);
        assertThat(reservation.getCustomer()).isEqualTo(customerBack);

        reservation.customer(null);
        assertThat(reservation.getCustomer()).isNull();
    }

    @Test
    void driverTest() throws Exception {
        Reservation reservation = getReservationRandomSampleGenerator();
        Driver driverBack = getDriverRandomSampleGenerator();

        reservation.setDriver(driverBack);
        assertThat(reservation.getDriver()).isEqualTo(driverBack);

        reservation.driver(null);
        assertThat(reservation.getDriver()).isNull();
    }

    @Test
    void vehicleTest() throws Exception {
        Reservation reservation = getReservationRandomSampleGenerator();
        Vehicle vehicleBack = getVehicleRandomSampleGenerator();

        reservation.setVehicle(vehicleBack);
        assertThat(reservation.getVehicle()).isEqualTo(vehicleBack);

        reservation.vehicle(null);
        assertThat(reservation.getVehicle()).isNull();
    }

    @Test
    void rateTest() throws Exception {
        Reservation reservation = getReservationRandomSampleGenerator();
        Rate rateBack = getRateRandomSampleGenerator();

        reservation.setRate(rateBack);
        assertThat(reservation.getRate()).isEqualTo(rateBack);

        reservation.rate(null);
        assertThat(reservation.getRate()).isNull();
    }

    @Test
    void paymentTest() throws Exception {
        Reservation reservation = getReservationRandomSampleGenerator();
        Payment paymentBack = getPaymentRandomSampleGenerator();

        reservation.setPayment(paymentBack);
        assertThat(reservation.getPayment()).isEqualTo(paymentBack);

        reservation.payment(null);
        assertThat(reservation.getPayment()).isNull();
    }
}
