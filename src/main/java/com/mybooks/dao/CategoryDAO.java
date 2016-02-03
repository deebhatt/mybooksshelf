package com.mybooks.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mybooks.entities.Category;
import com.mybooks.exception.DBRecordNotFoundException;

@Service("categoryDAO")
@Repository
public class CategoryDAO extends BaseDAO {
	
	public Category findCategoryByName(String categoryName) throws DBRecordNotFoundException
	{
		try{
			String query = "select c from " + Category.class.getSimpleName() + " c where c.categoryName = :categoryName";
			Query q = entityManager.createQuery(query);
			q.setParameter("categoryName", categoryName);
			return (Category) q.getSingleResult();
		} catch (NoResultException e) {
			throw new DBRecordNotFoundException(e);
		}
	}
	
	public List<Category> listallcategories()
	{
		Query q = entityManager.createQuery("select distinct c from Category c join fetch c.subcategory");
		return q.getResultList();
	}

}
