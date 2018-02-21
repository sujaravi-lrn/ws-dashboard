package com.lrn.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang.ObjectUtils;

@MappedSuperclass
public abstract class AbstractBaseEntity implements Serializable {

	private static final long serialVersionUID = -5716704199052112645L;
	
	/**
	 * Id of the entity. This is the primary key of the entity.
	 */
	@SuppressWarnings("all")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GEN_SEQ")
	@Column(name = "ID", nullable = false)
	protected Long id;
	
	/**
	 * Indicates whether the another object is equal to the current one.
	 * 
	 * @param obj the reference object with which to compare.
	 * @return <code>true</code> if this object is the same as the <code>obj</code> argument; <code>false</code>
	 *         otherwise.
	 */
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if(!this.getClass().getName().equals(obj.getClass().getName())) {
			return false;
		}

		final AbstractBaseEntity other = (AbstractBaseEntity) obj;
		return ObjectUtils.equals(this.id, other.id);
	}

	/**
	 * Returns a hash code value for the object. This method is supported for
	 * the benefit of collections.
	 * 
	 * @return a hash code value for this object.
	 */
	public int hashCode() {
		if (null == this.id) {
			return super.hashCode();
		}

		return this.id.hashCode();
	}

	/**
	 * Get the Id of the entity.
	 * 
	 * @return the id of the entity.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set the Id of the entity.
	 * 
	 * @param id The id of the entity.
	 */
    //Exception to PMD rule
    @SuppressWarnings("all")
	public void setId(Long id) {
		this.id = id;
	}	
}