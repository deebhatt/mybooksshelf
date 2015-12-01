package com.mybooks.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
			throw new DAOException(e);
		}
	}
	
	public <T extends BaseEntity> void delete(T anyEntity) throws DAOException{
		try {
			entityManager.remove(entityManager.merge(anyEntity));
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public <T extends BaseEntity> void update(T anyEntity) throws DAOException {
		try {
			entityManager.merge(anyEntity);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

}
