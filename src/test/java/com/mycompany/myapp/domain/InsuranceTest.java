package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.InsuranceTestSamples.*;
import static com.mycompany.myapp.domain.ReservationTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InsuranceTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Insurance.class);
        Insurance insurance1 = getInsuranceSample1();
        Insurance insurance2 = new Insurance();
        assertThat(insurance1).isNotEqualTo(insurance2);

        insurance2.setId(insurance1.getId());
        assertThat(insurance1).isEqualTo(insurance2);

        insurance2 = getInsuranceSample2();
        assertThat(insurance1).isNotEqualTo(insurance2);
    }

    @Test
    void reservationTest() throws Exception {
        Insurance insurance = getInsuranceRandomSampleGenerator();
        Reservation reservationBack = getReservationRandomSampleGenerator();

        insurance.setReservation(reservationBack);
        assertThat(insurance.getReservation()).isEqualTo(reservationBack);
        assertThat(reservationBack.getInsurance()).isEqualTo(insurance);

        insurance.reservation(null);
        assertThat(insurance.getReservation()).isNull();
        assertThat(reservationBack.getInsurance()).isNull();
    }
}
