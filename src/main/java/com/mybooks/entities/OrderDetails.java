package com.mybooks.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ORDER_DETAILS")
@SequenceGenerator(name = "SEQ_ORDER_DETAILS")
public class OrderDetails extends AuditableEntity implements BaseEntity, Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ORDER_DETAILS")
	@Column(name = "ORDER_ID")
	private Long orderId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ORDER_DATE")
	private Date orderDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DELIVERY_DATE")
	private Date deliveryDate;
	
	@Column(name = "ORDER_TOTAL", precision = 8, scale = 2)
	private BigDecimal orderTotal;
	
	@ElementCollection
	@CollectionTable(name = "PRODUCTS_AND_QUANTITY",
				joinColumns=@JoinColumn(name="ORDER_ID", referencedColumnName="ORDER_ID"))
	@MapKeyJoinColumn(name="PRODUCT_ID", referencedColumnName="PRODUCT_ID")
	@Column(name="PRODUCT_AND_QUANTITY")
	private Map<Products, Integer> productsAndQuantity;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "USER_ID")
	private UserMaster customerDetails;
	
	@Column(name = "ORDER_STATUS", length = 2)
	private String status;
	
	@OneToOne
	@JoinColumn(name="SHIPPING_ADDRESS")
	private Address shippingAddress;
	
	public OrderDetails(){
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public BigDecimal getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}

	public Map<Products, Integer> getProductsAndQuantity() {
		return productsAndQuantity;
	}

	public void setProductsAndQuantity(Map<Products, Integer> productsAndQuantity) {
		this.productsAndQuantity = productsAndQuantity;
	}

	public UserMaster getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(UserMaster customerDetails) {
		this.customerDetails = customerDetails;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
