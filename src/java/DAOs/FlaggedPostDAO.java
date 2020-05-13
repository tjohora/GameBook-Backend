package DAOs;

import DTOs.FlaggedPost;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlaggedPostDAO extends DAO implements FlaggedPostDAOInterface {

    public FlaggedPostDAO(Connection conn) {
        super(conn);
    }

    public FlaggedPostDAO(String databaseName) {
        super(databaseName);
    }

    public boolean reportPost(int postId, int userId, int flagPost) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;

        try {
            con = getConnection();

            ps = con.prepareStatement("INSERT INTO flaggedpost (postID, userID, flagPost) VALUES (?,?,?) ON DUPLICATE KEY UPDATE flagPost = ?");
            ps.setInt(1, postId);
            ps.setInt(2, userId);
            ps.setInt(3, flagPost);
            ps.setInt(4, flagPost);
            ps.executeUpdate();
            System.out.println("FLAGGED!");
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

    public List<FlaggedPost> getAllFlaggedPosts() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<FlaggedPost> flaggedPosts = new ArrayList();

        try {
            con = getConnection();

           String query = "SELECT postID, userID, SUM(flagPost) AS numFlags FROM flaggedpost GROUP BY postID ORDER BY numFlags DESC";
            //String query = "SELECT fp.postID, fp.userID, SUM(fp.flagPost) AS numFlags, p.postHeader,p.postContent FROM flaggedpost fp inner join posts p on fp.postID = p.postId GROUP BY postID ORDER BY flagPost";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                FlaggedPost fp = new FlaggedPost(rs.getInt("postID"), rs.getInt("userID"), rs.getInt("numFlags"));
                flaggedPosts.add(fp);
            }
        } catch (SQLException e) {
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
        return flaggedPosts;
    }

    public List<FlaggedPost> getAllFlaggedPostsByPostID(int postId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<FlaggedPost> flaggedPosts = new ArrayList();

        try {
            con = getConnection();

            String query = "SELECT postID, SUM(flagPost) AS numFlags FROM flaggedpost WHERE postId = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, postId);
            rs = ps.executeQuery();

            while (rs.next()) {
                FlaggedPost fp = new FlaggedPost(rs.getInt("postId"), rs.getInt("numFlags"));
                flaggedPosts.add(fp);
            }
        } catch (SQLException e) {
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
        return flaggedPosts;
    }


  public String releasePost (int id) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        con = getConnection();
    String query = "Delete from flaggedpost where postID = ?";

    try {
        ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.execute();

    } catch (Exception e) {
    } //finally {
        //fecharConexao(conexao, psmt, rs);
    //}

    return "id:"+ id + " successfully deleted";
    }
 
}

