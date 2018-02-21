package com.lrn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 * @author yuvraj.thakre
**/
@Entity
@SequenceGenerator(name = "GEN_SEQ", sequenceName = "USERS_SEQ")
@Table(name = "DIM_USERS")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_SEQ")
	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "LOGIN_NAME")
	private String loginName;
	
	@Column(name = "SITE_ID")
	private int siteId;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "ACTIVE")
	private String active;

	@Column(name = "ACCESS_ID")
	private String accessId;

	@Column(name = "FIRSTNAME")
	private String firstName;

	@Column(name = "MIDDLENAME")
	private String middleName;

	@Column(name = "LASTNAME")
	private String lastName;

	@Column(name = "DOB")
	private String dateOfBirth;

	@Column(name = "EMPID")
	private String empId;

	@Column(name = "SECRET")
	private String secret;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "CREATION_DATE")
	private Date creationDate;

	@Column(name = "MODDATE")
	private Date modifiedDate;

	@Column(name = "HADDRESS_1")
	private String homeAddressOne;

	@Column(name = "HADDRESS_2")
	private String homeAddressTwo;

	@Column(name = "HCITY")
	private String homeCity;

	@Column(name = "HSTATE")
	private String homeState;

	@Column(name = "HZIP")
	private String homeZip;

	@Column(name = "HCOUNTRY")
	private String homeCountry;

	@Column(name = "HPHONE")
	private String homePhone;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "COMPANY")
	private String company;

	@Column(name = "ADDRESS_1")
	private String addressOne;

	@Column(name = "ADDRESS_2")
	private String addressTwo;

	@Column(name = "CITY")
	private String city;

	@Column(name = "STATE")
	private String state;

	@Column(name = "ZIP")
	private String zip;

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "PHONE")
	private String phone;

	@Column(name = "FAX")
	private String fax;

	@Column(name = "BROWSER")
	private String browser;

	@Column(name = "DIVISION")
	private String division;

	@Column(name = "COSTCENTER")
	private String costcenter;

	@Column(name = "SUPERFIRSTNAME")
	private String superFirstName;

	@Column(name = "SUPERMIDDLENAME")
	private String superMiddleName;

	@Column(name = "SUPERLASTNAME")
	private String superLastName;

	@Column(name = "PASSWORD_MOD_DATE")
	private Date passwordModDate;

	@Column(name = "DEL_DATE")
	private Date delDate;

	@Column(name = "LC_PASSWORD")
	private String lcPassword;

	@Column(name = "LANGUAGE")
	private String language;

	@Column(name = "LC_FIRSTNAME")
	private String lcFirstName;

	@Column(name = "LC_LASTNAME")
	private String lcLastName;

	@Column(name = "LC_EMAIL")
	private String lcEmail;

	@Column(name = "DEACTIVATED_DATE")
	private Date deactivatedDate;

	@Column(name = "LOGIN_ATTEMPT")
	private int loginAttempt;

	@Column(name = "LOGIN_ATTEMPT_TIME")
	private Date loginAttemptTime;

	@Column(name = "CUSTOM_22")
	private String custom22;

	@Column(name = "CUSTOM_23")
	private String custom23;

	@Column(name = "CUSTOM_24")
	private String custom24;

	@Column(name = "CUSTOM_25")
	private String custom25;

	@Column(name = "CUSTOM_26")
	private String custom26;

	@Column(name = "CUSTOM_27")
	private String custom27;

	@Column(name = "CUSTOM_28")
	private String custom28;

	@Column(name = "CUSTOM_29")
	private String custom9;

	@Column(name = "CUSTOM_30")
	private String custom30;

	@Column(name = "CUSTOM_31")
	private String custom31;

	@Column(name = "CUSTOM_32")
	private String custom32;

	@Column(name = "CUSTOM_33")
	private String custom33;

	@Column(name = "CUSTOM_34")
	private String custom34;

	@Column(name = "CUSTOM_35")
	private String custom35;

	@Column(name = "CUSTOM_36")
	private String custom36;

	@Column(name = "CUSTOM_37")
	private String custom37;

	@Column(name = "CUSTOM_38")
	private String custom38;

	@Column(name = "CUSTOM_39")
	private String custom39;

	@Column(name = "CUSTOM_40")
	private String custom40;

	@Column(name = "CUSTOM_41")
	private String custom41;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getAccessId() {
		return accessId;
	}

	public void setAccessId(String accessId) {
		this.accessId = accessId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getHomeAddressOne() {
		return homeAddressOne;
	}

	public void setHomeAddressOne(String homeAddressOne) {
		this.homeAddressOne = homeAddressOne;
	}

	public String getHomeAddressTwo() {
		return homeAddressTwo;
	}

	public void setHomeAddressTwo(String homeAddressTwo) {
		this.homeAddressTwo = homeAddressTwo;
	}

	public String getHomeCity() {
		return homeCity;
	}

	public void setHomeCity(String homeCity) {
		this.homeCity = homeCity;
	}

	public String getHomeState() {
		return homeState;
	}

	public void setHomeState(String homeState) {
		this.homeState = homeState;
	}

	public String getHomeZip() {
		return homeZip;
	}

	public void setHomeZip(String homeZip) {
		this.homeZip = homeZip;
	}

	public String getHomeCountry() {
		return homeCountry;
	}

	public void setHomeCountry(String homeCountry) {
		this.homeCountry = homeCountry;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddressOne() {
		return addressOne;
	}

	public void setAddressOne(String addressOne) {
		this.addressOne = addressOne;
	}

	public String getAddressTwo() {
		return addressTwo;
	}

	public void setAddressTwo(String addressTwo) {
		this.addressTwo = addressTwo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getCostcenter() {
		return costcenter;
	}

	public void setCostcenter(String costcenter) {
		this.costcenter = costcenter;
	}

	public String getSuperFirstName() {
		return superFirstName;
	}

	public void setSuperFirstName(String superFirstName) {
		this.superFirstName = superFirstName;
	}

	public String getSuperMiddleName() {
		return superMiddleName;
	}

	public void setSuperMiddleName(String superMiddleName) {
		this.superMiddleName = superMiddleName;
	}

	public String getSuperLastName() {
		return superLastName;
	}

	public void setSuperLastName(String superLastName) {
		this.superLastName = superLastName;
	}

	public Date getPasswordModDate() {
		return passwordModDate;
	}

	public void setPasswordModDate(Date passwordModDate) {
		this.passwordModDate = passwordModDate;
	}

	public Date getDelDate() {
		return delDate;
	}

	public void setDelDate(Date delDate) {
		this.delDate = delDate;
	}

	public String getLcPassword() {
		return lcPassword;
	}

	public void setLcPassword(String lcPassword) {
		this.lcPassword = lcPassword;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLcFirstName() {
		return lcFirstName;
	}

	public void setLcFirstName(String lcFirstName) {
		this.lcFirstName = lcFirstName;
	}

	public String getLcLastName() {
		return lcLastName;
	}

	public void setLcLastName(String lcLastName) {
		this.lcLastName = lcLastName;
	}

	public String getLcEmail() {
		return lcEmail;
	}

	public void setLcEmail(String lcEmail) {
		this.lcEmail = lcEmail;
	}

	public Date getDeactivatedDate() {
		return deactivatedDate;
	}

	public void setDeactivatedDate(Date deactivatedDate) {
		this.deactivatedDate = deactivatedDate;
	}

	public int getLoginAttempt() {
		return loginAttempt;
	}

	public void setLoginAttempt(int loginAttempt) {
		this.loginAttempt = loginAttempt;
	}

	public Date getLoginAttemptTime() {
		return loginAttemptTime;
	}

	public void setLoginAttemptTime(Date loginAttemptTime) {
		this.loginAttemptTime = loginAttemptTime;
	}

	public String getCustom22() {
		return custom22;
	}

	public void setCustom22(String custom22) {
		this.custom22 = custom22;
	}

	public String getCustom23() {
		return custom23;
	}

	public void setCustom23(String custom23) {
		this.custom23 = custom23;
	}

	public String getCustom24() {
		return custom24;
	}

	public void setCustom24(String custom24) {
		this.custom24 = custom24;
	}

	public String getCustom25() {
		return custom25;
	}

	public void setCustom25(String custom25) {
		this.custom25 = custom25;
	}

	public String getCustom26() {
		return custom26;
	}

	public void setCustom26(String custom26) {
		this.custom26 = custom26;
	}

	public String getCustom27() {
		return custom27;
	}

	public void setCustom27(String custom27) {
		this.custom27 = custom27;
	}

	public String getCustom28() {
		return custom28;
	}

	public void setCustom28(String custom28) {
		this.custom28 = custom28;
	}

	public String getCustom9() {
		return custom9;
	}

	public void setCustom9(String custom9) {
		this.custom9 = custom9;
	}

	public String getCustom30() {
		return custom30;
	}

	public void setCustom30(String custom30) {
		this.custom30 = custom30;
	}

	public String getCustom31() {
		return custom31;
	}

	public void setCustom31(String custom31) {
		this.custom31 = custom31;
	}

	public String getCustom32() {
		return custom32;
	}

	public void setCustom32(String custom32) {
		this.custom32 = custom32;
	}

	public String getCustom33() {
		return custom33;
	}

	public void setCustom33(String custom33) {
		this.custom33 = custom33;
	}

	public String getCustom34() {
		return custom34;
	}

	public void setCustom34(String custom34) {
		this.custom34 = custom34;
	}

	public String getCustom35() {
		return custom35;
	}

	public void setCustom35(String custom35) {
		this.custom35 = custom35;
	}

	public String getCustom36() {
		return custom36;
	}

	public void setCustom36(String custom36) {
		this.custom36 = custom36;
	}

	public String getCustom37() {
		return custom37;
	}

	public void setCustom37(String custom37) {
		this.custom37 = custom37;
	}

	public String getCustom38() {
		return custom38;
	}

	public void setCustom38(String custom38) {
		this.custom38 = custom38;
	}

	public String getCustom39() {
		return custom39;
	}

	public void setCustom39(String custom39) {
		this.custom39 = custom39;
	}

	public String getCustom40() {
		return custom40;
	}

	public void setCustom40(String custom40) {
		this.custom40 = custom40;
	}

	public String getCustom41() {
		return custom41;
	}

	public void setCustom41(String custom41) {
		this.custom41 = custom41;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

}
