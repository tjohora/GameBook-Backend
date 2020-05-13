/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import DAOs.UserDAO;
import DTOs.User;
import java.text.SimpleDateFormat;

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

    private JSONObject convertUserToJson2(User u) {
        JSONObject jObj = new JSONObject();
        jObj.put("userId", u.getUserId());
        jObj.put("profileId", u.getProfileId());
        jObj.put("userName", u.getUsername());
        jObj.put("fname", u.getFname());
        jObj.put("lname", u.getLname());
        jObj.put("userType", u.getUserType());
        jObj.put("active", u.getActive());
        jObj.put("email", u.getEmail());
        jObj.put("address", u.getAddress());
        jObj.put("dob", u.getDob());
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
                if (!userName.isEmpty() && !password.isEmpty() && password.length() >= 6 && password.length() <= 30) {;
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
                if (!userName.isEmpty() && !password.isEmpty() && !email.isEmpty() && password.length() >= 6 && password.length() <= 30) {
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

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/getAllUsers")
    public String getAllUsers() {
        UserDAO userDB = new UserDAO("projectdb");

        System.out.println("GET called: getAllUsers");

        JSONArray array = new JSONArray();
        try {
            for (User u : userDB.getAllUsers()) {
                JSONObject obj = convertUserToJson2(u);
                array.add(obj);
            }
            JSONObject response = new JSONObject();
            response.put("Users", array);
        } catch (Exception e) {
            //e.printStackTrace();
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }

        return array.toJSONString();
    }

    @PUT
    @Path("/deleteUser/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean deleteUser(@PathParam("id") int userId, String content) {
        System.out.println("'DELETE' content = " + content);
        boolean flag = false;
        try {
            if (userId > 0) {
                UserDAO uDAO = new UserDAO("projectdb");
                flag = uDAO.deleteUser(userId);
            }
        } catch (Exception e) {
            System.out.println("Exception is Post DELETE : " + e.getMessage());
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

            String dateStr = ((String) obj.get("dob"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date myDob = sdf.parse(dateStr);
            java.sql.Date dob = new java.sql.Date(myDob.getTime());

            String fname = ((String) obj.get("fname"));
            String lname = ((String) obj.get("lname"));
            String address = ((String) obj.get("address"));

            int userId = (((Long) obj.get("userId")).intValue());

            if (fname != null && lname != null && address != null && dob != null) {
                if (!fname.isEmpty() && !lname.isEmpty() && !address.isEmpty()) {
                    UserDAO db = new UserDAO("projectdb");
                    System.out.println(dob);
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
