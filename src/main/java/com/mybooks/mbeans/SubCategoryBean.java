package com.mybooks.mbeans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SubCategoryBean {
	
	private Long id;
	
	private String subcategoryName;

	private String subcategoryLabel;
	
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getsubcategoryName() {
		return subcategoryName;
	}

	public void setsubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
	}
	
	public String getSubcategoryLabel() {
		return subcategoryLabel;
	}

	public void setSubcategoryLabel(String subcategoryLabel) {
		this.subcategoryLabel = subcategoryLabel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
