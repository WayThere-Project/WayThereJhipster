package com.mycompany.myapp.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@StaticMetamodel(Insurance.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Insurance_ {

	
	/**
	 * @see com.mycompany.myapp.domain.Insurance#insID
	 **/
	public static volatile SingularAttribute<Insurance, Long> insID;
	
	/**
	 * @see com.mycompany.myapp.domain.Insurance#reservation
	 **/
	public static volatile SingularAttribute<Insurance, Reservation> reservation;
	
	/**
	 * @see com.mycompany.myapp.domain.Insurance#id
	 **/
	public static volatile SingularAttribute<Insurance, Long> id;
	
	/**
	 * @see com.mycompany.myapp.domain.Insurance
	 **/
	public static volatile EntityType<Insurance> class_;
	
	/**
	 * @see com.mycompany.myapp.domain.Insurance#insDescription
	 **/
	public static volatile SingularAttribute<Insurance, String> insDescription;
	
	/**
	 * @see com.mycompany.myapp.domain.Insurance#startDate
	 **/
	public static volatile SingularAttribute<Insurance, LocalDate> startDate;
	
	/**
	 * @see com.mycompany.myapp.domain.Insurance#expirationDate
	 **/
	public static volatile SingularAttribute<Insurance, LocalDate> expirationDate;

	public static final String INS_ID = "insID";
	public static final String RESERVATION = "reservation";
	public static final String ID = "id";
	public static final String INS_DESCRIPTION = "insDescription";
	public static final String START_DATE = "startDate";
	public static final String EXPIRATION_DATE = "expirationDate";

}

