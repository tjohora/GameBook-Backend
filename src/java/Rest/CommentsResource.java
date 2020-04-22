/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import DAOs.CommentDAO;
import DAOs.PostDAO;
import DTOs.Comment;
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

/**
 * REST Web Service
 *
 * @author TJ
 */
@Path("comments")
public class CommentsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CommentsResource
     */
    public CommentsResource() {
    }
    
    private JSONObject convertCommentToJson(Comment c) {
        JSONObject jObj = new JSONObject();
        jObj.put("userId", c.getUserId());
        jObj.put("postId", c.getPostID());
        jObj.put("commentID", c.getCommentID());
        jObj.put("userName", c.getUserName());
        jObj.put("content", c.getContent());
        jObj.put("commentDate", c.getCommentDate());
        jObj.put("active", c.getActive());

        return jObj;    
    }

    /**
     * Retrieves representation of an instance of Rest.CommentsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllComments() {
        CommentDAO commentDB = new CommentDAO("projectdb");

        System.out.println("GET called: getAllComments");

        JSONArray array = new JSONArray();
        try {
            for (Comment c : commentDB.getAllComments()) {
                JSONObject obj = convertCommentToJson(c);
                array.add(obj);
            }
            JSONObject response = new JSONObject();
            response.put("Comments", array);
        } catch (Exception e) {
            //e.printStackTrace();
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }

        return array.toJSONString();
    }
    @GET
    @Path("/commentsOfUser/{userId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCommentsOfUser(@PathParam("userId") int userId) {
        CommentDAO commentDB = new CommentDAO("projectdb");

        System.out.println("GET called: getCommentsOfUser");
        JSONArray array = new JSONArray();
        try{
            List<Comment> comments = commentDB.getCommentsOfUser(userId);
            if (comments.isEmpty()) 
            {               
                return "{\"message\":\"No comments found.\"}";
            } 
            else 
            {
                for (Comment c : comments) 
                {
                    JSONObject obj = convertCommentToJson(c);
                    array.add(obj);
                }
            }
            JSONObject response = new JSONObject();
            response.put("Comments", array);
        } catch (Exception e) {
            System.out.println(e);
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }

        return array.toJSONString();
    }
    
    @GET
    @Path("/commentsOfPost/{postId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCommentsOfPost(@PathParam("postId") int postId) {
        CommentDAO commentDB = new CommentDAO("projectdb");

        System.out.println("GET called: GetCommentsOfPost");
        JSONArray array = new JSONArray();
        try{
            List<Comment> comments = commentDB.getCommentsOfPost(postId);
            if (comments.isEmpty()) 
            {               
                return "{\"message\":\"No Comments found\"}";
            } 
            else 
            {
                for (Comment c : comments) 
                {
                    JSONObject obj = convertCommentToJson(c);
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
    public boolean makeComment(String content) {
        boolean flag = false;
        System.out.println("POST content = " + content);
        try {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(content);
            int userId = ((Long) obj.get("userId")).intValue();
            int postID = ((Long) obj.get("postId")).intValue();
            String commentContent = (String) obj.get("content");
            if (commentContent != null) {
                if (!commentContent.isEmpty()) {
                    
                    CommentDAO db = new CommentDAO("projectdb");
                    flag = db.makeComment(userId, postID, commentContent);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception is Comment POST : " + e.getMessage());
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }
        return flag;
    }

    @PUT
    @Path("/deleteComment/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean deleteComment(@PathParam("id") int commentID, String content) {
        System.out.println("'DELETE' content = " + commentID);
        boolean flag = false;
        try {
                CommentDAO cDAO = new CommentDAO("projectdb");
                flag = cDAO.deleteComment(commentID);
        } catch (Exception e) {
            System.out.println("Exception is Post DELETE : " + e.getMessage());
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }
        return flag;
    }
    
    @PUT
    @Path("/updateComment/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
    public boolean updateComment(@PathParam("id") int commentID, String content) {
        System.out.println("'UPDATE' content = " + content);
        boolean flag = false;
        try {
            CommentDAO cDAO = new CommentDAO("projectdb");
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(content);
            String commentContent = (String) obj.get("content");
            flag = cDAO.updateComment(commentID, commentContent);
        } catch (Exception e) {
            System.out.println("Exception in Comment UPDATE : " + e.getMessage());
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }
        return flag;
    }
    
    @GET
    @Path("/oneComment/{commentId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getOneComment(@PathParam("commentId") int commentId) {

        CommentDAO cDAO = new CommentDAO("projectdb");

        System.out.println("GET called: getOneComment");
        JSONArray array = new JSONArray();
        try {
            for (Comment c : cDAO.getOneComment(commentId)) {
                JSONObject obj = convertCommentToJson(c);
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
}
