package com.mybooks.entities;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY")
@SequenceGenerator(name = "CATEGORY_SEQ")
@Access(AccessType.FIELD)
public class Category extends AuditableEntity implements BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CATEGORY_SEQ")
	@Column(name = "CATEGORY_ID", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "CATEGORY_NAME", length = 35, nullable = false)
	private String category_name;
	
	@Column(name = "CATEGORY_DESCRIPTION", length = 105)
	private String description;
	
	@OneToMany(mappedBy = "masterCategory")
	private List<SubCategory> subcategory;
	
	@OneToMany(mappedBy = "category")
	private List<Products> products;
	
	public Category(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SubCategory> getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(List<SubCategory> subcategory) {
		this.subcategory = subcategory;
	}

	public List<Products> getProducts() {
		return products;
	}

	public void setProducts(List<Products> products) {
		this.products = products;
	}

}
