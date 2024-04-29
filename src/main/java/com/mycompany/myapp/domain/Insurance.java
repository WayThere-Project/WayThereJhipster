package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Insurance.
 */
@Entity
@Table(name = "insurance")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Insurance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ins_id")
    private Long insID;

    @Column(name = "ins_description")
    private String insDescription;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @JsonIgnoreProperties(
        value = { "insurance", "pickupLocation", "dropoffLocation", "customer", "driver", "vehicle", "rate", "payment" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "insurance")
    private Reservation reservation;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Insurance id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInsID() {
        return this.insID;
    }

    public Insurance insID(Long insID) {
        this.setInsID(insID);
        return this;
    }

    public void setInsID(Long insID) {
        this.insID = insID;
    }

    public String getInsDescription() {
        return this.insDescription;
    }

    public Insurance insDescription(String insDescription) {
        this.setInsDescription(insDescription);
        return this;
    }

    public void setInsDescription(String insDescription) {
        this.insDescription = insDescription;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public Insurance startDate(LocalDate startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }

    public Insurance expirationDate(LocalDate expirationDate) {
        this.setExpirationDate(expirationDate);
        return this;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Reservation getReservation() {
        return this.reservation;
    }

    public void setReservation(Reservation reservation) {
        if (this.reservation != null) {
            this.reservation.setInsurance(null);
        }
        if (reservation != null) {
            reservation.setInsurance(this);
        }
        this.reservation = reservation;
    }

    public Insurance reservation(Reservation reservation) {
        this.setReservation(reservation);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Insurance)) {
            return false;
        }
        return getId() != null && getId().equals(((Insurance) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Insurance{" +
            "id=" + getId() +
            ", insID=" + getInsID() +
            ", insDescription='" + getInsDescription() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", expirationDate='" + getExpirationDate() + "'" +
            "}";
    }
}
