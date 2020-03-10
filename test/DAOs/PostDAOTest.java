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

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void getAllPosts_returnAllResults_getThreeReturns() throws SQLException
    {
        Post p1 = new Post(1, 10, "userName1" ,"MyTitle1", "PostHeader1", "11/1/2020", "This is media1", 1);
        Post p2 = new Post(2, 11, "userName2" ,"MyTitle2", "PostHeader2", "11/1/2020", "This is media2", 1);
        Post p3 = new Post(3, 12, "userName3" ,"MyTitle3", "PostHeader3", "11/1/2020", "This is media3", 1);
        ArrayList<Post> expectedResult = new ArrayList();
        expectedResult.add(p1);
        
        //Create Mock objects
        Connection mockDBcon = mock(Connection.class);
        PreparedStatement mockPs = mock(PreparedStatement.class);
        ResultSet mockRs = mock(ResultSet.class);
        
        //Populate the mocks
        when(mockDBcon.prepareStatement("Select u.userName, p.postID, p.userID, p.postHeader, p.postContent, p.postDate, p.media, p.active from posts p inner join users u on u.userId = p.userId where active = 1 order by postId desc")).thenReturn(mockPs);
        when(mockPs.executeQuery()).thenReturn(mockRs);
        when(mockRs.next()).thenReturn(true, true, true,false);
        
        //Fill in Result Set
        when(mockRs.getInt("postId")).thenReturn(p1.getPostId(), p2.getPostId(), p3.getPostId());
        when(mockRs.getInt("userId")).thenReturn(p1.getUserId(), p2.getUserId(), p3.getUserId());
        when(mockRs.getString("userName")).thenReturn(p1.getUserName(), p2.getUserName(), p3.getUserName());
        when(mockRs.getString("postHeader")).thenReturn(p1.getPostHeader(), p2.getPostHeader(), p3.getPostHeader());
        when(mockRs.getString("postContent")).thenReturn(p1.getPostContent(), p2.getPostContent(), p3.getPostContent());
        when(mockRs.getString("postDate")).thenReturn(p1.getPostDate(), p2.getPostDate(), p3.getPostDate());
        when(mockRs.getString("media")).thenReturn(p1.getMedia(), p2.getMedia(), p3.getMedia());
        when(mockRs.getInt("active")).thenReturn(p1.getActive(), p2.getActive(), p3.getActive());
        
        int numPosts = 3;      
        PostDAO pd = new PostDAO(mockDBcon); 
        List<Post> result = pd.getAllPosts();
        assertEquals(numPosts, result.size());
        //System.out.println(result);
    }
    
    @Test
    public void getAllPosts_returnAllActiveResults_getTwoReturns() throws SQLException
    {
        Post p1 = new Post(1, 10, "userName1" ,"MyTitle1", "PostHeader1", "11/1/2020", "This is media1", 1);
        Post p2 = new Post(2, 11, "userName2" ,"MyTitle2", "PostHeader2", "11/1/2020", "This is media2", 1);
        Post p3 = new Post(3, 12, "userName3" ,"MyTitle3", "PostHeader3", "11/1/2020", "This is media3", 0);
        ArrayList<Post> expectedResult = new ArrayList();
        expectedResult.add(p1);
        expectedResult.add(p2);
        
        //Create Mock objects
        Connection mockDBcon = mock(Connection.class);
        PreparedStatement mockPs = mock(PreparedStatement.class);
        ResultSet mockRs = mock(ResultSet.class);
        
        //Populate the mocks
        when(mockDBcon.prepareStatement("Select u.userName, p.postID, p.userID, p.postHeader, p.postContent, p.postDate, p.media, p.active from posts p inner join users u on u.userId = p.userId where active = 1 order by postId desc")).thenReturn(mockPs);
        when(mockPs.executeQuery()).thenReturn(mockRs);
        when(mockRs.next()).thenReturn(true, true, false);
        
        //Fill in Result Set
        when(mockRs.getInt("postId")).thenReturn(p1.getPostId(), p2.getPostId(), p3.getPostId());
        when(mockRs.getInt("userId")).thenReturn(p1.getUserId(), p2.getUserId(), p3.getUserId());
        when(mockRs.getString("userName")).thenReturn(p1.getUserName(), p2.getUserName(), p3.getUserName());
        when(mockRs.getString("postHeader")).thenReturn(p1.getPostHeader(), p2.getPostHeader(), p3.getPostHeader());
        when(mockRs.getString("postContent")).thenReturn(p1.getPostContent(), p2.getPostContent(), p3.getPostContent());
        when(mockRs.getString("postDate")).thenReturn(p1.getPostDate(), p2.getPostDate(), p3.getPostDate());
        when(mockRs.getString("media")).thenReturn(p1.getMedia(), p2.getMedia(), p3.getMedia());
        when(mockRs.getInt("active")).thenReturn(p1.getActive(), p2.getActive(), p3.getActive());
        
        int numPosts = 2;      
        PostDAO pd = new PostDAO(mockDBcon); 
        List<Post> result = pd.getAllPosts();
        assertEquals(numPosts, result.size());
        //System.out.println(result);
    }
    
    @Test
    public void getOnePost_postIdExists_ReturnCorrectPost() throws SQLException
    {
        Post p1 = new Post(1, 10, "userName1" ,"MyTitle1", "PostHeader1", "11/1/2020", "This is media1", 1);
        Post p2 = new Post(2, 11, "userName2" ,"MyTitle2", "PostHeader2", "11/1/2020", "This is media2", 1);
        Post p3 = new Post(3, 12, "userName3" ,"MyTitle3", "PostHeader3", "11/1/2020", "This is media3", 1);
        ArrayList<Post> expectedResult = new ArrayList();
        expectedResult.add(p2);
        
        //Create Mock objects
        Connection mockDBcon = mock(Connection.class);
        PreparedStatement mockPs = mock(PreparedStatement.class);
        ResultSet mockRs = mock(ResultSet.class);
        
        //Populate the mocks
        when(mockDBcon.prepareStatement("Select u.userName, p.postID, p.userID, p.postHeader, p.postContent, p.postDate, p.media, p.active from posts p inner join users u on u.userId = p.userId where active = 1 && postId = ?")).thenReturn(mockPs);
        when(mockPs.executeQuery()).thenReturn(mockRs);
        when(mockRs.next()).thenReturn(true, false);
        
        //Fill in Result Set
        when(mockRs.getInt("postId")).thenReturn(p2.getPostId());
        when(mockRs.getInt("userId")).thenReturn(p2.getUserId());
        when(mockRs.getString("userName")).thenReturn(p2.getUserName());
        when(mockRs.getString("postHeader")).thenReturn(p2.getPostHeader());
        when(mockRs.getString("postContent")).thenReturn(p2.getPostContent());
        when(mockRs.getString("postDate")).thenReturn(p2.getPostDate());
        when(mockRs.getString("media")).thenReturn(p2.getMedia());
        when(mockRs.getInt("active")).thenReturn(p2.getActive());
           
        PostDAO pd = new PostDAO(mockDBcon); 
        List<Post> result = pd.getOnePost(2);
        assertEquals(expectedResult, result);
        System.out.println(result);
    }
    
    @Test
    public void getOnePost_PostIdDoesNotExist_ReturnCorrectPost() throws SQLException
    {
        Post p1 = new Post(1, 10, "userName1" ,"MyTitle1", "PostHeader1", "11/1/2020", "This is media1", 1);
        Post p2 = new Post(2, 11, "userName2" ,"MyTitle2", "PostHeader2", "11/1/2020", "This is media2", 1);
        Post p3 = new Post(3, 12, "userName3" ,"MyTitle3", "PostHeader3", "11/1/2020", "This is media3", 1);
        ArrayList<Post> expectedResult = new ArrayList();
        
        //Create Mock objects
        Connection mockDBcon = mock(Connection.class);
        PreparedStatement mockPs = mock(PreparedStatement.class);
        ResultSet mockRs = mock(ResultSet.class);
        
        //Populate the mocks
        when(mockDBcon.prepareStatement("Select u.userName, p.postID, p.userID, p.postHeader, p.postContent, p.postDate, p.media, p.active from posts p inner join users u on u.userId = p.userId where active = 1 && postId = ?")).thenReturn(mockPs);
        when(mockPs.executeQuery()).thenReturn(mockRs);
           
        PostDAO pd = new PostDAO(mockDBcon); 
        List<Post> result = pd.getOnePost(2);
        assertEquals(expectedResult, result);
        System.out.println(result);
    }

    /**
     * Test of getAllPosts method, of class PostDAO.
     */
