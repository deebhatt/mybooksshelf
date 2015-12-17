package com.mybooks.mbeans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CategoryBean {
	
	private Long id;
	
	private String categoryName;
	
	private String categoryLabel;
	
	private String description;
	
	private List<SubCategoryBean> listofsubcategories;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getcategoryName() {
		return categoryName;
	}

	public String getCategoryLabel() {
		return categoryLabel;
	}

	public void setcategoryLabel(String categoryLabel) {
		this.categoryLabel = categoryLabel;
	}

	public void setcategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SubCategoryBean> getListofsubcategories() {
		return listofsubcategories;
	}

	public void setListofsubcategories(List<SubCategoryBean> listofsubcategories) {
		this.listofsubcategories = listofsubcategories;
	}
}