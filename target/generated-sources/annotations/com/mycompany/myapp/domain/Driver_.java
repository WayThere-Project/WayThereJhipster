package com.mycompany.myapp.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@StaticMetamodel(Driver.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Driver_ {

	
	/**
	 * @see com.mycompany.myapp.domain.Driver#fname
	 **/
	public static volatile SingularAttribute<Driver, String> fname;
	
	/**
	 * @see com.mycompany.myapp.domain.Driver#lname
	 **/
	public static volatile SingularAttribute<Driver, String> lname;
	
	/**
	 * @see com.mycompany.myapp.domain.Driver#driverID
	 **/
	public static volatile SingularAttribute<Driver, Long> driverID;
	
	/**
	 * @see com.mycompany.myapp.domain.Driver#reservations
	 **/
	public static volatile SetAttribute<Driver, Reservation> reservations;
	
	/**
	 * @see com.mycompany.myapp.domain.Driver#dob
	 **/
	public static volatile SingularAttribute<Driver, LocalDate> dob;
	
	/**
	 * @see com.mycompany.myapp.domain.Driver#id
	 **/
	public static volatile SingularAttribute<Driver, Long> id;
	
	/**
	 * @see com.mycompany.myapp.domain.Driver
	 **/
	public static volatile EntityType<Driver> class_;
	
	/**
	 * @see com.mycompany.myapp.domain.Driver#vehicle
	 **/
	public static volatile SingularAttribute<Driver, Vehicle> vehicle;

	public static final String FNAME = "fname";
	public static final String LNAME = "lname";
	public static final String DRIVER_ID = "driverID";
	public static final String RESERVATIONS = "reservations";
	public static final String DOB = "dob";
	public static final String ID = "id";
	public static final String VEHICLE = "vehicle";

}

