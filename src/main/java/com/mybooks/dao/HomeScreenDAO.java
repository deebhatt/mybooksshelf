package com.mybooks.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mybooks.entities.Category;
import com.mybooks.entities.Products;

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

}