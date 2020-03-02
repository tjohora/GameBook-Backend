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

            String query = "Select * from posts where active = 1"; 
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(); 
            
            while(rs.next())
            {
                Post p = new Post(rs.getInt("postId"), rs.getInt("userId"), rs.getString("postHeader"), rs.getString("postContent"), rs.getString("postDate"), rs.getString("media"), rs.getInt("active"));
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
    public List<Post> getOnePost(int postId) 
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Post> post = new ArrayList();
        
        try{
            con = getConnection();

            String query = "Select * from post where active = 1 && postId = ?";
            ps.setInt(1, postId);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(); 
            
            while(rs.next())
            {
                Post p = new Post(rs.getInt("postId"), rs.getInt("userId"), rs.getString("postHeader"), rs.getString("postContent"), rs.getString("postDate"), rs.getString("media"), rs.getInt("active"));
                post.add(p);
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
        
        return post;
    
    }

    @Override
    public List<Post> getPostsByUser(int userId) 
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Post> posts = new ArrayList();
        
        try{
            con = getConnection();
            System.out.println("Test");
            String query = "Select * from posts where active = 1 && userId = ?";
            ps.setInt(1, userId);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            
            
            while(rs.next())
            {
                Post p = new Post(rs.getInt("postId"), rs.getInt("userId"), rs.getString("postHeader"), rs.getString("postContent"), rs.getString("postDate"), rs.getString("media"), rs.getInt("active"));
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
    public boolean makeAPost(int userId, String postHeader, String postContent) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;

        try {
            con = getConnection();

            ps = con.prepareStatement("insert into post (postID, userId, postHeader, postContent, postDate, media, active) values (null, ?, ?, ?, NOW(), ?, 1)");
            ps.setInt(1, userId);
            ps.setString(2, postHeader);
            ps.setString(3, postContent);
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
            ps = con.prepareStatement("UPDATE post SET status = 0, postHeader = '[deleted]', postContent = '[deleted]' WHERE postId = ?");
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
            ps = con.prepareStatement("UPDATE post SET content = ? WHERE postId = ?");
            ps.setString(1, content);
            ps.setInt(2, postId);
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
}
