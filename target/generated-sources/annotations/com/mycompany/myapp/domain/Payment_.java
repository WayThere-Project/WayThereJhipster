package com.mycompany.myapp.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Payment.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Payment_ {

	
	/**
	 * @see com.mycompany.myapp.domain.Payment#reservations
	 **/
	public static volatile SetAttribute<Payment, Reservation> reservations;
	
	/**
	 * @see com.mycompany.myapp.domain.Payment#paymentMode
	 **/
	public static volatile SingularAttribute<Payment, Integer> paymentMode;
	
	/**
	 * @see com.mycompany.myapp.domain.Payment#paymentID
	 **/
	public static volatile SingularAttribute<Payment, Long> paymentID;
	
	/**
	 * @see com.mycompany.myapp.domain.Payment#id
	 **/
	public static volatile SingularAttribute<Payment, Long> id;
	
	/**
	 * @see com.mycompany.myapp.domain.Payment
	 **/
	public static volatile EntityType<Payment> class_;
	
	/**
	 * @see com.mycompany.myapp.domain.Payment#customer
	 **/
	public static volatile SingularAttribute<Payment, Customer> customer;

	public static final String RESERVATIONS = "reservations";
	public static final String PAYMENT_MODE = "paymentMode";
	public static final String PAYMENT_ID = "paymentID";
	public static final String ID = "id";
	public static final String CUSTOMER = "customer";

}

