package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.CustomerTestSamples.*;
import static com.mycompany.myapp.domain.PaymentTestSamples.*;
import static com.mycompany.myapp.domain.ReservationTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class PaymentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Payment.class);
        Payment payment1 = getPaymentSample1();
        Payment payment2 = new Payment();
        assertThat(payment1).isNotEqualTo(payment2);

        payment2.setId(payment1.getId());
        assertThat(payment1).isEqualTo(payment2);

        payment2 = getPaymentSample2();
        assertThat(payment1).isNotEqualTo(payment2);
    }

    @Test
    void reservationTest() throws Exception {
        Payment payment = getPaymentRandomSampleGenerator();
        Reservation reservationBack = getReservationRandomSampleGenerator();

        payment.addReservation(reservationBack);
        assertThat(payment.getReservations()).containsOnly(reservationBack);
        assertThat(reservationBack.getPayment()).isEqualTo(payment);

        payment.removeReservation(reservationBack);
        assertThat(payment.getReservations()).doesNotContain(reservationBack);
        assertThat(reservationBack.getPayment()).isNull();

        payment.reservations(new HashSet<>(Set.of(reservationBack)));
        assertThat(payment.getReservations()).containsOnly(reservationBack);
        assertThat(reservationBack.getPayment()).isEqualTo(payment);

        payment.setReservations(new HashSet<>());
        assertThat(payment.getReservations()).doesNotContain(reservationBack);
        assertThat(reservationBack.getPayment()).isNull();
    }

    @Test
    void customerTest() throws Exception {
        Payment payment = getPaymentRandomSampleGenerator();
        Customer customerBack = getCustomerRandomSampleGenerator();

        payment.setCustomer(customerBack);
        assertThat(payment.getCustomer()).isEqualTo(customerBack);

        payment.customer(null);
        assertThat(payment.getCustomer()).isNull();
    }
}
