package com.mybooks.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybooks.commons.ResponseMessage;
import com.mybooks.constants.ApplicationConstants;
import com.mybooks.dao.OrderMasterDAO;
import com.mybooks.dao.ProductDAO;
import com.mybooks.entities.Address;
import com.mybooks.entities.OrderDetails;
import com.mybooks.entities.Products;
import com.mybooks.entities.UserMaster;
import com.mybooks.enums.VALIDATION_MODE;
import com.mybooks.exception.DAOException;
import com.mybooks.exception.DBRecordNotFoundException;
import com.mybooks.exception.EmailNotFoundException;
import com.mybooks.exception.OrderServiceException;
import com.mybooks.exception.ShippingAddressValidationException;
import com.mybooks.mbeans.AddressBean;
import com.mybooks.mbeans.OrderDetailBean;
import com.mybooks.mbeans.OrderFormBean;
import com.mybooks.mbeans.ProductOrder;
import com.mybooks.mbeans.UserFormBean;
import com.mybooks.utility.SecurityUtil;
import com.mybooks.validator.ShippingAddressValidator;

@Service("orderExecutionService")
@Repository
public class OrderExecutionService {

	private static final Log LOG = LogFactory
			.getLog(OrderExecutionService.class);
	
	@Inject
	private OrderMasterDAO orderMasterDAO;
	
	@Inject
	private ProductDAO productDAO;
	
	@Inject
	private UserService userService;
	
	@Inject
	private ShippingAddressValidator shippingaddressValidator;
	
	@Transactional(rollbackFor=DAOException.class)
	public ResponseMessage createnewOrder(OrderFormBean orderform, String cookieSecret)
	{
		try {
			String userMobile = null;
			List<Long> productIds = new ArrayList<Long>();
			BigDecimal orderTotal = new BigDecimal(0);
			if(!cookieSecret.equals("CookieNotFound"))
			{
				userMobile = SecurityUtil.decodeValue(cookieSecret);
			}
			else
			{
				return new ResponseMessage(ResponseMessage.Type.danger,
						"The session is expired. Please resend the token again!!!");
			}
			UserMaster customer = userService.findByMobileNo(userMobile);
			
			List<ProductOrder> productOrder = orderform.getOrderProducts();
			Map<Long, Integer> pidandQuantity = new HashMap<Long, Integer>();
			for(ProductOrder products: productOrder)
			{
				pidandQuantity.put(products.getP_id(), products.getQuantity());
				productIds.add(products.getP_id());
			}
			shippingaddressValidator.isShippingAddressValid(orderform.getAddress(), VALIDATION_MODE.SAVE);
			Address shipAddress = new Address();
			shipAddress.setAddressLine1(orderform.getAddress().getAddressLine1());
			shipAddress.setAddressLine2(orderform.getAddress().getAddressLine2());
			shipAddress.setAddressType(orderform.getAddress().getAddressType());
			shipAddress.setCountry(orderform.getAddress().getCountry());
			shipAddress.setFullName(orderform.getAddress().getFullName());
			shipAddress.setLandMark(orderform.getAddress().getLandMark());
			shipAddress.setMobileNumber(orderform.getAddress().getMobileNumber());
			shipAddress.setPinCode(orderform.getAddress().getPinCode());
			shipAddress.setState(orderform.getAddress().getState());
			shipAddress.setTownOrCity(orderform.getAddress().getTownOrCity());
			shipAddress.setMasterUser(customer);
			
			OrderDetails order = new OrderDetails();
			order.setOrderDate(new Date());
			order.setorderStatus(ApplicationConstants.ORDER_STATUS_PENDING);
			Map<Products, Integer> productsAndQuantity = new HashMap<Products, Integer>();
			List<Products> orderedProducts = productDAO.getListofProductByIds(productIds);
			for(Products product: orderedProducts)
			{
				productsAndQuantity.put(product, pidandQuantity.get(product.getId()));
				orderTotal = orderTotal.add(product.getUsedBookSellingPrice().multiply(new BigDecimal(pidandQuantity.get(product.getId()))));
			}
			order.setProductsAndQuantity(productsAndQuantity);
			order.setOrderTotal(orderTotal);
			order.setCustomerDetails(customer);
			order.setShippingAddress(shipAddress);
			orderMasterDAO.persist(order);
			
			return new ResponseMessage(
					ResponseMessage.Type.success,
					"The order is placed successfully", Long.toString(order.getOrderId()));
		} catch (ShippingAddressValidationException e) {
			LOG.error("There was a validation error while registering", e);
			return new ResponseMessage(ResponseMessage.Type.danger,
					"There was a validation error while registering");
		} catch (DBRecordNotFoundException e) {
			LOG.error("Product List is empty, cannot save the order", e);
			return new ResponseMessage(ResponseMessage.Type.danger,
					"Product List is empty, cannot save the order. Please try again");
		} catch (DAOException e) {
			LOG.error("There was a technical error while processing Order", e);
			return new ResponseMessage(ResponseMessage.Type.danger,
					"There was a technical error while processing Order");
		}
	}
	
	public List<OrderDetailBean> getAllOrders() throws OrderServiceException
	{
		try {
			List<OrderDetails> orderList = orderMasterDAO.getAllOrders();
			List<OrderDetailBean> showorderList = new ArrayList<OrderDetailBean>();
			for(OrderDetails order: orderList)
			{
				OrderDetailBean orders = new OrderDetailBean();
				orders.setOrderDate(order.getOrderDate());
				orders.setOrderId(order.getOrderId());
				orders.setDeliveryDate(order.getDeliveryDate());
				orders.setOrderStatus(order.getorderStatus());
				orders.setOrderTotal(order.getOrderTotal());
				
				AddressBean address = new AddressBean();
				address.setAddressLine1(order.getShippingAddress().getAddressLine1());
				address.setAddressLine2(order.getShippingAddress().getAddressLine2());
				address.setAddressType(order.getShippingAddress().getAddressType());
				address.setCountry(order.getShippingAddress().getCountry());
				address.setFullName(order.getShippingAddress().getFullName());
				address.setLandMark(order.getShippingAddress().getLandMark());
				address.setMobileNumber(order.getShippingAddress().getMobileNumber());
				address.setPinCode(order.getShippingAddress().getPinCode());
				address.setState(order.getShippingAddress().getState());
				address.setTownOrCity(order.getShippingAddress().getTownOrCity());
				
				orders.setShippingAddress(address);
				UserFormBean user = new UserFormBean();
				user.setFirstName(order.getCustomerDetails().getFirstName());
				user.setLastName(order.getCustomerDetails().getLastName());
				user.setUserId(order.getCustomerDetails().getUserId());
				user.setEmail(order.getCustomerDetails().getEmail());	
				orders.setUser(user);
				showorderList.add(orders);
			}
			return showorderList;
			
		} catch(DAOException e) {
			LOG.error("An error occured while retrieving Orders", e);
			throw new OrderServiceException();
		}
	}
}
