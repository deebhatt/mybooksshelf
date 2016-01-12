package com.mybooks.Controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mybooks.commons.ResponseMessage;
import com.mybooks.exception.OrderServiceException;
import com.mybooks.mbeans.OrderDetailBean;
import com.mybooks.mbeans.OrderFormBean;
import com.mybooks.service.OrderExecutionService;

@Controller
public class OrderServiceController {
	
	private static final Log LOG = LogFactory
			.getLog(OrderServiceController.class);
	
	@Inject
	private OrderExecutionService orderExecutionService;
	
	@RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage processorder(@RequestBody OrderFormBean orderForm, @CookieValue(value="verify_guestuser", defaultValue = "CookieNotFound") String cookieSecret) {
		return orderExecutionService.createnewOrder(orderForm, cookieSecret);
	}
	
	@RequestMapping(value="/getallOrders" ,method=RequestMethod.GET)
	public @ResponseBody List<OrderDetailBean> listallProducts()
	{
		try {
			return orderExecutionService.getAllOrders();
		} catch (OrderServiceException e) {
			LOG.error(e);
			return null;
		}
	}
}
