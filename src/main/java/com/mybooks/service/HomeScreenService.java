package com.mybooks.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mybooks.dao.HomeScreenDAO;
import com.mybooks.entities.Category;
import com.mybooks.entities.Products;
import com.mybooks.entities.SubCategory;
import com.mybooks.mbeans.CategoryBean;
import com.mybooks.mbeans.ProductBean;
import com.mybooks.mbeans.SubCategoryBean;

@Service("homescreenservice")
@Repository
public class HomeScreenService {
	
	@Inject
	private HomeScreenDAO screendao;
	
	public List<CategoryBean> findcategories()
	{
		List<CategoryBean> categorylist = new ArrayList<CategoryBean>();
		List<Category> mastercategory = screendao.listallcategories();
		for(Category category: mastercategory)
		{
			CategoryBean categorybean = new CategoryBean();
			categorybean.setId(category.getId());
			categorybean.setcategoryName(category.getcategoryName());
			categorybean.setcategoryLabel(category.getCategoryLabel());
			
			List<SubCategory> mastersubcategory = category.getSubcategory();
			List<SubCategoryBean> subcategorylist = new ArrayList<SubCategoryBean>();
			for(SubCategory subcategory: mastersubcategory)
			{
				SubCategoryBean subcategorybean = new SubCategoryBean();
				subcategorybean.setId(subcategory.getId());
				subcategorybean.setsubcategoryName(subcategory.getsubcategoryName());
				subcategorybean.setSubcategoryLabel(subcategory.getSubcategoryLabel());
				subcategorylist.add(subcategorybean);
			}
			categorybean.setListofsubcategories(subcategorylist);
			categorylist.add(categorybean);
		}
		return categorylist;
		
	}
	
	public List<ProductBean> listAllProducts()
	{
		List<ProductBean> productslist = new ArrayList<ProductBean>();
		List<Products> listall = screendao.getAllProducts();
		for(Products products : listall)
		{
			ProductBean productbean = new ProductBean();
			productbean.setProductName(products.getProductName());
			productbean.setProductLabel(products.getProductLabel());
			productbean.setPrice(products.getPrice());
			productbean.setImages(products.getImages());
			productbean.setProductCode(products.getProductCode());
			CategoryBean categorybean = new CategoryBean();
			categorybean.setId(products.getCategory().getId());
			categorybean.setcategoryName(products.getCategory().getcategoryName());
			productbean.setCategory(categorybean);
			productslist.add(productbean);
		}
		return productslist;
	}

}