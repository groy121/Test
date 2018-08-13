package com.cbp.rest.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cbp.rest.database.connection.DBConnection;
import com.cbp.rest.exception.UserExistingException;
import com.cbp.rest.exception.UserNotFoundException;
import com.cbp.rest.model.User;
import com.cbp.rest.model.UserSecurity;

public class UserDAO implements IUserDAO {
		
	private Connection connection = null;
	
	public UserDAO( DBConnection connection ) {
		this.connection = (Connection) connection.getDBConnection();
	}

	@Override
	public boolean createUser( UserSecurity user ) throws UserExistingException {		
		PreparedStatement stmt = null;
		
	    try {
	    	
			// check if user already registered
			try {
				if( getUserIdByUserName( user.getUserName() ) != null ) {
					throw new UserExistingException( user.getUserName() );
				}
			}
			// continue if no user found
			catch( UserNotFoundException e) {}
	    	
	    	stmt = connection.prepareStatement( "INSERT INTO USER(" + 
	    									    "username,firstname,lastname,password,role) VALUES" +
	    									    "(?,?,?,?,?)" );
	    	stmt.setString( 1, user.getUserName() );
	    	stmt.setString( 2, user.getFirstname() );
	    	stmt.setString( 3, user.getLastname() );
	    	stmt.setString( 4, user.getPassword() );
	    	stmt.setString( 5, user.getRole() );
		    stmt.executeUpdate();
		    
	    } catch ( SQLException e ) {
	    	e.printStackTrace();
	    }
	    finally {
	    	try {
				stmt.close();
			} catch ( SQLException e ) {
				e.printStackTrace();
			}
	    }
	    
	    return true;
	}

	@Override
	public String getUserIdByUserName(String username) throws UserNotFoundException {
				
		String id = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
	    try {
	    	stmt = connection.prepareStatement( "SELECT id FROM USER WHERE username=?;" );
	    	stmt.setString(1, username);
		    rs = stmt.executeQuery();
		    
		    if( rs.next() ) {
		    	id = String.valueOf( rs.getInt("id") );
		    }
		    else {
		    	throw new UserNotFoundException( username );
		    }
		    
	    } catch ( SQLException e ) {
	    	e.printStackTrace();
	    }
	    finally {
	    	try {
				rs.close();
				stmt.close();
			} catch ( SQLException e ) {
				e.printStackTrace();
			}
	    }
	    
	    return id;
	}

	@Override
	public User getUser(String id) throws UserNotFoundException {
				
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User user = null;
		
	    try {
	    	stmt = connection.prepareStatement( "SELECT id, firstname, lastname, username FROM USER WHERE id=?;" );
	    	stmt.setString(1, id);
		    rs = stmt.executeQuery();
		    
		    if( rs.next() ) {
		    	String userId = String.valueOf( rs.getInt("id") );
		    	String username = rs.getString("username");
		    	String firstname = rs.getString("firstname");
		    	String lastname = rs.getString("lastname");
		    	
		    	user = new User(userId, username, firstname, lastname );
		    }
		    else {
		    	throw new UserNotFoundException( id );
		    }
		    
	    } catch ( SQLException e ) {
	    	e.printStackTrace();
	    }
	    finally {
	    	try {
				rs.close();
				stmt.close();
			} catch ( SQLException e ) {
				e.printStackTrace();
			}
	    }
	    
	    return user;
	}

	@Override
	public List<User> getAllUsers() {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<User> user = new ArrayList<User>();
		
	    try {
	    	stmt = connection.prepareStatement( "SELECT id, firstname, lastname, username FROM USER;" );
		    rs = stmt.executeQuery();
		    
		    while( rs.next() ) {
		    	String userId = String.valueOf( rs.getInt("id") );
		    	String username = rs.getString("username");
		    	String firstname = rs.getString("firstname");
		    	String lastname = rs.getString("lastname");
		    	
		    	user.add( new User( userId, username, firstname, lastname ) );
		    }
		    
	    } catch ( SQLException e ) {
	    	e.printStackTrace();
	    }
	    finally {
	    	try {
				rs.close();
				stmt.close();
			} catch ( SQLException e ) {
				e.printStackTrace();
			}
	    }
	    
	    return user;
	}

