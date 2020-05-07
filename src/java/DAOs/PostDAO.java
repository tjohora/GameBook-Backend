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

    public PostDAO(Connection conn) {
        super(conn);
    }
    
    public PostDAO(String databaseName)
    {
        super(databaseName);
    }

    @Override
    public List<Post> getAllPosts() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Post> posts = new ArrayList();
        
        try{
            con = getConnection();
            String query = "Select u.userName, p.postID, p.userID, p.postHeader, p.postContent, p.postDate, p.media, p.active, p.flagged from posts p inner join users u on u.userId = p.userId where active = 1 order by postId desc"; 
            ps = con.prepareStatement(query);
            rs = ps.executeQuery(); 
            
            while(rs.next())
            {
                Post p = new Post(rs.getInt("postId"), rs.getInt("userId"), rs.getString("userName"), rs.getString("postHeader"), rs.getString("postContent"), rs.getString("postDate"), rs.getString("media"), rs.getInt("active"), rs.getInt("flagged"));
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
 

            String query = "Select u.userName, p.postID, p.userID, p.postHeader, p.postContent, p.postDate, p.media, p.active, p.flagged from posts p inner join users u on u.userId = p.userId where active = 1 && postId = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, postId);
            rs = ps.executeQuery(); 
            
            while(rs.next())
            {
                Post p = new Post(rs.getInt("postId"), rs.getInt("userId"), rs.getString("userName"), rs.getString("postHeader"), rs.getString("postContent"), rs.getString("postDate"), rs.getString("media"), rs.getInt("active"), rs.getInt("flagged"));
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
            String query = "Select u.userName, p.postID, p.userID, p.postHeader, p.postContent, p.postDate, p.media, p.active from posts p inner join users u on u.userId = p.userId where active = 1 && userId = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                Post p = new Post(rs.getInt("postId"), rs.getInt("userId"), rs.getString("userName"), rs.getString("postHeader"), rs.getString("postContent"), rs.getString("postDate"), rs.getString("media"), rs.getInt("active"), rs.getInt("flagged"));
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
    public boolean makePost(int userId, String postHeader, String postContent, int media) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;

        try {
            con = getConnection();

            ps = con.prepareStatement("insert into posts (postID, userId, postHeader, postContent, postDate, media, active) values (null, ?, ?, ?, NOW(), ?, 1)");
            ps.setInt(1, userId);
            ps.setString(2, postHeader);
            ps.setString(3, postContent);
            ps.setInt(4, media);
            ps.executeUpdate();
            System.out.println("Post has been added.");
            flag = true;
            
        } catch (SQLException e) {
            System.out.println("Exception occured in the makePost() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the makePost() method: " + e.getMessage());
            }
        }
        return flag;
    }

    @Override
    public boolean deletePost(int postId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;       
        try
        {
            con = getConnection();         
            ps = con.prepareStatement("UPDATE posts SET active = 0, postHeader = '[deleted]', postContent = '[deleted]' WHERE postId = ?");
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
    public boolean updatePost(int postId, String postHeader, String postContent) 
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;       
        try
        {
            con = getConnection();       
            ps = con.prepareStatement("UPDATE posts SET postHeader = ?, postContent = ? WHERE postId = ?");
            ps.setString(1, postHeader);
            ps.setString(2, postContent);
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
    public List<Post> getPostsBySearch(String searchResult) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Post> posts = new ArrayList();
        
        try{
            con = getConnection();
            ps = con.prepareStatement("Select u.userName, p.postID, p.userID, p.postHeader, p.postContent, p.postDate, p.media, p.active from posts p inner join users u on u.userId = p.userId where active = 1 AND LOWER(p.postHeader) LIKE LOWER(?) order by postId desc");
            ps.setString(1, "%" + searchResult + "%");
            rs = ps.executeQuery();
            while(rs.next())
            {
                Post p = new Post(rs.getInt("postId"), rs.getInt("userId"), rs.getString("userName"), rs.getString("postHeader"), rs.getString("postContent"), rs.getString("postDate"), rs.getString("media"), rs.getInt("active"), rs.getInt("flagged"));
                posts.add(p);
            }
        }catch (SQLException e) {
            System.out.println("Exception occured in the getPostsBySearch() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the getPostsBySearch() method: " + e.getMessage());
            }
        }
        
        return posts;
    }
}
