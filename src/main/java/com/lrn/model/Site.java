package com.lrn.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import com.lrn.model.AbstractBaseEntity;


@NamedQueries( {
	@NamedQuery(name = "findByName",
			query = "from Site s where s.name = :siteName")
})
@Entity
@SequenceGenerator(
		name 			= "GEN_SEQ",
		sequenceName 	= "SITE_SEQ"
)
@Table(name="SITE")
public class Site extends AbstractBaseEntity {
	
    private static final long serialVersionUID = 6366423552206963996L;

    @Column(name="NAME")
    private String name;
    
    @Column(name="HOST")
    private String host;
    
    @Column(name="ACTIVE")
    private Boolean active;
    
    @Column(name="PURPOSE")
    private String purpose;
    
    @Column(name="DEACTIVATED")
    private Date deactivatedDate;
    
    @Column(name="UPDATED")
    private Date updatedDate;
    
    @OneToMany(targetEntity=User.class, fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST })
	@JoinTable(name="SITE_USER",
	    joinColumns = @JoinColumn(name="SITE_ID"),
	    inverseJoinColumns = @JoinColumn(name="USER_ID")
	)
	private Set<User> users;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Date getDeactivatedDate() {
		return deactivatedDate;
	}

	public void setDeactivatedDate(Date deactivatedDate) {
		this.deactivatedDate = deactivatedDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	//@IgnoreProperty
	public Set<User> getUsers() {
		return this.users;
	}
	
	public void setUsers(Set<User> users) {
		this.users = users;
	}
}