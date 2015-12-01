package com.mybooks.dao;


import javax.persistence.Query;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mybooks.entities.UserMaster;
import com.mybooks.exception.DBRecordNotFoundException;

@Service("userDAO")
@Repository
public class UserDAO extends BaseDAO{

	public UserMaster findByEmail(String email)
			throws DBRecordNotFoundException{
		try {
			Query q = entityManager
					.createQuery("select e from UserMaster e where e.email = ?");
			q.setParameter(1, email);
			return (UserMaster) q.getSingleResult();
		} catch (EmptyResultDataAccessException e) {
			throw new DBRecordNotFoundException(e);
		}
	}

}
