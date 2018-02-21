package com.lrn.manager;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

public interface IGenericManager<T, PK extends Serializable> {

	/**
	 * Generic method to get an object based on class and identifier. 
	 * 
	 * @param primaryKey the identifier (primary key) of the object to get
	 * 
	 * @return a populated object
	 */
	T get(PK primaryKey);

	/**
	 * Checks for existence of an object of type T using the id arg.
	 * 
	 * @param primaryKey the identifier (primary key) of the object to get
	 * 
	 * @return - true if it exists, false if it doesn't
	 */
	boolean exists(PK primaryKey);

	/**
	 * Generic method to save an object - handles both update and insert.
	 * 
	 * @param object the object to save
	 * 
	 * @return the updated object
	 */
	T save(T object);

	/**
	 * Generic method to delete an object based on class and id
	 * 
	 * @param primaryKey the identifier (primary key) of the object to remove
	 */
	void remove(PK primaryKey);
	
	/**
	 * Find iterator of map by query
	 * 
	 * @param String query
	 * 
	 * @return Iterator<Map<String, Object>>
	 */
	Iterator<Map<String, Object>> findBySqlQuery(String query);
}