package com.mybooks.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.mybooks.entities.BaseEntity;
import com.mybooks.exception.DAOException;


public class BaseDAO implements IBaseDAO{
	
	protected EntityManager entityManager;
	
	@PersistenceContext(unitName = "persistenceUnit")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public <T extends BaseEntity> void persist(T anyEntity) throws DAOException {
		try {
			entityManager.persist(anyEntity);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends BaseEntity> List<T> findAll(Class<? extends T> anytype) throws DAOException
	{
		try{
			String entityName = anytype.getSimpleName();
			String query = "select e from "+ entityName +" e ";
			return entityManager.createQuery(query).getResultList();
		}
		catch(Exception e){
			throw new DAOException(e);
		}
	}
	
	
	public <T extends BaseEntity, X extends Long> T findById(Class<? extends T> type, X id) throws DAOException
	{
		try{
			return entityManager.find(type, id);
		}
		catch (Exception e) {

			throw new RuntimeException();
		}
	}

	public <T extends BaseEntity> void update(T anyEntity) throws DAOException {
		try {
			entityManager.merge(anyEntity);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public <T extends BaseEntity> void delete(T anyEntity) {
		try {
			entityManager.remove(entityManager.merge(anyEntity));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public <T extends BaseEntity> List<T> findByFilter(Class<? extends T> type,
			Map<String, Object> criteria) throws DAOException {
		try {
			String entityName = type.getSimpleName();
			StringBuffer query = new StringBuffer();
			query.append("select e from " + entityName + " e ");
			List<Object> values = new ArrayList<Object>();
			if (criteria != null && criteria.size() > 0) {
				query.append(" where ");
				int index = 1;
				for (Entry<String, Object> entry : criteria.entrySet()) {
					values.add(entry.getValue());
					if (index > 1) {
						query.append(" and ");
					}
					query.append("e.").append(entry.getKey()).append(" = ?")
							.append(index++);
				}
			}
			Query q = entityManager.createQuery(query.toString());
			for (int i = 0; i < values.size(); i++) {
				q.setParameter(i + 1, values.get(i));
			}
			return (List<T>) q.getResultList();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public <T extends BaseEntity> Long countAll(Class<? extends T> type) throws DAOException {
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
			countQuery.select(builder.count(countQuery.from(type)));
			Long count = entityManager.createQuery(countQuery).getSingleResult();
			return count;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}
