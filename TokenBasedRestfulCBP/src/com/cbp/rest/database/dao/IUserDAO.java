package com.cbp.rest.database.dao;

import java.util.List;

import com.cbp.rest.exception.UserExistingException;
import com.cbp.rest.exception.UserNotFoundException;
import com.cbp.rest.model.User;
import com.cbp.rest.model.UserSecurity;

public interface IUserDAO {
	public boolean createUser( UserSecurity user ) throws UserExistingException;
	
	public String getUserIdByUserName( String username ) throws UserNotFoundException;
	public User getUser( String id ) throws UserNotFoundException;
	
	public List<User> getAllUsers();
	
	public UserSecurity getUserAuthentication( String id ) throws UserNotFoundException;
	public boolean setUserAuthentication( UserSecurity user ) throws UserNotFoundException;
	
	public boolean updateUser( User user ) throws UserNotFoundException;
	public boolean deleteUser( String id ) throws UserNotFoundException;
}
