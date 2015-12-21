package com.mybooks.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mybooks.dao.HomeScreenDAO;
import com.mybooks.entities.Category;
import com.mybooks.entities.ProductImages;
import com.mybooks.entities.Products;
import com.mybooks.entities.SubCategory;
import com.mybooks.exception.DBRecordNotFoundException;
import com.mybooks.mbeans.CategoryBean;
import com.mybooks.mbeans.ProductBean;
import com.mybooks.mbeans.ProductImageBean;
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
			productbean.setOriginalPrice(products.getOriginalPrice());
			productbean.setUsedBookSellingPrice(products.getUsedBookSellingPrice());
			productbean.setNewBookSellingPrice(products.getNewBookSellingPrice());
			productbean.setUsedBookDiscount(products.getUsedBookDiscount());
			productbean.setNewBookDiscount(products.getNewBookDiscount());
			
			List<ProductImages> productimages = products.getListImages();
			List<ProductImageBean> imagelist = new ArrayList<ProductImageBean>();
			for(ProductImages productimage: productimages)
			{
				ProductImageBean image = new ProductImageBean();
				image.setId(productimage.getId());
				image.setImageUrl(productimage.getImageUrl());
				imagelist.add(image);
			}
			productbean.setProductImages(imagelist);
			productbean.setProductCode(products.getProductCode());
			CategoryBean categorybean = new CategoryBean();
			categorybean.setId(products.getCategory().getId());
			categorybean.setcategoryName(products.getCategory().getcategoryName());
			productbean.setCategory(categorybean);
			productslist.add(productbean);
		}
		return productslist;
	}
	
	public ProductBean getProductbyName(String productName)
	{
		ProductBean product = null;
		try{
			Products getproduct = screendao.getProductbyName(productName);
			product = new ProductBean();
			product.setAuthor(getproduct.getAuthor());
			product.setDescription(getproduct.getDescription());
			product.setEdition(getproduct.getEdition());
			product.setProductCode(getproduct.getProductCode());
			product.setProductLabel(getproduct.getProductLabel());
			product.setProductName(getproduct.getProductName());
			product.setPublisher(getproduct.getPublisher());
			product.setQuantity(getproduct.getQuantity());
			product.setOriginalPrice(getproduct.getOriginalPrice());
			product.setUsedBookSellingPrice(getproduct.getUsedBookSellingPrice());
			product.setNewBookSellingPrice(getproduct.getNewBookSellingPrice());
			product.setUsedBookDiscount(getproduct.getUsedBookDiscount());
			product.setNewBookDiscount(getproduct.getNewBookDiscount());
			
			List<ProductImages> productimages = getproduct.getListImages();
			List<ProductImageBean> imagelist = new ArrayList<ProductImageBean>();
			for(ProductImages productimage: productimages)
			{
				ProductImageBean image = new ProductImageBean();
				image.setId(productimage.getId());
				image.setImageUrl(productimage.getImageUrl());
				imagelist.add(image);
			}
			product.setProductImages(imagelist);
			CategoryBean categorybean = new CategoryBean();
			categorybean.setId(getproduct.getCategory().getId());
			categorybean.setcategoryName(getproduct.getCategory().getcategoryName());
			categorybean.setcategoryLabel(getproduct.getCategory().getCategoryLabel());
			product.setCategory(categorybean);
			
			List<SubCategory> subcategories = getproduct.getSubcategorieslist();
			List<SubCategoryBean> subcategorylist = new ArrayList<SubCategoryBean>();
			for(SubCategory subcategory: subcategories)
			{
				SubCategoryBean subcategorybean = new SubCategoryBean();
				subcategorybean.setId(subcategory.getId());
				subcategorybean.setsubcategoryName(subcategory.getsubcategoryName());
				subcategorybean.setSubcategoryLabel(subcategory.getSubcategoryLabel());
				subcategorylist.add(subcategorybean);
			}
			product.setSubcategory(subcategorylist);;
		}  catch(DBRecordNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return product;
	}
	
	

}