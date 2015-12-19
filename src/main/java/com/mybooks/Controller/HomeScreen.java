package com.mybooks.Controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mybooks.exception.DBRecordNotFoundException;
import com.mybooks.mbeans.CategoryBean;
import com.mybooks.mbeans.ProductBean;
import com.mybooks.service.HomeScreenService;

@Controller
public class HomeScreen {
	
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

}