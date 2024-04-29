package com.mycompany.myapp.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@StaticMetamodel(Reservation.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Reservation_ {

	
	/**
	 * @see com.mycompany.myapp.domain.Reservation#date
	 **/
	public static volatile SingularAttribute<Reservation, LocalDate> date;
	
	/**
	 * @see com.mycompany.myapp.domain.Reservation#insurance
	 **/
	public static volatile SingularAttribute<Reservation, Insurance> insurance;
	
	/**
	 * @see com.mycompany.myapp.domain.Reservation#dropoffLocation
	 **/
	public static volatile SingularAttribute<Reservation, Location> dropoffLocation;
	
	/**
	 * @see com.mycompany.myapp.domain.Reservation#pickupLocation
	 **/
	public static volatile SingularAttribute<Reservation, Location> pickupLocation;
	
	/**
	 * @see com.mycompany.myapp.domain.Reservation#vehicle
	 **/
	public static volatile SingularAttribute<Reservation, Vehicle> vehicle;
	
	/**
	 * @see com.mycompany.myapp.domain.Reservation#customerRating
	 **/
	public static volatile SingularAttribute<Reservation, Float> customerRating;
	
	/**
	 * @see com.mycompany.myapp.domain.Reservation#reservationID
	 **/
	public static volatile SingularAttribute<Reservation, Long> reservationID;
	
	/**
	 * @see com.mycompany.myapp.domain.Reservation#driver
	 **/
	public static volatile SingularAttribute<Reservation, Driver> driver;
	
	/**
	 * @see com.mycompany.myapp.domain.Reservation#requestedTime
	 **/
	public static volatile SingularAttribute<Reservation, Float> requestedTime;
	
	/**
	 * @see com.mycompany.myapp.domain.Reservation#rate
	 **/
	public static volatile SingularAttribute<Reservation, Rate> rate;
	
	/**
	 * @see com.mycompany.myapp.domain.Reservation#tripCost
	 **/
	public static volatile SingularAttribute<Reservation, Float> tripCost;
	
	/**
	 * @see com.mycompany.myapp.domain.Reservation#startTime
	 **/
	public static volatile SingularAttribute<Reservation, Float> startTime;
	
	/**
	 * @see com.mycompany.myapp.domain.Reservation#comment
	 **/
	public static volatile SingularAttribute<Reservation, String> comment;
	
	/**
	 * @see com.mycompany.myapp.domain.Reservation#payment
	 **/
	public static volatile SingularAttribute<Reservation, Payment> payment;
	
	/**
	 * @see com.mycompany.myapp.domain.Reservation#id
	 **/
	public static volatile SingularAttribute<Reservation, Long> id;
	
	/**
	 * @see com.mycompany.myapp.domain.Reservation#endTime
	 **/
	public static volatile SingularAttribute<Reservation, Float> endTime;
	
	/**
	 * @see com.mycompany.myapp.domain.Reservation#driverRating
	 **/
	public static volatile SingularAttribute<Reservation, Float> driverRating;
	
	/**
	 * @see com.mycompany.myapp.domain.Reservation
	 **/
	public static volatile EntityType<Reservation> class_;
	
	/**
	 * @see com.mycompany.myapp.domain.Reservation#waitTime
	 **/
	public static volatile SingularAttribute<Reservation, Float> waitTime;
	
	/**
	 * @see com.mycompany.myapp.domain.Reservation#customer
	 **/
	public static volatile SingularAttribute<Reservation, Customer> customer;

	public static final String DATE = "date";
	public static final String INSURANCE = "insurance";
	public static final String DROPOFF_LOCATION = "dropoffLocation";
	public static final String PICKUP_LOCATION = "pickupLocation";
	public static final String VEHICLE = "vehicle";
	public static final String CUSTOMER_RATING = "customerRating";
	public static final String RESERVATION_ID = "reservationID";
	public static final String DRIVER = "driver";
	public static final String REQUESTED_TIME = "requestedTime";
	public static final String RATE = "rate";
	public static final String TRIP_COST = "tripCost";
	public static final String START_TIME = "startTime";
	public static final String COMMENT = "comment";
	public static final String PAYMENT = "payment";
	public static final String ID = "id";
	public static final String END_TIME = "endTime";
	public static final String DRIVER_RATING = "driverRating";
	public static final String WAIT_TIME = "waitTime";
	public static final String CUSTOMER = "customer";

}

