/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import DAOs.UserDAO;
import DTOs.User;
import java.sql.Date;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.json.simple.*;
import org.json.simple.parser.*;

/**
 * REST Web Service
 *
 * @author TJ
 */
@Path("user")
public class UserResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public UserResource() {
    }

    private User convertJsonStringToUser(String jsonString) {
        User u = null;
        try {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonString);
            u = new User();
            u.setUsername((String) obj.get("username"));
            u.setEmail((String) obj.get("email"));
        } catch (ParseException exp) {
            System.out.println(exp);
            u = null;
        }
        return u;
    }

    private JSONObject convertUserToJson(User u) {
        JSONObject jObj = new JSONObject();
        jObj.put("userId", u.getUserId());
        jObj.put("profileId", u.getProfileId());
        jObj.put("userType", u.getUserType());
        jObj.put("userName", u.getUsername());
        jObj.put("error", "");
        return jObj;
    }
    
    

    /**
     * Retrieves representation of an instance of Rest.UserResource
     *
     * @return an instance of java.lang.String
     */
    @POST
    @Path("/login")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String login(String content) {
        try {
            System.out.println("GET content = " + content);
            UserDAO uDAO = new UserDAO("projectdb");
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(content);
            String userName = (String) obj.get("userName");
            String password = (String) obj.get("password");
            System.out.println(userName + " " + password);
            if (userName != null && password != null) {
                if (!userName.isEmpty() && !password.isEmpty() && password.length() >= 6 && password.length() <= 16) {;
                    User u = uDAO.login(userName, password);
                    System.out.println(u.toString());
                    if (u.getUserType() == 0) {
                        JSONObject jObj = new JSONObject();
                        jObj.put("error", "Incorrect username or password");
                        return jObj.toString();
                    } else {
                        return convertUserToJson(u).toString();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Exception is User GET : " + e.getMessage());
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }
        return "Server error. Try again later";
    }

    @PUT
    //@Consumes(MediaType.TEXT_PLAIN)
    public void putText(String content) {
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean register(String content) {
        boolean flag = false;
        System.out.println("POST content = " + content);
        try {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(content);

            String userName = ((String) obj.get("userName"));
            String password = ((String) obj.get("password"));
            String email = ((String) obj.get("email"));

            if (userName != null && password != null && email != null) {
                if (!userName.isEmpty() && !password.isEmpty() && !email.isEmpty() && password.length() >= 6 && password.length() <= 16) {
                    UserDAO db = new UserDAO("projectdb");
                    flag = db.register(userName, password, email);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception is User POST : " + e.getMessage());
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }
        return flag;
    }
    
    @POST
    @Path("/editUser")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean editUserDetails(String content) {
        boolean flag = false;
        System.out.println("POST content = " + content);
        try {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(content);

            String fname = ((String) obj.get("fname"));
            String lname = ((String) obj.get("lname"));
            String address = ((String) obj.get("address"));
            Date dob = ((Date) obj.get("dob"));
            int userId = (((Long) obj.get("userId")).intValue());

            if (fname != null && lname != null && address != null && dob != null) {
                if (!fname.isEmpty() && !lname.isEmpty() && !address.isEmpty() && !dob.equals(null)) {
                    UserDAO db = new UserDAO("projectdb");
                    flag = db.editUser(fname, lname, address, dob, userId);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception is User POST : " + e.getMessage());
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }
        return flag;
    }
    
}
