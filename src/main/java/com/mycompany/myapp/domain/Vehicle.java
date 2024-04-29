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
 * A Vehicle.
 */
@Entity
@Table(name = "vehicle")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "vehicle_id")
    private Long vehicleID;

    @Column(name = "vehicle_size")
    private Integer vehicleSize;

    @Column(name = "make")
    private String make;

    @Column(name = "last_servicing_date")
    private LocalDate lastServicingDate;

    @Column(name = "model")
    private String model;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vehicle")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "insurance", "pickupLocation", "dropoffLocation", "customer", "driver", "vehicle", "rate", "payment" },
        allowSetters = true
    )
    private Set<Reservation> reservations = new HashSet<>();

    @JsonIgnoreProperties(value = { "vehicle", "reservations" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "vehicle")
    private Driver driver;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Vehicle id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVehicleID() {
        return this.vehicleID;
    }

    public Vehicle vehicleID(Long vehicleID) {
        this.setVehicleID(vehicleID);
        return this;
    }

    public void setVehicleID(Long vehicleID) {
        this.vehicleID = vehicleID;
    }

    public Integer getVehicleSize() {
        return this.vehicleSize;
    }

    public Vehicle vehicleSize(Integer vehicleSize) {
        this.setVehicleSize(vehicleSize);
        return this;
    }

    public void setVehicleSize(Integer vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    public String getMake() {
        return this.make;
    }

    public Vehicle make(String make) {
        this.setMake(make);
        return this;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public LocalDate getLastServicingDate() {
        return this.lastServicingDate;
    }

    public Vehicle lastServicingDate(LocalDate lastServicingDate) {
        this.setLastServicingDate(lastServicingDate);
        return this;
    }

    public void setLastServicingDate(LocalDate lastServicingDate) {
        this.lastServicingDate = lastServicingDate;
    }

    public String getModel() {
        return this.model;
    }

    public Vehicle model(String model) {
        this.setModel(model);
        return this;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Set<Reservation> getReservations() {
        return this.reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        if (this.reservations != null) {
            this.reservations.forEach(i -> i.setVehicle(null));
        }
        if (reservations != null) {
            reservations.forEach(i -> i.setVehicle(this));
        }
        this.reservations = reservations;
    }

    public Vehicle reservations(Set<Reservation> reservations) {
        this.setReservations(reservations);
        return this;
    }

    public Vehicle addReservations(Reservation reservation) {
        this.reservations.add(reservation);
        reservation.setVehicle(this);
        return this;
    }

    public Vehicle removeReservations(Reservation reservation) {
        this.reservations.remove(reservation);
        reservation.setVehicle(null);
        return this;
    }

    public Driver getDriver() {
        return this.driver;
    }

    public void setDriver(Driver driver) {
        if (this.driver != null) {
            this.driver.setVehicle(null);
        }
        if (driver != null) {
            driver.setVehicle(this);
        }
        this.driver = driver;
    }

    public Vehicle driver(Driver driver) {
        this.setDriver(driver);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vehicle)) {
            return false;
        }
        return getId() != null && getId().equals(((Vehicle) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Vehicle{" +
            "id=" + getId() +
            ", vehicleID=" + getVehicleID() +
            ", vehicleSize=" + getVehicleSize() +
            ", make='" + getMake() + "'" +
            ", lastServicingDate='" + getLastServicingDate() + "'" +
            ", model='" + getModel() + "'" +
            "}";
    }
}
