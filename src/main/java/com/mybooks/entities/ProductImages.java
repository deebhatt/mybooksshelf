package com.mybooks.entities;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_IMAGES")
@SequenceGenerator(name = "PRODUCT_IMAGE_SEQ")
@Access(AccessType.FIELD)
public class ProductImages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PRODUCT_IMAGE_SEQ")
	@Column(name = "IMAGE_ID", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "PRODUCT_IMAGE_URL")
	private String imageUrl;
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private Products productType;
	
	public ProductImages(){
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Products getProductType() {
		return productType;
	}

	public void setProductType(Products productType) {
		this.productType = productType;
	}

	@Override
	public String toString() {
		return "ProductImages [id=" + id + ", imageUrl=" + imageUrl + ", productType=" + productType + "]";
	}

}
