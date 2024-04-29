package com.mycompany.myapp.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@StaticMetamodel(Vehicle.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Vehicle_ {

	
	/**
	 * @see com.mycompany.myapp.domain.Vehicle#reservations
	 **/
	public static volatile SetAttribute<Vehicle, Reservation> reservations;
	
	/**
	 * @see com.mycompany.myapp.domain.Vehicle#driver
	 **/
	public static volatile SingularAttribute<Vehicle, Driver> driver;
	
	/**
	 * @see com.mycompany.myapp.domain.Vehicle#vehicleSize
	 **/
	public static volatile SingularAttribute<Vehicle, Integer> vehicleSize;
	
	/**
	 * @see com.mycompany.myapp.domain.Vehicle#model
	 **/
	public static volatile SingularAttribute<Vehicle, String> model;
	
	/**
	 * @see com.mycompany.myapp.domain.Vehicle#id
	 **/
	public static volatile SingularAttribute<Vehicle, Long> id;
	
	/**
	 * @see com.mycompany.myapp.domain.Vehicle#vehicleID
	 **/
	public static volatile SingularAttribute<Vehicle, Long> vehicleID;
	
	/**
	 * @see com.mycompany.myapp.domain.Vehicle
	 **/
	public static volatile EntityType<Vehicle> class_;
	
	/**
	 * @see com.mycompany.myapp.domain.Vehicle#make
	 **/
	public static volatile SingularAttribute<Vehicle, String> make;
	
	/**
	 * @see com.mycompany.myapp.domain.Vehicle#lastServicingDate
	 **/
	public static volatile SingularAttribute<Vehicle, LocalDate> lastServicingDate;

	public static final String RESERVATIONS = "reservations";
	public static final String DRIVER = "driver";
	public static final String VEHICLE_SIZE = "vehicleSize";
	public static final String MODEL = "model";
	public static final String ID = "id";
	public static final String VEHICLE_ID = "vehicleID";
	public static final String MAKE = "make";
	public static final String LAST_SERVICING_DATE = "lastServicingDate";

}

