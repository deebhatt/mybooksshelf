package com.mybooks.service;

import javax.inject.Inject;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybooks.dao.UserDAO;
import com.mybooks.entities.Roles;
import com.mybooks.entities.UserMaster;
import com.mybooks.exception.DAOException;
import com.mybooks.exception.DBRecordNotFoundException;
import com.mybooks.exception.EmailNotFoundException;
import com.mybooks.exception.RoleNotFoundException;
import com.mybooks.exception.UserServiceException;

@Service("userService")
@Repository
public class UserService {
	
	@Inject
	private UserDAO userDAO;
	
	public UserMaster findByEmail(String email) throws EmailNotFoundException {
		try {
			return userDAO.findByEmail(email);
		} catch (DBRecordNotFoundException e) {
			throw new EmailNotFoundException(e);
		}
	}

	@Transactional(rollbackFor = DAOException.class)
	public void saveUser(UserMaster userMaster) throws UserServiceException {
		try {
			userDAO.persist(userMaster);
		} catch (DAOException e) {
			throw new UserServiceException(e);
		}
	}
	
	@Transactional(rollbackFor = DAOException.class)
	public void updateUser(UserMaster userMaster) throws UserServiceException {
		try {
			userDAO.update(userMaster);
		} catch (DAOException e) {
			throw new UserServiceException(e);
		}
	}
	
	/**
	 * Get logged in user
	 * 
	 * @return
	 * @throws EmailNotFoundException
	 */
	public UserMaster getLoggedInUser() throws EmailNotFoundException {
		String email = SecurityContextHolder.getContext().getAuthentication()
				.getName();
		// Retrieve user from database
		UserMaster user = findByEmail(email);
		return user;
	}
	
	@Transactional(rollbackFor = DAOException.class)
	public void saveRole(Roles roles) throws UserServiceException {
		try {
			userDAO.persist(roles);
		} catch (DAOException e) {
			throw new UserServiceException(e);
		}
	}
	
	public Roles findRoleByName(String roleName) throws RoleNotFoundException {
		try {
			return userDAO.findRoleByName(roleName);
		} catch (DBRecordNotFoundException e) {
			throw new RoleNotFoundException(e);
		}
	}

}
