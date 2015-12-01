package com.mybooks.entities;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SUB_CATEGORY")
@SequenceGenerator(name = "SUB_CATEGORY_SEQ")
@Access(AccessType.FIELD)
public class SubCategory extends AuditableEntity implements BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SUB_CATEGORY_SEQ")
	@Column(name = "SUBCATEGORY_ID", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "SUBCATEGORY_NAME", length = 35)
	private String subcategory_name;
	
	@Column(name = "SUBCATEGORY_DESCRIPTION", length = 105)
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID")
	private Category masterCategory;
	
	@ManyToMany(mappedBy = "subcategorieslist")
	private List<Products> listofProducts;
	
	public SubCategory(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubcategory_name() {
		return subcategory_name;
	}

	public void setSubcategory_name(String subcategory_name) {
		this.subcategory_name = subcategory_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getMasterCategory() {
		return masterCategory;
	}

	public void setMasterCategory(Category masterCategory) {
		this.masterCategory = masterCategory;
	}

	public List<Products> getListofProducts() {
		return listofProducts;
	}

	public void setListofProducts(List<Products> listofProducts) {
		this.listofProducts = listofProducts;
	}

}
