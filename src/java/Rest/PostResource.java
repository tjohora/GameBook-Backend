/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import DAOs.PostDAO;
import DTOs.Post;
import java.sql.Connection;
import java.util.List;
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

    public static JSONObject convertPostToJson(Post p) {
        JSONObject jObj = new JSONObject();
        jObj.put("postId", p.getPostId());
        jObj.put("userId", p.getUserId());
        jObj.put("userName", p.getUserName());
        jObj.put("postHeader", p.getPostHeader());
        jObj.put("postContent", p.getPostContent());
        jObj.put("postDate", p.getPostDate());
        jObj.put("media", p.getMedia());
        jObj.put("active", p.getActive());
        jObj.put("active", p.getFlagged());

        return jObj;
    }

//    private Post convertJsonStringToPost(String jsonString) {
//        Post p = null;
//        try
//        {
//            JSONParser parser = new JSONParser();
//            JSONObject obj = (JSONObject)parser.parse(jsonString);
//
//            p = new Post();
//            
//            p.setUserId(((Long)obj.get("userId")).intValue());
//        }
//            catch(ParseException exp)
//        {
//            System.out.println(exp);
//            p = null;
//        }
//        return p;
//    }
    /**
     * Retrieves representation of an instance of Rest.PostResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllPosts() {
        PostDAO postDB = new PostDAO("projectdb");

        System.out.println("GET called: getAllPosts");

        JSONArray array = new JSONArray();
        try {
            for (Post p : postDB.getAllPosts()) {
                JSONObject obj = convertPostToJson(p);
                array.add(obj);
            }
            JSONObject response = new JSONObject();
            response.put("Posts", array);
        } catch (Exception e) {
            //e.printStackTrace();
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }

        return array.toJSONString();
        //return response.toJSONString();
    }

    @GET
    @Path("/onePost/{postId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getOnePost(@PathParam("postId") int postId) {

        PostDAO postDB = new PostDAO("projectdb");

        System.out.println("GET called: getOnePosts");
        JSONArray array = new JSONArray();
        try {
            for (Post p : postDB.getOnePost(postId)) {
                JSONObject obj = convertPostToJson(p);
                array.add(obj);
            }
            JSONObject response = new JSONObject();
            response.put("Posts", array);
        } catch (Exception e) {
            //e.printStackTrace();
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }

        return array.toJSONString();
    }

    @GET
    @Path("/userPosts/{userId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getPostsByUser(@PathParam("userId") int userId) {
        PostDAO postDB = new PostDAO("projectdb");

        System.out.println("GET called: getPostsByUser");
        JSONArray array = new JSONArray();
        try {
            List<Post> posts = postDB.getPostsByUser(userId);
            if (posts.isEmpty()) {
                return "{\"message\":\"No posts found\"}";
            } else {
                for (Post p : posts) {
                    JSONObject obj = convertPostToJson(p);
                    array.add(obj);
                }
            }
            JSONObject response = new JSONObject();
            response.put("Posts", array);
        } catch (Exception e) {
            System.out.println(e);
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }

        return array.toJSONString();
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean makePost(String content) {
        boolean flag = false;
        System.out.println("POST content = " + content);
        try {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(content);
            int userId = ((Long) obj.get("userId")).intValue();
            int media = ((Long) obj.get("media")).intValue();
            String postHeader = (String) obj.get("postHeader");
            String postContent = (String) obj.get("postContent");
            if (postHeader != null && postContent != null) {
                if (!postHeader.isEmpty()) {
                    PostDAO db = new PostDAO("projectdb");
                    flag = db.makePost(userId, postHeader, postContent, media);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception is User POST : " + e.getMessage());
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }
        return flag;
    }

    @PUT
    @Path("/deletePost/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean deletePost(@PathParam("id") int postId, String content) {
        System.out.println("'DELETE' content = " + content);
        boolean flag = false;
        try {
            PostDAO pDAO = new PostDAO("projectdb");
            flag = pDAO.deletePost(postId);
        } catch (Exception e) {
            System.out.println("Exception is Post DELETE : " + e.getMessage());
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }
        return flag;
    }

    @PUT
    @Path("/updatePost/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean updatePost(@PathParam("id") int postId, String content) {
        System.out.println("'UPDATE' content = " + content);
        boolean flag = false;
        try {
            PostDAO pDAO = new PostDAO("projectdb");
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(content);
            String postHeader = (String) obj.get("postHeader");
            String postContent = (String) obj.get("postContent");
            flag = pDAO.updatePost(postId, postHeader, postContent);
        } catch (Exception e) {
            System.out.println("Exception is Post DELETE : " + e.getMessage());
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }
        return flag;
    }
    
    @GET
    @Path("/getPostBySearch/{searchItem}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getPostBySearch(@PathParam("searchItem") String searchItem) {
        PostDAO postDB = new PostDAO("projectdb");

        System.out.println("GET called: getPostBySearch");
        System.out.println(searchItem);
        JSONArray array = new JSONArray();
        try {
            List<Post> posts = postDB.getPostsBySearch(searchItem);
            if (posts.isEmpty()) {
                return "{\"message\":\"No posts found\"}";
            } else {
                for (Post p : posts) {
                    JSONObject obj = convertPostToJson(p);
                    array.add(obj);
                }
            }
            JSONObject response = new JSONObject();
            response.put("Posts", array);
        } catch (Exception e) {
            System.out.println(e);
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }

        return array.toJSONString();
    }
}
