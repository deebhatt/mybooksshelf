package com.mybooks.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USER_MASTER")
@SequenceGenerator(name = "SEQ_USER_MASTER")
public class UserMaster extends AuditableEntity implements BaseEntity, Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_USER_MASTER")
	@Column(name = "USER_ID", unique = true, nullable = false)
	private Long userId;

	@Column(length = 2)
	private String active;

	@Column(length = 35)
	private String email;

	@Column(name = "FIRST_NAME", length = 35)
	private String firstName;

	@Column(name = "LAST_NAME", length = 35)
	private String lastName;

	@Column(length = 60)
	private String password;

	@Column(length = 2)
	private String verified;
	
	@Column(name = "MOBILE_NUMBER", length = 15)
	private String mobileNumber;
	
	@ManyToMany
	@JoinTable(name = "USERS_AND_ROLES",
			joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID"),
			inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID"))
	private List<Roles> listOfRoles;
	
	@OneToMany(mappedBy = "masterUser")
	private List<Address> addresses;
	
	@OneToMany(mappedBy = "customerDetails")
	private List<OrderDetails> orders;
	
	public UserMaster() {
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerified() {
		return verified;
	}

	public void setVerified(String verified) {
		this.verified = verified;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public List<Roles> getListOfRoles() {
		return listOfRoles;
	}

	public void setListOfRoles(List<Roles> listOfRoles) {
		this.listOfRoles = listOfRoles;
	}

	@Override
	public String toString() {
		return "UserMaster [userId=" + userId + ", active=" + active + ", email=" + email + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", password=" + password + ", verified=" + verified + ", mobileNumber="
				+ mobileNumber + "]";
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<OrderDetails> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDetails> orders) {
		this.orders = orders;
	}
}
