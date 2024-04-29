package com.mycompany.myapp.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Location.class)
@Generated("org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
public abstract class Location_ {

	
	/**
	 * @see com.mycompany.myapp.domain.Location#country
	 **/
	public static volatile SingularAttribute<Location, String> country;
	
	/**
	 * @see com.mycompany.myapp.domain.Location#zipCode
	 **/
	public static volatile SingularAttribute<Location, Integer> zipCode;
	
	/**
	 * @see com.mycompany.myapp.domain.Location#landmarkName
	 **/
	public static volatile SingularAttribute<Location, String> landmarkName;
	
	/**
	 * @see com.mycompany.myapp.domain.Location#city
	 **/
	public static volatile SingularAttribute<Location, String> city;
	
	/**
	 * @see com.mycompany.myapp.domain.Location#locationID
	 **/
	public static volatile SingularAttribute<Location, Long> locationID;
	
	/**
	 * @see com.mycompany.myapp.domain.Location#addressLine1
	 **/
	public static volatile SingularAttribute<Location, String> addressLine1;
	
	/**
	 * @see com.mycompany.myapp.domain.Location#addressLine2
	 **/
	public static volatile SingularAttribute<Location, String> addressLine2;
	
	/**
	 * @see com.mycompany.myapp.domain.Location#id
	 **/
	public static volatile SingularAttribute<Location, Long> id;
	
	/**
	 * @see com.mycompany.myapp.domain.Location#state
	 **/
	public static volatile SingularAttribute<Location, String> state;
	
	/**
	 * @see com.mycompany.myapp.domain.Location
	 **/
	public static volatile EntityType<Location> class_;

	public static final String COUNTRY = "country";
	public static final String ZIP_CODE = "zipCode";
	public static final String LANDMARK_NAME = "landmarkName";
	public static final String CITY = "city";
	public static final String LOCATION_ID = "locationID";
	public static final String ADDRESS_LINE1 = "addressLine1";
	public static final String ADDRESS_LINE2 = "addressLine2";
	public static final String ID = "id";
	public static final String STATE = "state";

}

