package com.mybooks.dao;

import java.util.List;

import com.mybooks.entities.BaseEntity;
import com.mybooks.exception.DAOException;

public interface IBaseDAO {
	
	public <T extends BaseEntity> void persist(T anyEntity) throws DAOException;

	public <T extends BaseEntity> void update(T anyEntity) throws DAOException;

	public <T extends BaseEntity> void delete(T anyEntity) throws DAOException;

	public <T extends BaseEntity> List<T> findAll(Class<? extends T> type) throws DAOException;

	public <T extends BaseEntity, X extends Long> T findById(
			Class<? extends T> type, X id) throws DAOException;

}
