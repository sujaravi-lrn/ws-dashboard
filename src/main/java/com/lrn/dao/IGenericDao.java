package com.lrn.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public interface IGenericDao<T, PK extends Serializable> {
	/**
	 * Generic method used to get all objects of a particular type. This is the
	 * same as lookup up all rows in a table.
	 * 
	 * @return List of populated objects
	 */
	List<T> getAll();

	/**
	 * Generic method to get an object based on class and identifier. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param id
	 *            the identifier (primary key) of the object to get
	 * @return a populated object
	 * @see org.springframework.orm.ObjectRetrievalFailureException
	 */
	T get(PK primaryKey);

	/**
	 * Checks for existence of an object of type T using the id arg.
	 * 
	 * @param id
	 *            the id of the entity
	 * @return - true if it exists, false if it doesn't
	 */
	boolean exists(PK primaryKey);

	/**
	 * Generic method to save an object - handles both update and insert.
	 * 
	 * @param object
	 *            the object to save
	 * @return the persisted object
	 */
	T save(T object);

	/**
	 * Generic method to delete an object based on class and id
	 * 
	 * @param id
	 *            the identifier (primary key) of the object to remove
	 */
	void remove(PK primaryKey);

	/**
	 * Gets all records without duplicates.
	 * <p>
	 * Note that if you use this method, it is imperative that your model
	 * classes correctly implement the hashcode/equals methods
	 * </p>
	 * 
	 * @return List of populated objects
	 */
	List<T> getAllDistinct();

	/**
	 * Find a list of records by using a named query
	 * 
	 * @param queryName
	 *            query name of the named query
	 * @param queryParams
	 *            a map of the query names and the values
	 * @return a list of the records found
	 */
	List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams);

	/**
	 * Find a list of records by using a named query
	 * 
	 * @param queryName
	 *            query name of the named query
	 * @param paramNames
	 *            array of strings for parameterised query name
	 * @param paramValues
	 *            array of Objects for parameterised query value
	 * @return a list of the records found
	 */
	List<T> findByNamedQuery(String query, String[] paramNames,
			Object[] paramValues);

	/**
	 * Find iterator of map by query
	 * 
	 * @param String
	 *            query
	 * 
	 * @return Iterator<Map<String, Object>>
	 */
	Iterator<Map<String, Object>> findBySqlQuery(String query);

	void logConnectionPool(DataSource ds);

	List<Map<String, Object>> queryForList(String query, List<Object> params);
	
	List<Map<String, Object>> queryForListByMaxRow(String query, List<Object> params, int maxRow);

	List<Map<String, Object>> queryForListByFetchSize(String query,
			List<Object> params, int fetchSize);

	List<Map<String, Object>> queryForLargeList(String query, List<Object> params, List<String> columnNames);
}
