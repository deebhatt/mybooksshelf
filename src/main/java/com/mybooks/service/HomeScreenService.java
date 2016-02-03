package com.mybooks.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mybooks.dao.CategoryDAO;
import com.mybooks.dao.ProductDAO;
import com.mybooks.dao.SubCategoryDAO;
import com.mybooks.entities.BaseEntity;
import com.mybooks.entities.Category;
import com.mybooks.entities.ProductImages;
import com.mybooks.entities.Products;
import com.mybooks.entities.SubCategory;
import com.mybooks.exception.DAOException;
import com.mybooks.exception.DBRecordNotFoundException;
import com.mybooks.mbeans.CategoryBean;
import com.mybooks.mbeans.ProductBean;
import com.mybooks.mbeans.ProductImageBean;
import com.mybooks.mbeans.SubCategoryBean;
import com.mybooks.utility.PropertiesUtil;

@Service("homescreenservice")
@Repository
public class HomeScreenService {
	
	private static final Log LOG = LogFactory
			.getLog(HomeScreenService.class);
	
	@Inject
	private ProductDAO productDAO;
	
	@Inject
	private CategoryDAO categoryDAO;
	
	@Inject
	private SubCategoryDAO subcategoryDAO;
	
	public List<CategoryBean> findcategories()
	{
		List<CategoryBean> categorylist = new ArrayList<CategoryBean>();
		List<Category> mastercategory = categoryDAO.listallcategories();
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
		List<Products> listall = productDAO.getAllProducts();
		for(Products products : listall)
		{
			ProductBean productbean = new ProductBean();
			productbean.setId(products.getId());
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
			Products getproduct = productDAO.getProductbyName(productName);
			product = new ProductBean();
			product.setId(getproduct.getId());
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
			
		}
		return product;
	}
	
	public List<ProductBean> getAllProductsforSearchQuery(String query) throws DAOException
	{
		List<ProductBean> productslist = new ArrayList<ProductBean>();
		List<Products> listall;
		try {
			listall = productDAO.getAllProductsforSearchQuery(query);
			for(Products products : listall)
			{
				ProductBean productbean = new ProductBean();
				productbean.setId(products.getId());
				productbean.setProductName(products.getProductName());
				productbean.setProductLabel(products.getProductLabel());
				
				productslist.add(productbean);
			}
			return productslist;
		} catch (DAOException e) {
			throw new DAOException(e);
		}
	}
	
	public List<ProductBean> getProductsByCategory(String categoryNameWithPage) throws DAOException
	{
		List<ProductBean> productslist = new ArrayList<ProductBean>();
		List<Products> listall;
		String categoryName = categoryNameWithPage.split(":")[0];
		int pageNo = Integer.parseInt(categoryNameWithPage.split(":")[1]);
		int offset = (pageNo-1) * (Integer.valueOf(PropertiesUtil.getProperty("result.setmaxResult")));
		try {
			//Get category Type by categoryName
			Category categoryType = categoryDAO.findCategoryByName(categoryName);
			listall = productDAO.getListofProductByCategory(categoryType, offset);
			for(Products products : listall)
			{
				ProductBean productbean = new ProductBean();
				productbean.setId(products.getId());
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
		} catch (DBRecordNotFoundException e) {
			LOG.debug("Category Name does not exist");
			throw new DBRecordNotFoundException(e);
		}
		catch (DAOException e) {
			LOG.error("Error while retrieving products by Category");
			throw new DAOException(e);
		}
	}
	
	public List<ProductBean> getProductsBySubCategory(String CategoryandSubcategoryNameWithPage) throws DAOException
	{
		String[] catandSub = CategoryandSubcategoryNameWithPage.split(":");
		List<ProductBean> productslist = new ArrayList<ProductBean>();
		List<Products> listall;
		try {
			//Get Category Type by CategoryName
			Category categoryType = categoryDAO.findCategoryByName(catandSub[0]);
			//Get subcategory Type by subcategoryName
			SubCategory subcategoryType = subcategoryDAO.findSubCategoryByName(catandSub[1]);
			int pageNo = Integer.parseInt(catandSub[2]);
			int offset = (pageNo-1) * (Integer.valueOf(PropertiesUtil.getProperty("result.setmaxResult")));
			listall = productDAO.getListofProductByCategoryandSubCategory(categoryType, subcategoryType, offset);
			for(Products products : listall)
			{
				ProductBean productbean = new ProductBean();
				productbean.setId(products.getId());
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
		} catch (DBRecordNotFoundException e) {
			LOG.debug("SubCategory Name does not exist");
			throw new DBRecordNotFoundException(e);
		}
		catch (DAOException e) {
			LOG.error("Error while retrieving products by SubCategory");
			throw new DAOException(e);
		}
	}
	
	public Long countAllfromEntity(String entityName)
	{
		Long countObjects = null;
		try {
			switch(entityName.toLowerCase())
			{
			case "products" :
				countObjects = productDAO.countAll(Products.class);
				break;
			}
			return countObjects;
		} catch(DAOException e) {
			LOG.error("Error while retrieving count of all Entity");
		}
		return countObjects;
	}

}