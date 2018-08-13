package com.cbp.rest.database.connection;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cbp.rest.security.PasswordSecurity;


public class DBConnection {
	Connection con=null;
	public Connection getDBConnection() {
	    try {	 
	    	Class.forName("com.mysql.jdbc.Driver");  
	    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cbp_enovia","root","Nidhi@123");  	    	
	        return con;
	    } catch ( Exception e ) {
	      e.printStackTrace();
	      System.exit(0);
	    }
		return con;
	}

	private boolean checkForUserSchema() {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT name FROM sqlite_master WHERE type='table' AND name='USER';" );
			if (rs.next()) {
				return true;
			}
			rs.close();
			stmt.close();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return false;
	}
	
	private void createUserSchema() {
		if( checkForUserSchema() == true ) return;
		
		try {
			Statement stmt = con.createStatement();
			String sql = "CREATE TABLE USER( " +
					     " ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
					     " USERNAME 	  TEXT NOT NULL, " +
					     " FIRSTNAME      TEXT, " + 
					     " LASTNAME       TEXT, " +
					     " PASSWORD		  TEXT NOT NULL, " + 
					     " TOKEN          TEXT, " + 
					     " ROLE           TEXT)"; 
			stmt.executeUpdate( sql );
			stmt.close();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
	
	private boolean checkForAdminUser() {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT email FROM USER  WHERE username='admin00049717';" );
			if (rs.next()) {
				return true;
			}
			rs.close();
			stmt.close();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return false;
	}
	
	private void createAdminUser() {
		if( checkForAdminUser() ) return;
		
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(
	    	 "INSERT INTO USER(" + 
			 "username,firstname,lastname,password,role) VALUES" +
			 "(?,?,?,?,?)" );
			
			stmt.setString( 1, "admin00049717" );
			stmt.setString( 2, "Admin" );
			stmt.setString( 3, "User" );
			stmt.setString( 4, PasswordSecurity.generateHash( "secret" ) );
			stmt.setString( 5, "admin" );
			stmt.executeUpdate();
	    } catch ( SQLException e ) {
	    	e.printStackTrace();
	    } catch (NoSuchAlgorithmException e) {
	    	e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
	    finally {
	    	try {
				stmt.close();
			} catch ( SQLException e ) {
				e.printStackTrace();
			}
	    }
	}

}
