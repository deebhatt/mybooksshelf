package com.mybooks.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mybooks.entities.Products;
import com.mybooks.exception.DBRecordNotFoundException;

@Service("productDAO")
@Repository
public class ProductDAO extends BaseDAO{
	
	public List<Products> getListofProductByIds(List<Long> productIds) throws DBRecordNotFoundException
	{
		try{
			String query = "select p from " + Products.class.getSimpleName() + " p where p.id IN :productIds";
			Query q = entityManager.createQuery(query);
			q.setParameter("productIds", productIds);
			return q.getResultList();
		}
		catch(EmptyResultDataAccessException e) {
			throw new DBRecordNotFoundException(e);
		}
	}

}