//    @Test
//    public void testGetAllPosts() {
//        System.out.println("getAllPosts");
//        PostDAO instance = null;
//        List<Post> expResult = null;
//        List<Post> result = instance.getAllPosts();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getOnePost method, of class PostDAO.
//     */
//    @Test
//    public void testGetOnePost() {
//        System.out.println("getOnePost");
//        int postId = 0;
//        PostDAO instance = null;
//        List<Post> expResult = null;
//        List<Post> result = instance.getOnePost(postId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPostsByUser method, of class PostDAO.
//     */
//    @Test
//    public void testGetPostsByUser() {
//        System.out.println("getPostsByUser");
//        int userId = 0;
//        PostDAO instance = null;
//        List<Post> expResult = null;
//        List<Post> result = instance.getPostsByUser(userId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of makePost method, of class PostDAO.
//     */
//    @Test
//    public void testMakePost() {
//        System.out.println("makePost");
//        int userId = 0;
//        String postHeader = "";
//        String postContent = "";
//        int media = 0;
//        PostDAO instance = null;
//        boolean expResult = false;
//        boolean result = instance.makePost(userId, postHeader, postContent, media);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of deletePost method, of class PostDAO.
//     */
//    @Test
//    public void testDeletePost() {
//        System.out.println("deletePost");
//        int postId = 0;
//        PostDAO instance = null;
//        boolean expResult = false;
//        boolean result = instance.deletePost(postId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updatePost method, of class PostDAO.
//     */
//    @Test
//    public void testUpdatePost() {
//        System.out.println("updatePost");
//        int postId = 0;
//        String title = "";
//        String content = "";
//        PostDAO instance = null;
//        boolean expResult = false;
//        boolean result = instance.updatePost(postId, title, content);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
    


}
