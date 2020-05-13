/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import DAOs.FlaggedPostDAO;
import DTOs.FlaggedPost;
import java.util.List;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * REST Web Service
 *
 * @author andre
 */
@Path("FlaggedPost")
public class FlaggedPostResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FlaggedPostResource
     */
    public FlaggedPostResource() {
    }

      private static final String SUCCESS_RESULT="<result>success</result>";
    
    
    @GET
    @Path("/getAllFlaggedPosts")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllFlaggedPosts() {
        FlaggedPostDAO fDAO = new FlaggedPostDAO("projectdb");

        JSONArray array = new JSONArray();
        try {
            for (FlaggedPost fp : fDAO.getAllFlaggedPosts()) {
                JSONObject obj = convertFlaggedPToJson(fp);
                array.add(obj);
            }
            JSONObject response = new JSONObject();
            response.put("FlaggedPost", array);
        } catch (Exception e) {
            //e.printStackTrace();
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }

        return array.toJSONString();
        //return response.toJSONString();
    }
    
    @GET
    @Path("/getAllFlaggedPostsByPostId/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllFlaggedPostsByPostId(@PathParam("id") int postId) {
        FlaggedPostDAO fDAO = new FlaggedPostDAO("projectdb");

        JSONArray array = new JSONArray();
        try {
            List<FlaggedPost> flaggedPosts = fDAO.getAllFlaggedPostsByPostID(postId);
            if(flaggedPosts.isEmpty()){
                return "{\"message\":\"No posts found\"}";
            }
            else{
            for (FlaggedPost fp : fDAO.getAllFlaggedPostsByPostID(postId)) {
                JSONObject obj = convertFlaggedPToJson(fp);
                array.add(obj);
            }
            }
            JSONObject response = new JSONObject();
            response.put("FlaggedPost", array);
        } catch (Exception e) {
            //e.printStackTrace();
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }

        return array.toJSONString();
        //return response.toJSONString();
    }
    
    
    
        private JSONObject convertFlaggedPToJson(FlaggedPost fp ) {
        JSONObject jObj = new JSONObject();       
        jObj.put("postId", fp.getPostID());
         jObj.put("userId", fp.getUserID());
        jObj.put("flagPost", fp.getFlagPost());
        
        return jObj;
    }
        
   
    @POST
    @Path("/reportPost")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean reportRating(String content) {
        boolean flag = false;
        System.out.println("POST called: updateRating");
        try {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(content);
            int userId = ((Long) obj.get("userID")).intValue();
            int postId = ((Long) obj.get("postID")).intValue();
            int flagPost = ((Long) obj.get("flagPost")).intValue();
            FlaggedPostDAO fDAO = new FlaggedPostDAO("projectdb");
            flag = fDAO.reportPost(postId, userId, flagPost);
        } catch (Exception e) {
            System.out.println("Exception is FLAGGING POST : " + e.getMessage());
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }
        return flag;
    }
    
    @GET
@Path("/releasePost/{id}")
@Produces("text/plain")
public String removeId(@PathParam ("id") int id) {
    FlaggedPostDAO fDAO = new FlaggedPostDAO("projectdb");
    return  fDAO.releasePost(id);
}
        
       
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}