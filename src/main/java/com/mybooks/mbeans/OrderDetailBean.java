package com.mybooks.mbeans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.mybooks.entities.Address;

public class OrderDetailBean {
	
	private Long orderId;
	
	private Date orderDate;
	
	private Date deliveryDate;
	
	private BigDecimal orderTotal;
	
	private Map<ProductBean, Integer> productsAndQuantity;
	
	private UserFormBean user;
	
	private String orderStatus;
	
	private AddressBean shippingAddress;

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

	public Map<ProductBean, Integer> getProductsAndQuantity() {
		return productsAndQuantity;
	}

	public void setProductsAndQuantity(Map<ProductBean, Integer> productsAndQuantity) {
		this.productsAndQuantity = productsAndQuantity;
	}

	public UserFormBean getUser() {
		return user;
	}

	public void setUser(UserFormBean user) {
		this.user = user;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public AddressBean getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(AddressBean shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

}