	@Override
	public UserSecurity getUserAuthentication( String id ) throws UserNotFoundException {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		UserSecurity userSecurity = null;
		
	    try {
	    	stmt = connection.prepareStatement( "SELECT username, password, token, role FROM USER WHERE id=?;" );
	    	stmt.setString(1, id);
		    rs = stmt.executeQuery();
		    
		    if( rs.next() ) {
		    	String username = rs.getString("username");
		    	String password = rs.getString("password");
		    	String token = rs.getString("token");
		    	String role = rs.getString("role");
		    	
		    	userSecurity = new UserSecurity( username, password, token, role );
		    }
		    else {
		    	throw new UserNotFoundException( id );
		    }
		    
	    } catch ( SQLException e ) {
	    	e.printStackTrace();
	    }
	    finally {
	    	try {
				rs.close();
				stmt.close();
			} catch ( SQLException e ) {
				e.printStackTrace();
			}
	    }
	    
	    return userSecurity;
	}

	@Override
	public boolean setUserAuthentication( UserSecurity user ) throws UserNotFoundException {
		
		PreparedStatement stmt = null;
		
	    try {
	    	// prepare query
	    	StringBuffer query = new StringBuffer();
	    	query.append( "UPDATE USER SET " );
	    	
	    	boolean comma = false;
	    	List<String> prepare = new ArrayList<String>();
	    	if( user.getPassword() != null ) {
	    		query.append( "password=?" );
	    		comma = true;
	    		prepare.add( user.getPassword() );
	    	}
	    	
	    	if( user.getToken() != null ) {
	    		if( comma ) query.append(",");
	    		query.append( "token=?" );
	    		comma = true;
	    		prepare.add( user.getToken() );
	    	}
	    	
	    	if( user.getRole() != null ) {
	    		if( comma ) query.append(",");
	    		query.append( "role=?" );
	    		prepare.add( user.getRole() );
	    	}
	    	
	    	query.append(" WHERE id=?");
	    	stmt = connection.prepareStatement( query.toString() );
	    	
	    	for( int i = 0; i < prepare.size(); i++ ) {
	    		stmt.setString( i+1, prepare.get(i) );
	    	}
	    	
	    	stmt.setInt( prepare.size() + 1, Integer.parseInt( user.getId() ) );
	    	
	    	stmt.executeUpdate();
		    
	    } catch ( SQLException e ) {
	    	e.printStackTrace();
	    }
	    finally {
	    	try {
				stmt.close();
			} catch ( SQLException e ) {
				e.printStackTrace();
			}
	    }
	    
	    return true;
	}

	@Override
	public boolean updateUser( User user ) throws UserNotFoundException {
		
		PreparedStatement stmt = null;
		
	    try {
	    	// prepare query
	    	StringBuffer query = new StringBuffer();
	    	query.append( "UPDATE USER SET " );
	    	
	    	boolean comma = false;
	    	List<String> prepare = new ArrayList<String>();
	    	if( user.getFirstname() != null ) {
	    		query.append( "firstname=?" );
	    		comma = true;
	    		prepare.add( user.getFirstname() );
	    	}
	    	
	    	if( user.getLastname() != null ) {
	    		if( comma ) query.append(",");
	    		query.append( "lastname=?" );
	    		comma = true;
	    		prepare.add( user.getLastname() );
	    	}
	    	
	    	if( user.getUserName() != null ) {
	    		if( comma ) query.append(",");
	    		query.append( "username=?" );
	    		prepare.add( user.getUserName() );
	    	}
	    	
	    	query.append(" WHERE id=?");
	    	stmt = connection.prepareStatement( query.toString() );
	    	
	    	for( int i = 0; i < prepare.size(); i++ ) {
	    		stmt.setString( i+1, prepare.get(i) );
	    	}
	    	
	    	stmt.setInt( prepare.size() + 1, Integer.parseInt( user.getId() ) );
	    	
	    	stmt.executeUpdate();
		    
	    } catch ( SQLException e ) {
	    	e.printStackTrace();
	    }
	    finally {
	    	try {
				stmt.close();
			} catch ( SQLException e ) {
				e.printStackTrace();
			}
	    }
		
		return true;
	}

	@Override
	public boolean deleteUser( String id ) throws UserNotFoundException {
		
		PreparedStatement stmt = null;
		
	    try {
	    	
	    	stmt = connection.prepareStatement( "DELETE FROM USER WHERE id=?" );
	    	stmt.setString( 1, id );
	    	
		    stmt.executeUpdate();
		    
	    } catch ( SQLException e ) {
	    	e.printStackTrace();
	    }
	    finally {
	    	try {
				stmt.close();
			} catch ( SQLException e ) {
				e.printStackTrace();
			}
	    }
	    
	    return true;
	}
}
