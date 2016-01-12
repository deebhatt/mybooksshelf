package com.mybooks.service;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mybooks.entities.Roles;
import com.mybooks.enums.USER_ROLES;
import com.mybooks.exception.RoleNotFoundException;
import com.mybooks.exception.UserServiceException;

@Service("createRoles")
@Repository
public class CreateRolesonStartup {
	
	private static final Log LOG = LogFactory
			.getLog(CreateRolesonStartup.class);
	
	@Inject
	private UserService userService;

	@PostConstruct
	public void init()
	{
		List<USER_ROLES> listOfRoles = Arrays.asList(USER_ROLES.values());
		for(USER_ROLES roleName: listOfRoles)
		{
			Roles roles = null;
			try {
				roles = userService.findRoleByName(roleName.toString());
			} catch (RoleNotFoundException e) {
				LOG.debug("Role Not Found so create one");
			}
			try {
				if(roles == null){
					roles = new Roles();
					roles.setRoleName(roleName.toString());
					userService.saveRole(roles);
				}
			}
			catch (UserServiceException e) {
				LOG.error("Error while saving the Roles on server startup");
			} 
		}
	}
}
