package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Reservation.
 */
@Entity
@Table(name = "reservation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reservation_id")
    private Long reservationID;

    @Column(name = "requested_time")
    private Float requestedTime;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "start_time")
    private Float startTime;

    @Column(name = "end_time")
    private Float endTime;

    @Column(name = "wait_time")
    private Float waitTime;

    @Column(name = "driver_rating")
    private Float driverRating;

    @Column(name = "customer_rating")
    private Float customerRating;

    @Column(name = "comment")
    private String comment;

    @Column(name = "trip_cost")
    private Float tripCost;

    @JsonIgnoreProperties(value = { "reservation" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Insurance insurance;

    @ManyToOne(fetch = FetchType.LAZY)
    private Location pickupLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    private Location dropoffLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "payments", "reservations" }, allowSetters = true)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "vehicle", "reservations" }, allowSetters = true)
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "reservations", "driver" }, allowSetters = true)
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "reservations" }, allowSetters = true)
    private Rate rate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "reservations", "customer" }, allowSetters = true)
    private Payment payment;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Reservation id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReservationID() {
        return this.reservationID;
    }

    public Reservation reservationID(Long reservationID) {
        this.setReservationID(reservationID);
        return this;
    }

    public void setReservationID(Long reservationID) {
        this.reservationID = reservationID;
    }

    public Float getRequestedTime() {
        return this.requestedTime;
    }

    public Reservation requestedTime(Float requestedTime) {
        this.setRequestedTime(requestedTime);
        return this;
    }

    public void setRequestedTime(Float requestedTime) {
        this.requestedTime = requestedTime;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public Reservation date(LocalDate date) {
        this.setDate(date);
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Float getStartTime() {
        return this.startTime;
    }

    public Reservation startTime(Float startTime) {
        this.setStartTime(startTime);
        return this;
    }

    public void setStartTime(Float startTime) {
        this.startTime = startTime;
    }

    public Float getEndTime() {
        return this.endTime;
    }

    public Reservation endTime(Float endTime) {
        this.setEndTime(endTime);
        return this;
    }

    public void setEndTime(Float endTime) {
        this.endTime = endTime;
    }

    public Float getWaitTime() {
        return this.waitTime;
    }

    public Reservation waitTime(Float waitTime) {
        this.setWaitTime(waitTime);
        return this;
    }

    public void setWaitTime(Float waitTime) {
        this.waitTime = waitTime;
    }

    public Float getDriverRating() {
        return this.driverRating;
    }

    public Reservation driverRating(Float driverRating) {
        this.setDriverRating(driverRating);
        return this;
    }

    public void setDriverRating(Float driverRating) {
        this.driverRating = driverRating;
    }

    public Float getCustomerRating() {
        return this.customerRating;
    }

    public Reservation customerRating(Float customerRating) {
        this.setCustomerRating(customerRating);
        return this;
    }

    public void setCustomerRating(Float customerRating) {
        this.customerRating = customerRating;
    }

    public String getComment() {
        return this.comment;
    }

    public Reservation comment(String comment) {
        this.setComment(comment);
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Float getTripCost() {
        return this.tripCost;
    }

    public Reservation tripCost(Float tripCost) {
        this.setTripCost(tripCost);
        return this;
    }

    public void setTripCost(Float tripCost) {
        this.tripCost = tripCost;
    }

    public Insurance getInsurance() {
        return this.insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public Reservation insurance(Insurance insurance) {
        this.setInsurance(insurance);
        return this;
    }

    public Location getPickupLocation() {
        return this.pickupLocation;
    }

    public void setPickupLocation(Location location) {
        this.pickupLocation = location;
    }

    public Reservation pickupLocation(Location location) {
        this.setPickupLocation(location);
        return this;
    }

    public Location getDropoffLocation() {
        return this.dropoffLocation;
    }

    public void setDropoffLocation(Location location) {
        this.dropoffLocation = location;
    }

    public Reservation dropoffLocation(Location location) {
        this.setDropoffLocation(location);
        return this;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Reservation customer(Customer customer) {
        this.setCustomer(customer);
        return this;
    }

    public Driver getDriver() {
        return this.driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Reservation driver(Driver driver) {
        this.setDriver(driver);
        return this;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Reservation vehicle(Vehicle vehicle) {
        this.setVehicle(vehicle);
        return this;
    }

    public Rate getRate() {
        return this.rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public Reservation rate(Rate rate) {
        this.setRate(rate);
        return this;
    }

    public Payment getPayment() {
        return this.payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Reservation payment(Payment payment) {
        this.setPayment(payment);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Reservation)) {
            return false;
        }
        return getId() != null && getId().equals(((Reservation) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Reservation{" +
            "id=" + getId() +
            ", reservationID=" + getReservationID() +
            ", requestedTime=" + getRequestedTime() +
            ", date='" + getDate() + "'" +
            ", startTime=" + getStartTime() +
            ", endTime=" + getEndTime() +
            ", waitTime=" + getWaitTime() +
            ", driverRating=" + getDriverRating() +
            ", customerRating=" + getCustomerRating() +
            ", comment='" + getComment() + "'" +
            ", tripCost=" + getTripCost() +
            "}";
    }
}
