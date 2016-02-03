package com.mybooks.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mybooks.entities.Category;
import com.mybooks.entities.Products;
import com.mybooks.entities.SubCategory;
import com.mybooks.exception.DAOException;
import com.mybooks.exception.DBRecordNotFoundException;
import com.mybooks.utility.PropertiesUtil;

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
	
	public List<Products> getListofProductByCategory(Category categoryType, int offset) throws DAOException
	{
		try {
			String query = "select p from " + Products.class.getSimpleName() + " p where p.category = :categoryType";
			Query q = entityManager.createQuery(query)
			.setParameter("categoryType", categoryType)
			.setFirstResult(offset)
			.setMaxResults(Integer.valueOf(PropertiesUtil.getProperty("result.setmaxResult")));
			return (List<Products>) q.getResultList();
		} catch(Exception e) {
			throw new DAOException(e);
		}
	}
	
	public List<Products> getAllProducts()
	{
		Query q = entityManager.createQuery("select distinct p from Products p join fetch p.category join fetch p.listImages");
		return q.getResultList();
	}
	
	public List<Products> getAllProductsforSearchQuery(String queryString) throws DAOException
	{
		String query = "select distinct p from " + Products.class.getSimpleName() + " p where p.productName LIKE :queryString OR p.productLabel LIKE :queryString OR p.Author LIKE :queryString OR p.Publisher LIKE :queryString";
		Query q = entityManager.createQuery(query);
		q.setParameter("queryString", "%" + queryString + "%");
		return q.getResultList();
	}
	
	public Products getProductbyName(String productName)
			throws DBRecordNotFoundException {
		try{
			String query = "select p from " + Products.class.getSimpleName() + " p join fetch p.category join fetch p.subcategorieslist where p.productName = :productName";
			Query q = entityManager.createQuery(query);
			q.setParameter("productName", productName);
			return (Products) q.getSingleResult();
		} catch (NoResultException e) {
			throw new DBRecordNotFoundException(e);
		}
	}
	
	public List<Products> getListofProductBySubCategory(SubCategory subCategoryType, int offset) throws DAOException
	{
		try {
			String query = "select distinct p from " + Products.class.getSimpleName() + " p join p.subcategorieslist type where type.id = :IdSubCategory";
			Query q = entityManager.createQuery(query);
			q.setParameter("IdSubCategory", subCategoryType.getId())
			.setFirstResult(offset)
			.setMaxResults(Integer.valueOf(PropertiesUtil.getProperty("result.setmaxResult")));
			return (List<Products>) q.getResultList();
		} catch(Exception e) {
			throw new DAOException(e);
		}
	}
	
	public List<Products> getListofProductByCategoryandSubCategory(Category categoryType, SubCategory subCategoryType, int offset) throws DAOException
	{
		try {
			String query = "select distinct p from " + Products.class.getSimpleName() + " p join p.subcategorieslist type where type.id = :IdSubCategory and p.category = :categoryType";
			Query q = entityManager.createQuery(query);
			q.setParameter("IdSubCategory", subCategoryType.getId())
			.setParameter("categoryType", categoryType)
			.setFirstResult(offset)
			.setMaxResults(Integer.valueOf(PropertiesUtil.getProperty("result.setmaxResult")));
			return (List<Products>) q.getResultList();
		} catch(Exception e) {
			throw new DAOException(e);
		}
	}

}
