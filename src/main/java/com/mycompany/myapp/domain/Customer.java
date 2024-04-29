package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "customer_id")
    private Long customerID;

    @Column(name = "fname")
    private String fname;

    @Column(name = "lname")
    private String lname;

    @Column(name = "dob")
    private LocalDate dob;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "reservations", "customer" }, allowSetters = true)
    private Set<Payment> payments = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
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

    public Customer id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerID() {
        return this.customerID;
    }

    public Customer customerID(Long customerID) {
        this.setCustomerID(customerID);
        return this;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public String getFname() {
        return this.fname;
    }

    public Customer fname(String fname) {
        this.setFname(fname);
        return this;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return this.lname;
    }

    public Customer lname(String lname) {
        this.setLname(lname);
        return this;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    public Customer dob(LocalDate dob) {
        this.setDob(dob);
        return this;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Set<Payment> getPayments() {
        return this.payments;
    }

    public void setPayments(Set<Payment> payments) {
        if (this.payments != null) {
            this.payments.forEach(i -> i.setCustomer(null));
        }
        if (payments != null) {
            payments.forEach(i -> i.setCustomer(this));
        }
        this.payments = payments;
    }

    public Customer payments(Set<Payment> payments) {
        this.setPayments(payments);
        return this;
    }

    public Customer addPayments(Payment payment) {
        this.payments.add(payment);
        payment.setCustomer(this);
        return this;
    }

    public Customer removePayments(Payment payment) {
        this.payments.remove(payment);
        payment.setCustomer(null);
        return this;
    }

    public Set<Reservation> getReservations() {
        return this.reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        if (this.reservations != null) {
            this.reservations.forEach(i -> i.setCustomer(null));
        }
        if (reservations != null) {
            reservations.forEach(i -> i.setCustomer(this));
        }
        this.reservations = reservations;
    }

    public Customer reservations(Set<Reservation> reservations) {
        this.setReservations(reservations);
        return this;
    }

    public Customer addReservations(Reservation reservation) {
        this.reservations.add(reservation);
        reservation.setCustomer(this);
        return this;
    }

    public Customer removeReservations(Reservation reservation) {
        this.reservations.remove(reservation);
        reservation.setCustomer(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }
        return getId() != null && getId().equals(((Customer) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", customerID=" + getCustomerID() +
            ", fname='" + getFname() + "'" +
            ", lname='" + getLname() + "'" +
            ", dob='" + getDob() + "'" +
            "}";
    }
}
