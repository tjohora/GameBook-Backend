/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import DAOs.FlaggedCommentDAO;
import DAOs.FlaggedPostDAO;
import DTOs.FlaggedComment;
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
@Path("FlaggedComment")
public class FlaggedCommentResource {

    @Context
    private UriInfo context;

    public FlaggedCommentResource() {
    }


    
@GET
@Path("/releaseComment/{id}")
@Produces("text/plain")
public String removeId(@PathParam ("id") int id) {
    FlaggedCommentDAO fDAO = new FlaggedCommentDAO("projectdb");
    return  fDAO.releaseComment(id);
}

    @GET
    @Path("/getAllFlaggedComments")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllFlaggedComments() {
        FlaggedCommentDAO fDAO = new FlaggedCommentDAO("projectdb");

        JSONArray array = new JSONArray();
        try {
            for (FlaggedComment fc : fDAO.getAllFlaggedComments()) {
                JSONObject obj = convertFlaggedCToJson(fc);
                array.add(obj);
            }
            JSONObject response = new JSONObject();
            response.put("FlaggedComment", array);
        } catch (Exception e) {
            //e.printStackTrace();
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }

        return array.toJSONString();
        //return response.toJSONString();
    }

    private JSONObject convertFlaggedCToJson(FlaggedComment fc) {
        JSONObject jObj = new JSONObject();
        jObj.put("commentId", fc.getCommentID());
        jObj.put("userId", fc.getUserID());
        jObj.put("flagComment", fc.getFlagComment());

        return jObj;

    }
    
       @POST
    @Path("/reportComment")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean reportComment(String content) {
        boolean flag = false;
        System.out.println("Comment called: updateComment");
        try {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(content);
            int userId = ((Long) obj.get("userID")).intValue();
            int commentId = ((Long) obj.get("commentID")).intValue();
            int flagComment = ((Long) obj.get("flagComment")).intValue();
            FlaggedCommentDAO fDAO = new FlaggedCommentDAO("projectdb");
            flag = fDAO.reportComment(commentId, userId, flagComment);
        } catch (Exception e) {
            System.out.println("Exception is FLAGGING POST : " + e.getMessage());
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }
        return flag;
    }

    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void putText(String content) {
    }
}
