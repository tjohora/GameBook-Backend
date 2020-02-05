/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import DAOs.UserDAO;
import DTOs.User;
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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
    
    private User convertJsonStringToUser(String jsonString)
    {
        User u = null;
        try
        {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject)parser.parse(jsonString);
            u = new User();
            u.setUsername((String)obj.get("username"));
            u.setPassword((String)obj.get("password"));
            u.setEmail((String)obj.get("email"));
        }catch(ParseException exp)
        {
            System.out.println(exp);
            u = null;
        }
        return u;
    }
    
    private JSONObject convertUserToJson(User u)
    {
        JSONObject jObj = new JSONObject();
        jObj.put("userId", u.getUserId());
        jObj.put("profileId", u.getProfileId());
        jObj.put("userType", u.getUserType());
        return jObj;
    }

    /**
     * Retrieves representation of an instance of Rest.UserResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/login/{loginDetails}")
    @Produces(MediaType.TEXT_PLAIN)
    public String login(@PathParam("loginDetails")String content) {
        try
        {
            System.out.println("GET content = " + content);
            UserDAO uDAO = new UserDAO();
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject)parser.parse(content); 
            String username = (String)obj.get("username");
            String password = (String)obj.get("password");
            System.out.println("User received in Get message = " + username + ", " + password);
            User u = uDAO.login(username, password);
            System.out.println(u.toString());
            if (u.getActive() == 0){
                return "Incorrect username or password";
            }
            else
            {
                return convertUserToJson(u).toString();
            }
        }catch(Exception e)
        {
            System.out.println("Exception is User GET : " + e.getMessage());
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }
    }


//    @PUT
//    @Consumes(MediaType.TEXT_PLAIN)
//    public void putText(String content) {
//    }
    
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean register(String content)
    {
        boolean flag = false;
        System.out.println("POST content = " + content);
        try
        {           
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject)parser.parse(content);
            
            String userName = ((String)obj.get("username"));
            String password = ((String)obj.get("password"));
            String email = ((String)obj.get("email"));
            if(!userName.isEmpty()){
                UserDAO db = new UserDAO();
                flag = db.register(userName, password, email);
            }
        }catch(Exception e){
            System.out.println("Exception is User POST : " + e.getMessage());
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }
        return flag;
    }
    
//    @POST
//    @Path("/login")
//    @Consumes(MediaType.TEXT_PLAIN)
}
