  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import DAOs.PostDAO;
import DTOs.Post;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * REST Web Service
 *
 * @author TJ
 */
@Path("post")
public class PostResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PostResource
     */
    public PostResource() {
    }

    /**
     * Retrieves representation of an instance of Rest.PostResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllPosts() {
        PostDAO postDB = new PostDAO();
        
        System.out.println("GET called: getAllPosts");
        
        JSONArray array = new JSONArray();
        try
        {
            for(Post p : postDB.getAllPosts())
            {
                JSONObject obj = convertPostToJson(p);
                array.add(obj);
            }
            JSONObject response = new JSONObject();
            response.put("Posts", array);
        }
        catch(Exception e)
        { 
            System.out.println(e);
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }

        return array.toJSONString();
        //return response.toJSONString();
    }

    /**
     * PUT method for updating or creating an instance of PostResource
     * @param content representation for the resource
     */
    @PUT
    @Path("/deletePost/{postId}")
    @Consumes(MediaType.TEXT_PLAIN)
    public boolean deletePost(@PathParam("loginDetails")int postId) {
        System.out.println("'DELETE' content = " + postId);
        boolean flag = false;
        try {
//            JSONParser parser = new JSONParser();
//            JSONObject obj = (JSONObject) parser.parse(postId);
//            int topicId = ((Long) obj.get("Id")).intValue();
            if (postId != -1) {
                System.out.println("Topic received in DELETE message = " + postId);
                PostDAO pDAO = new PostDAO();
                flag = pDAO.deleteAPost(postId);
            }
        } catch (Exception e) {
            System.out.println("Exception is Topic DELETE : " + e.getMessage());
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }
        return flag;
    }
    
//    @PUT
//    @Path("/updatePost/{postDetails")
//    @Consumes(MediaType.TEXT_PLAIN)
//    public void updatePost(String content) {
//    }
    
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean makeAPost(String content)
    {
        boolean flag = false;
        System.out.println("POST content = " + content);
        try
        {           
            Post p = convertJsonStringToPost(content);
            if(p != null){
                System.out.println("Post received in POST message = " + p.toString());
                PostDAO db = new PostDAO();
                flag = db.makeAPost(p);
            }
        }catch(Exception e){
            System.out.println("Exception is User POST : " + e.getMessage());
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }
        return flag;
    }

    public static JSONObject convertPostToJson(Post p) {
        JSONObject jObj = new JSONObject();
        jObj.put("postId", p.getPostId());
        jObj.put("userId", p.getUserId());
        jObj.put("title", p.getTitle());
        jObj.put("content" , p.getContent());
        jObj.put("postDate", p.getPostDate());
        return jObj;
    }

    private Post convertJsonStringToPost(String jsonString) {
        Post p = null;
        try
        {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject)parser.parse(jsonString);

            p = new Post();
            
            p.setUserId(((Long)obj.get("userId")).intValue());
            p.setTitle((String)obj.get(("title")));
            p.setContent((String)obj.get(("content")));
        }
            catch(ParseException exp)
        {
            System.out.println(exp);
            p = null;
        }
        return p;
    }
}
