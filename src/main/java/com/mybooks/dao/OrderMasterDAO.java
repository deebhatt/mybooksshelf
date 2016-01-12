package com.mybooks.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mybooks.entities.OrderDetails;
import com.mybooks.entities.Products;
import com.mybooks.exception.DAOException;
import com.mybooks.exception.DBRecordNotFoundException;

@Service("orderMasterDAO")
@Repository
public class OrderMasterDAO extends  BaseDAO{
	
	public List<Products> findProductsbyProductId(List<Long> listProductId)throws DAOException
	{
		try{
			String query = "select p from " + Products.class.getSimpleName() + " p where p.id IN (:listProductId)";
			Query q = entityManager.createQuery(query);
			q.setParameter("listProductId", listProductId);
			return q.getResultList();
		} catch(Exception e) {
			throw new DAOException(e);
		}
	}
	
	public List<OrderDetails> getAllOrders()
			throws DBRecordNotFoundException {
		try {
			Query q = entityManager.createQuery("select distinct p from OrderDetails p join fetch p.productsAndQuantity join fetch p.shippingAddress");
			return q.getResultList();
		} catch(Exception e) {
			throw new DBRecordNotFoundException(e);
		}
	}
}
