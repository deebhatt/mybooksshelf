package com.mybooks.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mybooks.entities.GuestUser;
import com.mybooks.exception.DBRecordNotFoundException;


@Service("guestuserDAO")
@Repository
public class GuestUserDAO extends BaseDAO{
	
	public GuestUser findUserByEmail(String email)throws DBRecordNotFoundException
	{
		try {
			Query q = entityManager
					.createQuery("select e from GuestUser e where e.email = :email");
			q.setParameter("email", email);
			return (GuestUser) q.getSingleResult();
		} catch (NoResultException e) {
			throw new DBRecordNotFoundException(e);
		}
	}
	
	public GuestUser findUserByEmailandToken(String email, Long token)throws DBRecordNotFoundException
	{
		try {
			Query q = entityManager
					.createQuery("select e from GuestUser e where e.email = :email and e.token = :token");
			q.setParameter("email", email);
			q.setParameter("token", token);
			return (GuestUser) q.getSingleResult();
		} catch (NoResultException e) {
			throw new DBRecordNotFoundException(e);
		}
	}

}
