package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.RateTestSamples.*;
import static com.mycompany.myapp.domain.ReservationTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class RateTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Rate.class);
        Rate rate1 = getRateSample1();
        Rate rate2 = new Rate();
        assertThat(rate1).isNotEqualTo(rate2);

        rate2.setId(rate1.getId());
        assertThat(rate1).isEqualTo(rate2);

        rate2 = getRateSample2();
        assertThat(rate1).isNotEqualTo(rate2);
    }

    @Test
    void reservationsTest() throws Exception {
        Rate rate = getRateRandomSampleGenerator();
        Reservation reservationBack = getReservationRandomSampleGenerator();

        rate.addReservations(reservationBack);
        assertThat(rate.getReservations()).containsOnly(reservationBack);
        assertThat(reservationBack.getRate()).isEqualTo(rate);

        rate.removeReservations(reservationBack);
        assertThat(rate.getReservations()).doesNotContain(reservationBack);
        assertThat(reservationBack.getRate()).isNull();

        rate.reservations(new HashSet<>(Set.of(reservationBack)));
        assertThat(rate.getReservations()).containsOnly(reservationBack);
        assertThat(reservationBack.getRate()).isEqualTo(rate);

        rate.setReservations(new HashSet<>());
        assertThat(rate.getReservations()).doesNotContain(reservationBack);
        assertThat(reservationBack.getRate()).isNull();
    }
}
