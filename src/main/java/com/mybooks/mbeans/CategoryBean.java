package com.mybooks.mbeans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CategoryBean {
	
	private Long id;
	
	private String category_name;
	
	private String description;
	
	private List<SubCategoryBean> listofsubcategories;

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

	public List<SubCategoryBean> getListofsubcategories() {
		return listofsubcategories;
	}

	public void setListofsubcategories(List<SubCategoryBean> listofsubcategories) {
		this.listofsubcategories = listofsubcategories;
	}
}