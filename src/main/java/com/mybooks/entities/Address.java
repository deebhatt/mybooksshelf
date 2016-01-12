package com.mybooks.entities;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USERS_ADDRESS")
@SequenceGenerator(name = "SEQ_USER_ADDRESS")
@Access(AccessType.FIELD)
public class Address extends AuditableEntity implements BaseEntity, Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_USER_ADDRESS")
	@Column(name = "ADDRESS_ID", unique = true, nullable = false)
	private Long addressId;
	
	@Column(name = "FULL_NAME", length = 70)
	private String fullName;
	
	@Column(name = "ADDRESS_LINE1", length = 120)
	private String addressLine1;
	
	@Column(name = "ADDRESS_LINE2", length = 120)
	private String addressLine2;
	
	@Column(name = "TOWN_OR_CITY", length = 35)
	private String townOrCity;
	
	@Column(name = "STATE", length = 35)
	private String state;
	
	@Column(name = "PINCODE")
	private int pinCode;
	
	@Column(name = "COUNTRY", length = 35)
	private String country;
	
	@Column(name = "MOBILE_NUMBER", length = 15)
	private String mobileNumber;
	
	@Column(name = "LANDMARK", length = 150)
	private String landMark;
	
	@Column(name = "ADDRESS_TYPE", length = 150)
	private String addressType;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private UserMaster masterUser;
	
	public Address(){
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getTownOrCity() {
		return townOrCity;
	}

	public void setTownOrCity(String townOrCity) {
		this.townOrCity = townOrCity;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public UserMaster getMasterUser() {
		return masterUser;
	}

	public void setMasterUser(UserMaster masterUser) {
		this.masterUser = masterUser;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", fullName=" + fullName + ", addressLine1=" + addressLine1
				+ ", addressLine2=" + addressLine2 + ", townOrCity=" + townOrCity + ", state=" + state + ", pinCode="
				+ pinCode + ", country=" + country + ", mobileNumber=" + mobileNumber + ", landMark=" + landMark
				+ ", addressType=" + addressType + ", masterUser=" + masterUser + "]";
	}
}
