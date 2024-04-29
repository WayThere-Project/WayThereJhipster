package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.CustomerTestSamples.*;
import static com.mycompany.myapp.domain.PaymentTestSamples.*;
import static com.mycompany.myapp.domain.ReservationTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class CustomerTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Customer.class);
        Customer customer1 = getCustomerSample1();
        Customer customer2 = new Customer();
        assertThat(customer1).isNotEqualTo(customer2);

        customer2.setId(customer1.getId());
        assertThat(customer1).isEqualTo(customer2);

        customer2 = getCustomerSample2();
        assertThat(customer1).isNotEqualTo(customer2);
    }

    @Test
    void paymentsTest() throws Exception {
        Customer customer = getCustomerRandomSampleGenerator();
        Payment paymentBack = getPaymentRandomSampleGenerator();

        customer.addPayments(paymentBack);
        assertThat(customer.getPayments()).containsOnly(paymentBack);
        assertThat(paymentBack.getCustomer()).isEqualTo(customer);

        customer.removePayments(paymentBack);
        assertThat(customer.getPayments()).doesNotContain(paymentBack);
        assertThat(paymentBack.getCustomer()).isNull();

        customer.payments(new HashSet<>(Set.of(paymentBack)));
        assertThat(customer.getPayments()).containsOnly(paymentBack);
        assertThat(paymentBack.getCustomer()).isEqualTo(customer);

        customer.setPayments(new HashSet<>());
        assertThat(customer.getPayments()).doesNotContain(paymentBack);
        assertThat(paymentBack.getCustomer()).isNull();
    }

    @Test
    void reservationsTest() throws Exception {
        Customer customer = getCustomerRandomSampleGenerator();
        Reservation reservationBack = getReservationRandomSampleGenerator();

        customer.addReservations(reservationBack);
        assertThat(customer.getReservations()).containsOnly(reservationBack);
        assertThat(reservationBack.getCustomer()).isEqualTo(customer);

        customer.removeReservations(reservationBack);
        assertThat(customer.getReservations()).doesNotContain(reservationBack);
        assertThat(reservationBack.getCustomer()).isNull();

        customer.reservations(new HashSet<>(Set.of(reservationBack)));
        assertThat(customer.getReservations()).containsOnly(reservationBack);
        assertThat(reservationBack.getCustomer()).isEqualTo(customer);

        customer.setReservations(new HashSet<>());
        assertThat(customer.getReservations()).doesNotContain(reservationBack);
        assertThat(reservationBack.getCustomer()).isNull();
    }
}
