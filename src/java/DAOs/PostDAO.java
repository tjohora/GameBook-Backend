/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Post;
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
public class PostDAO extends DAO implements PostDAOInterface {

    @Override
    public List<Post> getAllPosts() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Post> posts = new ArrayList();
        
        try{
            con = getConnection();

            String query = "Select * from post where status = 1"; 
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(); 
            
            while(rs.next())
            {
                Post p = new Post(rs.getInt("postId"), rs.getInt("userId"), rs.getString("title"), rs.getString("content"), rs.getString("postDate"));
                posts.add(p);
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the getAllPosts() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getAllPosts() method: " + e.getMessage());
            }
        }
        
        return posts;
    }

    @Override
    public boolean makeAPost(Post p) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;

        try {
            con = getConnection();

            ps = con.prepareStatement("insert into post (postId, userId, title, content, postDate) values (null, ?, ?, ?, NOW())");
            ps.setInt(1, p.getUserId());
            ps.setString(2, p.getTitle());
            ps.setString(3, p.getContent());
            ps.executeUpdate();
            System.out.println("Post has been added.");
            flag = true;
            
        } catch (SQLException e) {
            System.out.println("Exception occured in the makeAPost() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the makeAPost() method: " + e.getMessage());
            }
        }
        return flag;
    }

    @Override
    public boolean deleteAPost(int postId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;       
        try
        {
            con = getConnection();         
            ps = con.prepareStatement("UPDATE post SET status = 0 WHERE postId = ?");
            ps.setInt(1, postId);
            ps.executeUpdate();
            System.out.println("Post has been deleted.");
            flag = true;
        }catch (SQLException e) {
            System.out.println("Exception occured in the deletePost() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the deletePost() method: " + e.getMessage());
            }
        }
        return flag;
    }

    @Override
    public boolean updateAPost(int postId, String title, String content) 
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;       
        try
        {
            con = getConnection();       
            ps = con.prepareStatement("UPDATE post SET title = ?, content = ? WHERE = ?");
            ps.setString(1, title);
            ps.setString(2, content);
            ps.setInt(3, postId);
            ps.executeUpdate();
            System.out.println("Post has been updated.");
            flag = true;
        }catch (SQLException e) {
            System.out.println("Exception occured in the updateAPost() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the updateAPost() method: " + e.getMessage());
            }
        }
        return flag;
    }

    @Override
    public Post getOnePost() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Post> getPostsByUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
