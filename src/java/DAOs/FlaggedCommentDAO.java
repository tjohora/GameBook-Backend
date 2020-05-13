package DAOs;

import DTOs.FlaggedComment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlaggedCommentDAO extends DAO {

    public FlaggedCommentDAO(Connection conn) {
        super(conn);
    }

    public FlaggedCommentDAO(String databaseName) {
        super(databaseName);
    }

    public boolean reportComment(int commentId, int userId, int flagComment) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;

        try {
            con = getConnection();

            ps = con.prepareStatement("INSERT INTO flaggedcomment (commentID, userID, flagComment) VALUES (?,?,?) ON DUPLICATE KEY UPDATE flagComment = ?");
            ps.setInt(1, commentId);
            ps.setInt(2, userId);
            ps.setInt(3, flagComment);
            ps.setInt(4, flagComment);
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

    public List<FlaggedComment> getAllFlaggedComments() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<FlaggedComment> flaggedComments = new ArrayList();

        try {
            con = getConnection();

            String query = "SELECT commentID, userID, SUM(flagComment) AS numFlags FROM flaggedcomment GROUP BY commentID ORDER BY numFlags DESC";
            //String query = "SELECT fp.postID, fp.userID, SUM(fp.flagPost) AS numFlags, p.postHeader,p.postContent FROM flaggedpost fp inner join posts p on fp.postID = p.postId GROUP BY postID ORDER BY flagPost";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                FlaggedComment fc = new FlaggedComment(rs.getInt("commentID"), rs.getInt("userID"), rs.getInt("numFlags"));
                flaggedComments.add(fc);
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
                System.out.println("Exception occured in the method: " + e.getMessage());
            }
        }
        return flaggedComments;
    }

    public String releaseComment(int id) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        con = getConnection();
        String query = "Delete from flaggedcomment where commentID = ?";

        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.execute();

        } catch (Exception e) {
        } //finally {
        //fecharConexao(conexao, psmt, rs);
        //}

        return "id:" + id + " successfully deleted";
    }
}
