/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Rating;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TJ
 */
public class RatingDAO extends DAO implements RatingDAOInterface { 
    
    public RatingDAO(Connection conn) {
        super(conn);
    }
    
    public RatingDAO(String databaseName)
    {
        super(databaseName);
    }

    @Override
    public boolean updateRating(int postId, int userId, int selectedRating) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;
        
        try {
            con = getConnection();

            ps = con.prepareStatement("INSERT INTO rating (postID, userID, selectedRating) VALUES (?,?,?) ON DUPLICATE KEY UPDATE selectedRating = ?");
            ps.setInt(1, postId);
            ps.setInt(2, userId);
            ps.setInt(3, selectedRating);
            ps.setInt(4, selectedRating);
            ps.executeUpdate();
            System.out.println("Rating has been added/updated.");
            flag = true;
            
        } catch (SQLException e) {
            System.out.println("Exception occured in the updateRating() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the updateRating() method: " + e.getMessage());
            }
        }
        return flag;
    }

    @Override
    public List<Rating> getRatingByPostID(int postId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Rating> ratings = new ArrayList();
        
        try{
            con = getConnection();
 

            String query = "SELECT postId, SUM(selectedRating) AS totalRating FROM rating WHERE postId = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, postId);
            rs = ps.executeQuery(); 
            
            while(rs.next())
            {
                Rating p = new Rating(rs.getInt("postId"), rs.getInt("totalRating"));
                ratings.add(p);
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the getRatingByPostID() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getRatingByPostID() method: " + e.getMessage());
            }
        }
        
        return ratings;
    }

    @Override
    public List<Rating> getRatingOfUser(int userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rating> getAllPostRatings() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Rating> ratings = new ArrayList();
        
        try{
            con = getConnection();
 

            String query = "SELECT postId, SUM(selectedRating) AS totalRating FROM rating GROUP BY postId";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(); 
            
            while(rs.next())
            {
                Rating r = new Rating(rs.getInt("postId"), rs.getInt("totalRating"));
                ratings.add(r);
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the getAllPostRatings() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the getAllPostRatings() method: " + e.getMessage());
            }
        }
        return ratings;
    }
    
}
