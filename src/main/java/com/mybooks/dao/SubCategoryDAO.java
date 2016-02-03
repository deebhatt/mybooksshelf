package com.mybooks.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mybooks.entities.SubCategory;
import com.mybooks.exception.DBRecordNotFoundException;

@Service("subcategoryDAO")
@Repository
public class SubCategoryDAO extends BaseDAO {
	
	public SubCategory findSubCategoryByName(String subCategoryName) throws DBRecordNotFoundException
	{
		try {
			String query = "select c from " + SubCategory.class.getSimpleName() + " c where c.subcategoryName = :subCategoryName";
			Query q = entityManager.createQuery(query);
			q.setParameter("subCategoryName", subCategoryName);
			return  (SubCategory) q.getSingleResult();
		} catch (NoResultException e) {
			throw new DBRecordNotFoundException(e);
		}
	}
}
