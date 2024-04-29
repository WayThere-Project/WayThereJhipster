package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Rate.
 */
@Entity
@Table(name = "rate")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Rate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "rate_id")
    private Long rateID;

    @Column(name = "time_of_day")
    private Integer timeOfDay;

    @Column(name = "vehicle_size")
    private Integer vehicleSize;

    @Column(name = "base_rate")
    private Integer baseRate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rate")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "insurance", "pickupLocation", "dropoffLocation", "customer", "driver", "vehicle", "rate", "payment" },
        allowSetters = true
    )
    private Set<Reservation> reservations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Rate id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRateID() {
        return this.rateID;
    }

    public Rate rateID(Long rateID) {
        this.setRateID(rateID);
        return this;
    }

    public void setRateID(Long rateID) {
        this.rateID = rateID;
    }

    public Integer getTimeOfDay() {
        return this.timeOfDay;
    }

    public Rate timeOfDay(Integer timeOfDay) {
        this.setTimeOfDay(timeOfDay);
        return this;
    }

    public void setTimeOfDay(Integer timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public Integer getVehicleSize() {
        return this.vehicleSize;
    }

    public Rate vehicleSize(Integer vehicleSize) {
        this.setVehicleSize(vehicleSize);
        return this;
    }

    public void setVehicleSize(Integer vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    public Integer getBaseRate() {
        return this.baseRate;
    }

    public Rate baseRate(Integer baseRate) {
        this.setBaseRate(baseRate);
        return this;
    }

    public void setBaseRate(Integer baseRate) {
        this.baseRate = baseRate;
    }

    public Set<Reservation> getReservations() {
        return this.reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        if (this.reservations != null) {
            this.reservations.forEach(i -> i.setRate(null));
        }
        if (reservations != null) {
            reservations.forEach(i -> i.setRate(this));
        }
        this.reservations = reservations;
    }

    public Rate reservations(Set<Reservation> reservations) {
        this.setReservations(reservations);
        return this;
    }

    public Rate addReservations(Reservation reservation) {
        this.reservations.add(reservation);
        reservation.setRate(this);
        return this;
    }

    public Rate removeReservations(Reservation reservation) {
        this.reservations.remove(reservation);
        reservation.setRate(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Rate)) {
            return false;
        }
        return getId() != null && getId().equals(((Rate) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Rate{" +
            "id=" + getId() +
            ", rateID=" + getRateID() +
            ", timeOfDay=" + getTimeOfDay() +
            ", vehicleSize=" + getVehicleSize() +
            ", baseRate=" + getBaseRate() +
            "}";
    }
}
