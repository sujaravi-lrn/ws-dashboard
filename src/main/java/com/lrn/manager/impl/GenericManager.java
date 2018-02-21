package com.lrn.manager.impl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.lrn.dao.IGenericDao;
import com.lrn.manager.IGenericManager;

@Transactional(readOnly = false)
public class GenericManager<T, PK extends Serializable> implements IGenericManager<T, PK> {
	
	private static final Logger logger = Logger.getLogger(GenericManager.class);
	
	/**
	 * Log variable for all child classes. Uses LogFactory.getLog(getClass())
	 * from Commons Logging
	 */
	protected final Log		LOG	= LogFactory.getLog(getClass());

	/**
     * GenericDao instance, set by constructor of child classes
     */
    protected IGenericDao<T, PK> iGenericDao;
    
	public GenericManager(IGenericDao<T, PK> iGenericDao) {
        this.iGenericDao = iGenericDao;
    }

	@Override
	public T get(PK primaryKey) {
		return iGenericDao.get(primaryKey);
	}

	@Override
	public boolean exists(PK primaryKey) {
		return iGenericDao.exists(primaryKey);
	}

	@Override
	public T save(T object) {
		return iGenericDao.save(object);
    }

	@Override
	public void remove(PK primaryKey) {
		iGenericDao.remove(primaryKey);
	}
	
	@Override
	public Iterator<Map<String, Object>> findBySqlQuery(String query) {
		return iGenericDao.findBySqlQuery(query);
	}
}