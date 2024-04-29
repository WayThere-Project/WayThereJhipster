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
 * A Driver.
 */
@Entity
@Table(name = "driver")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Driver implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "driver_id")
    private Long driverID;

    @Column(name = "fname")
    private String fname;

    @Column(name = "lname")
    private String lname;

    @Column(name = "dob")
    private LocalDate dob;

    @JsonIgnoreProperties(value = { "reservations", "driver" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Vehicle vehicle;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "driver")
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

    public Driver id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDriverID() {
        return this.driverID;
    }

    public Driver driverID(Long driverID) {
        this.setDriverID(driverID);
        return this;
    }

    public void setDriverID(Long driverID) {
        this.driverID = driverID;
    }

    public String getFname() {
        return this.fname;
    }

    public Driver fname(String fname) {
        this.setFname(fname);
        return this;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return this.lname;
    }

    public Driver lname(String lname) {
        this.setLname(lname);
        return this;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    public Driver dob(LocalDate dob) {
        this.setDob(dob);
        return this;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Driver vehicle(Vehicle vehicle) {
        this.setVehicle(vehicle);
        return this;
    }

    public Set<Reservation> getReservations() {
        return this.reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        if (this.reservations != null) {
            this.reservations.forEach(i -> i.setDriver(null));
        }
        if (reservations != null) {
            reservations.forEach(i -> i.setDriver(this));
        }
        this.reservations = reservations;
    }

    public Driver reservations(Set<Reservation> reservations) {
        this.setReservations(reservations);
        return this;
    }

    public Driver addReservations(Reservation reservation) {
        this.reservations.add(reservation);
        reservation.setDriver(this);
        return this;
    }

    public Driver removeReservations(Reservation reservation) {
        this.reservations.remove(reservation);
        reservation.setDriver(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Driver)) {
            return false;
        }
        return getId() != null && getId().equals(((Driver) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Driver{" +
            "id=" + getId() +
            ", driverID=" + getDriverID() +
            ", fname='" + getFname() + "'" +
            ", lname='" + getLname() + "'" +
            ", dob='" + getDob() + "'" +
            "}";
    }
}
