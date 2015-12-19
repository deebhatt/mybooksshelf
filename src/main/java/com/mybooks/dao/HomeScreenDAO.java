package com.mybooks.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mybooks.entities.Category;
import com.mybooks.entities.Products;
import com.mybooks.exception.DBRecordNotFoundException;
import com.mybooks.mbeans.ProductBean;

@Service
@Repository
public class HomeScreenDAO extends BaseDAO{
	
	public List<Category> listallcategories()
	{
		Query q = entityManager.createQuery("select distinct c from Category c join fetch c.subcategory");
		return q.getResultList();
	}
	
	public List<Products> getAllProducts()
	{
		Query q = entityManager.createQuery("select distinct p from Products p join fetch p.category");
		return q.getResultList();
	}
	
	public Products getProductbyName(String productName)
			throws DBRecordNotFoundException {
		try{
			String query = "select e from " + Products.class.getSimpleName() + " e where e.productName = :productName";
			Query q = entityManager.createQuery(query);
			q.setParameter("productName", productName);
			return (Products) q.getSingleResult();
		} catch (NoResultException e) {
			throw new DBRecordNotFoundException(e);
		}
	}

}