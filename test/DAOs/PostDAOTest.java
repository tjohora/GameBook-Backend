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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author TJ
 */
public class PostDAOTest {
    
    public PostDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Test
    public void TestGetAllPosts() throws SQLException
    {
        Post p1 = new Post(1, 10, "MyTitle", "This is a comment.Hello world!", "11/1/2020", "This is media", 1);
        
        ArrayList<Post> expectedResult = new ArrayList();
        expectedResult.add(p1);
        
        
        //Create Mock objects
        Connection mockDBcon = mock(Connection.class);
        PreparedStatement mockPs = mock(PreparedStatement.class);
        ResultSet mockRs = mock(ResultSet.class);
        
        //Populate the mocks
        when(mockDBcon.prepareStatement("Select * from posts where active = 1")).thenReturn(mockPs);
        when(mockPs.executeQuery()).thenReturn(mockRs);
        when(mockRs.next()).thenReturn(true, false);
        
        //Fill in Result Set
        when(mockRs.getInt("postId")).thenReturn(p1.getPostId());
        when(mockRs.getInt("userId")).thenReturn(p1.getUserId());
        when(mockRs.getString("postHeader")).thenReturn(p1.getPostHeader());
        when(mockRs.getString("postContent")).thenReturn(p1.getPostContent());
        when(mockRs.getString("postDate")).thenReturn(p1.getPostDate());
        when(mockRs.getString("media")).thenReturn(p1.getMedia());
        when(mockRs.getInt("active")).thenReturn(p1.getActive());
        
        int numPosts = 1;
        
        PostDAO pd = new PostDAO(mockDBcon);
    }
    
}
