package com.mycompany.myapp.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Rate.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Rate_ {

	
	/**
	 * @see com.mycompany.myapp.domain.Rate#baseRate
	 **/
	public static volatile SingularAttribute<Rate, Integer> baseRate;
	
	/**
	 * @see com.mycompany.myapp.domain.Rate#reservations
	 **/
	public static volatile SetAttribute<Rate, Reservation> reservations;
	
	/**
	 * @see com.mycompany.myapp.domain.Rate#vehicleSize
	 **/
	public static volatile SingularAttribute<Rate, Integer> vehicleSize;
	
	/**
	 * @see com.mycompany.myapp.domain.Rate#id
	 **/
	public static volatile SingularAttribute<Rate, Long> id;
	
	/**
	 * @see com.mycompany.myapp.domain.Rate
	 **/
	public static volatile EntityType<Rate> class_;
	
	/**
	 * @see com.mycompany.myapp.domain.Rate#rateID
	 **/
	public static volatile SingularAttribute<Rate, Long> rateID;
	
	/**
	 * @see com.mycompany.myapp.domain.Rate#timeOfDay
	 **/
	public static volatile SingularAttribute<Rate, Integer> timeOfDay;

	public static final String BASE_RATE = "baseRate";
	public static final String RESERVATIONS = "reservations";
	public static final String VEHICLE_SIZE = "vehicleSize";
	public static final String ID = "id";
	public static final String RATE_ID = "rateID";
	public static final String TIME_OF_DAY = "timeOfDay";

}

