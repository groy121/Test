package com.cbp.services;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cbp.dao.UserDAO;
import com.dao.model.User;


@Path("/users")
public class UserService {
	// URI:
    // /contextPath/servletPath/users
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<User> getUsers_JSON() {
        List<User> listOfUsers = UserDAO.getAllUsers();
        return listOfUsers;
    }
 
    // URI:
    // /contextPath/servletPath/users/{userNo}
    @GET
    @Path("/{userNo}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public User getUser(@PathParam("userNo") String userNo) {
        return UserDAO.getUser(userNo);
    }
 
    // URI:
    // /contextPath/servletPath/users
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public User addUser(User user) {
        return UserDAO.addUser(user);
    }
 
    // URI:
    // /contextPath/servletPath/users
    @PUT
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public User updateUser(User user) {
        return UserDAO.updateUser(user);
    }
 
    @DELETE
    @Path("/{userNo}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deleteUser(@PathParam("userNo") String userNo) {
        UserDAO.deleteUser(userNo);
    }
}
