package com.mycompany.myapp.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@StaticMetamodel(Customer.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Customer_ {

	
	/**
	 * @see com.mycompany.myapp.domain.Customer#fname
	 **/
	public static volatile SingularAttribute<Customer, String> fname;
	
	/**
	 * @see com.mycompany.myapp.domain.Customer#lname
	 **/
	public static volatile SingularAttribute<Customer, String> lname;
	
	/**
	 * @see com.mycompany.myapp.domain.Customer#reservations
	 **/
	public static volatile SetAttribute<Customer, Reservation> reservations;
	
	/**
	 * @see com.mycompany.myapp.domain.Customer#dob
	 **/
	public static volatile SingularAttribute<Customer, LocalDate> dob;
	
	/**
	 * @see com.mycompany.myapp.domain.Customer#payments
	 **/
	public static volatile SetAttribute<Customer, Payment> payments;
	
	/**
	 * @see com.mycompany.myapp.domain.Customer#customerID
	 **/
	public static volatile SingularAttribute<Customer, Long> customerID;
	
	/**
	 * @see com.mycompany.myapp.domain.Customer#id
	 **/
	public static volatile SingularAttribute<Customer, Long> id;
	
	/**
	 * @see com.mycompany.myapp.domain.Customer
	 **/
	public static volatile EntityType<Customer> class_;

	public static final String FNAME = "fname";
	public static final String LNAME = "lname";
	public static final String RESERVATIONS = "reservations";
	public static final String DOB = "dob";
	public static final String PAYMENTS = "payments";
	public static final String CUSTOMER_ID = "customerID";
	public static final String ID = "id";

}

