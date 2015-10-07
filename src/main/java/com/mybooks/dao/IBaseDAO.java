package com.mybooks.dao;

import java.util.List;

import com.mybooks.entities.BaseEntity;

public interface IBaseDAO {
	
	public <T extends BaseEntity> void persist(T anyEntity);

	public <T extends BaseEntity> void update(T anyEntity);

	public <T extends BaseEntity> void delete(T anyEntity);

	public <T extends BaseEntity> List<T> findAll(Class<? extends T> type);

	public <T extends BaseEntity, X extends Long> T findById(
			Class<? extends T> type, X id);

}
