package com.mybooks.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mybooks.exception.DAOException;
import com.mybooks.exception.DBRecordNotFoundException;
import com.mybooks.mbeans.CategoryBean;
import com.mybooks.mbeans.ProductBean;
import com.mybooks.service.HomeScreenService;

@Controller
public class HomeScreen {
	
	private static final Log LOG = LogFactory
			.getLog(HomeScreen.class);
	
	@Inject
	private HomeScreenService homescreenservice;
	
	@RequestMapping(value="/getCategoriesandSubCategories" ,method=RequestMethod.GET)
	public @ResponseBody List<CategoryBean> getCategoriesandSubCategories()
	{
		return homescreenservice.findcategories();
	}
	
	@RequestMapping(value="/getProducts" ,method=RequestMethod.GET)
	public @ResponseBody List<ProductBean> getProducts()
	{
		return homescreenservice.listAllProducts();
	}
	
	@RequestMapping(value="/product/{productName}" ,method=RequestMethod.GET)
	public @ResponseBody ProductBean getProductbyName(@PathVariable("productName") String productName)
	{
		return homescreenservice.getProductbyName(productName);	
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public @ResponseBody List<ProductBean> getSearchResults(@RequestBody String queryString)
	{
		try {
			return homescreenservice.getAllProductsforSearchQuery(queryString);
		} catch (DAOException e) {
			LOG.error(e);
			return null;
		}
	}
	
	@RequestMapping(value="/getProductsbyCategory" ,method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<ProductBean> getProductsbyCategory(@RequestBody String categoryName)
	{
		try {
			return homescreenservice.getProductsByCategory(categoryName);
		}	catch(DBRecordNotFoundException e) {
			return null;
		}
		catch (DAOException e) {
			return null;
		}
	}
	
	@RequestMapping(value="/getProductsbySubcategory", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<ProductBean> getProductsbySubcategory(@RequestBody String CategoryandSubcategoryName)
	{
		try {
			return homescreenservice.getProductsBySubCategory(CategoryandSubcategoryName);
		}	catch(DBRecordNotFoundException e) {
			return null;
		}
		catch (DAOException e) {
			return null;
		}
	}
	
	@RequestMapping(value="/countAll", method=RequestMethod.POST)
	public @ResponseBody Long countAllfromEntity(@RequestBody String entityName)
	{
		return homescreenservice.countAllfromEntity(entityName);
	}

}