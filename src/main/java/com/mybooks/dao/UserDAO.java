package com.mybooks.dao;


import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mybooks.entities.Roles;
import com.mybooks.entities.UserMaster;
import com.mybooks.exception.DBRecordNotFoundException;

@Service("userDAO")
@Repository
public class UserDAO extends BaseDAO{

	public UserMaster findByEmail(String email)
			throws DBRecordNotFoundException{
		try {
			Query q = entityManager
					.createQuery("select e from UserMaster e where e.email = :email");
			q.setParameter("email", email);
			return (UserMaster) q.getSingleResult();
		} catch (NoResultException e) {
			throw new DBRecordNotFoundException(e);
		}
	}
	
	public Roles findRoleByName(String roleName)
			throws DBRecordNotFoundException{
		try {
			Query q = entityManager
					.createQuery("select e from Roles e where e.roleName = :roleName");
			q.setParameter("roleName", roleName);
			return (Roles) q.getSingleResult();
		} catch (NoResultException e) {
			throw new DBRecordNotFoundException(e);
		}
	}

}
