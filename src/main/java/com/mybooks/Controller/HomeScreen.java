package com.mybooks.Controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mybooks.mbeans.CategoryBean;
import com.mybooks.mbeans.ProductBean;
import com.mybooks.service.HomeScreenService;

@Controller
public class HomeScreen {
	
	@Inject
	private HomeScreenService homescreenservice;
	
	@RequestMapping(value="/getCategories" ,method=RequestMethod.GET)
	public @ResponseBody List<CategoryBean> getCategories()
	{
		return homescreenservice.findcategories();
	}
	
	@RequestMapping(value="/getProducts" ,method=RequestMethod.GET)
	public @ResponseBody List<ProductBean> getProducts()
	{
		return homescreenservice.listAllProducts();
	}

}