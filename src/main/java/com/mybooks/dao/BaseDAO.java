package com.mybooks.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mybooks.entities.BaseEntity;

public class BaseDAO implements IBaseDAO{
	
	protected EntityManager entityManager;
	
	@PersistenceContext(unitName = "persistenceUnit")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public <T extends BaseEntity> void persist(T anyEntity) {
		try {
			entityManager.persist(anyEntity);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T extends BaseEntity> List<T> findAll(Class<? extends T> anytype)
	{
		try{
			String entityName = anytype.getSimpleName();
			String query = "select e from "+ entityName +" e ";
			return entityManager.createQuery(query).getResultList();
		}
		catch(Exception e){
			throw new RuntimeException();
		}
	}
	
	public <T extends BaseEntity, X extends Long> T findById(Class<? extends T> type, X id)
	{
		try{
			return entityManager.find(type, id);
		}
		catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	public <T extends BaseEntity> void delete(T anyEntity) {
		try {
			entityManager.remove(entityManager.merge(anyEntity));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public <T extends BaseEntity> void update(T anyEntity) {
		try {
			entityManager.merge(anyEntity);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
