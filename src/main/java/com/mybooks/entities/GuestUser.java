package com.mybooks.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "GUEST_USER")
@SequenceGenerator(name = "SEQ_GUEST_USER")
public class GuestUser implements Serializable, BaseEntity{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_GUEST_USER")
	@Column(name = "GUESTUSER_ID", unique = true, nullable = false)
	private Long id;
	
	@Column(name="GUSETUSER_EMAIL", length=35, unique=true)
	private String email;
	
	@Column(name="GUSETUSER_MOBILE", length=10, unique=true)
	private String mobileNumber;
	
	@Column(name="TOKENCREATED_DATE")
	private Long tokenCreatedDate;
	
	@Column(name="SAVED_TOKEN")
	private Long token;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Long getTokenCreatedDate() {
		return tokenCreatedDate;
	}

	public void setTokenCreatedDate(Long tokenCreatedDate) {
		this.tokenCreatedDate = tokenCreatedDate;
	}

	public Long getToken() {
		return token;
	}

	public void setToken(Long token) {
		this.token = token;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
