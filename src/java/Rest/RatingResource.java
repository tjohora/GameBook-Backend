/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import DAOs.RatingDAO;
import DTOs.Rating;
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
@Path("Rating")
public class RatingResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RatingResource
     */
    public RatingResource() {
    }

    /**
     * Retrieves representation of an instance of Rest.RatingResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/getAllPostRatings")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllPostRatings() {
        RatingDAO rDAO = new RatingDAO("projectdb");

        System.out.println("GET called: getAllPostRatings");

        JSONArray array = new JSONArray();
        try {
            for (Rating r : rDAO.getAllPostRatings()) {
                JSONObject obj = convertRatingToJson(r);
                array.add(obj);
            }
            JSONObject response = new JSONObject();
            response.put("Ratings", array);
        } catch (Exception e) {
            //e.printStackTrace();
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }

        return array.toJSONString();
        //return response.toJSONString();
    }

    /**
     * PUT method for updating or creating an instance of RatingResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void putText(String content) {
    }
    
    @POST
    @Path("/updateRating")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean updateRating(String content) {
        boolean flag = false;
        System.out.println("POST called: updateRating");
        try {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(content);
            int userId = ((Long) obj.get("userId")).intValue();
            int postId = ((Long) obj.get("postId")).intValue();
            int selectedRating = ((Long) obj.get("selectedRating")).intValue();
            RatingDAO rDAO = new RatingDAO("projectdb");
            flag = rDAO.updateRating(postId, userId, selectedRating);
        } catch (Exception e) {
            System.out.println("Exception is Rating POST : " + e.getMessage());
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }
        return flag;
    }

    @GET
    @Path("/getRatingByPostID/{postId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getRatingByPostID(@PathParam("postId") int postId) {
        RatingDAO rDAO = new RatingDAO("projectdb");

        System.out.println("GET called: getRatingByPostID");

        JSONArray array = new JSONArray();
        try {
            for (Rating r : rDAO.getRatingByPostID(postId)) {
                JSONObject obj = convertRatingToJson(r);
                array.add(obj);
            }
            JSONObject response = new JSONObject();
            response.put("Ratings", array);
        } catch (Exception e) {
            //e.printStackTrace();
            // This exception sends error message to client
            throw new javax.ws.rs.ServerErrorException(e.getMessage(), 500);
        }

        return array.toJSONString();
        //return response.toJSONString();
    }
        

    private JSONObject convertRatingToJson(Rating r) {
        JSONObject jObj = new JSONObject();
        jObj.put("postId", r.getPostID());
        jObj.put("selectedRating", r.getSelectedRating());
        
        return jObj;
    }
}
