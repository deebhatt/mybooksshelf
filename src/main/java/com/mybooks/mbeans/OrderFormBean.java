package com.mybooks.mbeans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class OrderFormBean {
	
	private AddressBean address;
	
	public AddressBean getAddress() {
		return address;
	}

	public void setAddress(AddressBean address) {
		this.address = address;
	}

	private List<ProductOrder> orderProducts;

	public List<ProductOrder> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<ProductOrder> orderProducts) {
		this.orderProducts = orderProducts;
	}

}
