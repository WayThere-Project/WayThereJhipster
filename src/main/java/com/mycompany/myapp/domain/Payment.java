package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Payment.
 */
@Entity
@Table(name = "payment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "payment_id")
    private Long paymentID;

    @Column(name = "payment_mode")
    private Integer paymentMode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "payment")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "insurance", "pickupLocation", "dropoffLocation", "customer", "driver", "vehicle", "rate", "payment" },
        allowSetters = true
    )
    private Set<Reservation> reservations = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "payments", "reservations" }, allowSetters = true)
    private Customer customer;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Payment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPaymentID() {
        return this.paymentID;
    }

    public Payment paymentID(Long paymentID) {
        this.setPaymentID(paymentID);
        return this;
    }

    public void setPaymentID(Long paymentID) {
        this.paymentID = paymentID;
    }

    public Integer getPaymentMode() {
        return this.paymentMode;
    }

    public Payment paymentMode(Integer paymentMode) {
        this.setPaymentMode(paymentMode);
        return this;
    }

    public void setPaymentMode(Integer paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Set<Reservation> getReservations() {
        return this.reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        if (this.reservations != null) {
            this.reservations.forEach(i -> i.setPayment(null));
        }
        if (reservations != null) {
            reservations.forEach(i -> i.setPayment(this));
        }
        this.reservations = reservations;
    }

    public Payment reservations(Set<Reservation> reservations) {
        this.setReservations(reservations);
        return this;
    }

    public Payment addReservation(Reservation reservation) {
        this.reservations.add(reservation);
        reservation.setPayment(this);
        return this;
    }

    public Payment removeReservation(Reservation reservation) {
        this.reservations.remove(reservation);
        reservation.setPayment(null);
        return this;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Payment customer(Customer customer) {
        this.setCustomer(customer);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Payment)) {
            return false;
        }
        return getId() != null && getId().equals(((Payment) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Payment{" +
            "id=" + getId() +
            ", paymentID=" + getPaymentID() +
            ", paymentMode=" + getPaymentMode() +
            "}";
    }
}
