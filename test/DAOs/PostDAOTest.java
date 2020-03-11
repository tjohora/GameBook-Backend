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
        // Create expected results
        Post p1 = new Post(1, 10, "userName1" ,"MyTitle1", "PostHeader1", "11/1/2020", "This is media1", 1);
        Post p2 = new Post(2, 11, "userName2" ,"MyTitle2", "PostHeader2", "11/1/2020", "This is media2", 1);
        Post p3 = new Post(3, 12, "userName3" ,"MyTitle3", "PostHeader3", "11/1/2020", "This is media3", 1);
        ArrayList<Post> expectedResult = new ArrayList();
        expectedResult.add(p1);
        expectedResult.add(p2);
        expectedResult.add(p3);
        
        
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
        assertEquals(expectedResult, result);
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
            
        PostDAO pd = new PostDAO(mockDBcon); 
        List<Post> result = pd.getAllPosts();
        assertEquals(expectedResult, result);
        //System.out.println(result);
    }
    
    /**
     * Test of getOnePost method, of class PostDAO.
     */
    
    @Test
    public void getOnePost_postIdExists_ReturnCorrectPost() throws SQLException
    {
        Post p1 = new Post(1, 10, "userName1" ,"MyTitle1", "PostHeader1", "11/1/2020", "This is media1", 1);
        ArrayList<Post> expectedResult = new ArrayList();
        expectedResult.add(p1);
        
        //Create Mock objects
        Connection mockDBcon = mock(Connection.class);
        PreparedStatement mockPs = mock(PreparedStatement.class);
        ResultSet mockRs = mock(ResultSet.class);
        
        //Populate the mocks
        when(mockDBcon.prepareStatement("Select u.userName, p.postID, p.userID, p.postHeader, p.postContent, p.postDate, p.media, p.active from posts p inner join users u on u.userId = p.userId where active = 1 && postId = ?")).thenReturn(mockPs);
        when(mockPs.executeQuery()).thenReturn(mockRs);
        when(mockRs.next()).thenReturn(true, false);
        
        //Fill in Result Set
        when(mockRs.getInt("postId")).thenReturn(p1.getPostId());
        when(mockRs.getInt("userId")).thenReturn(p1.getUserId());
        when(mockRs.getString("userName")).thenReturn(p1.getUserName());
        when(mockRs.getString("postHeader")).thenReturn(p1.getPostHeader());
        when(mockRs.getString("postContent")).thenReturn(p1.getPostContent());
        when(mockRs.getString("postDate")).thenReturn(p1.getPostDate());
        when(mockRs.getString("media")).thenReturn(p1.getMedia());
        when(mockRs.getInt("active")).thenReturn(p1.getActive());
           
        PostDAO pd = new PostDAO(mockDBcon); 
        List<Post> result = pd.getOnePost(1);
        assertEquals(expectedResult, result);
    }
    
    @Test
    public void getOnePost_PostIdDoesNotExist_ReturnEmptyArray() throws SQLException
    {
        
        ArrayList<Post> expectedResult = new ArrayList();
        
        //Create Mock objects
        Connection mockDBcon = mock(Connection.class);
        PreparedStatement mockPs = mock(PreparedStatement.class);
        ResultSet mockRs = mock(ResultSet.class);
        
        //Populate the mocks
        when(mockDBcon.prepareStatement("Select u.userName, p.postID, p.userID, p.postHeader, p.postContent, p.postDate, p.media, p.active from posts p inner join users u on u.userId = p.userId where active = 1 && postId = ?")).thenReturn(mockPs);
        when(mockPs.executeQuery()).thenReturn(mockRs);
           
        PostDAO pd = new PostDAO(mockDBcon); 
        List<Post> result = pd.getOnePost(-1);
        assertEquals(expectedResult, result);
    }


    /**
     * Test of getPostsByUser method, of class PostDAO.
     */
    @Test
    public void GetPostsByUser_UserIdExists_GetOneResult() throws SQLException {
        System.out.println("getPostsByUser");
        
        int userId = -1;
        Post p1 = new Post(1, 10, "userName1" ,"MyTitle1", "PostHeader1", "11/1/2020", "This is media1", 1);
        ArrayList<Post> expectedResult = new ArrayList();
        expectedResult.add(p1);
        
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
        
        when(dbConn.prepareStatement("Select u.userName, p.postID, p.userID, p.postHeader, p.postContent, p.postDate, p.media, p.active from posts p inner join users u on u.userId = p.userId where active = 1 && userId = ?")).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        
        when(rs.next()).thenReturn(true, false);
        
        when(rs.getInt("postId")).thenReturn(p1.getPostId());
        when(rs.getInt("userId")).thenReturn(p1.getUserId());
        when(rs.getString("userName")).thenReturn(p1.getUserName());
        when(rs.getString("postHeader")).thenReturn(p1.getPostHeader());
        when(rs.getString("postContent")).thenReturn(p1.getPostContent());
        when(rs.getString("postDate")).thenReturn(p1.getPostDate());
        when(rs.getString("media")).thenReturn(p1.getMedia());
        when(rs.getInt("active")).thenReturn(p1.getActive());
        
        PostDAO pd = new PostDAO(dbConn);
        List<Post> result = pd.getPostsByUser(userId);
        assertEquals(expectedResult, result);
    }
    
    @Test
    public void GetPostsByUser_UserIdDoesNotExist_ReturnEmptyArray() throws SQLException {
        ArrayList<Post> expectedResult = new ArrayList();
        
        //Create Mock objects
        Connection mockDBcon = mock(Connection.class);
        PreparedStatement mockPs = mock(PreparedStatement.class);
        ResultSet mockRs = mock(ResultSet.class);
        
        //Populate the mocks
        when(mockDBcon.prepareStatement("Select u.userName, p.postID, p.userID, p.postHeader, p.postContent, p.postDate, p.media, p.active from posts p inner join users u on u.userId = p.userId where active = 1 && userId = ?")).thenReturn(mockPs);
        when(mockPs.executeQuery()).thenReturn(mockRs);
           
        PostDAO pd = new PostDAO(mockDBcon); 
        List<Post> result = pd.getPostsByUser(-1);
        assertEquals(expectedResult, result);
    }

    /**
     * Test of makePost method, of class PostDAO.
     */
    @Test
    public void makePost_AllDetailsProvided_ReturnTrue() throws SQLException  {
        
        boolean expResult = true;
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
        
        when(dbConn.prepareStatement("insert into posts (postID, userId, postHeader, postContent, postDate, media, active) values (null, ?, ?, ?, NOW(), ?, 1)")).thenReturn(ps);

        int userId = 10;
        String postHeader = "Header1";
        String postContent = "Content1";
        int media = 1;
        
        PostDAO pd = new PostDAO(dbConn); 
        boolean result = pd.makePost(userId, postHeader, postContent, media);
        assertEquals(expResult, result);
    }
    
    @Test
    public void makePost_MissingDetails_ReturnTFalse() throws SQLException  {
        
        boolean expResult = false;
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
        
        when(dbConn.prepareStatement("insert into posts (postID, userId, postHeader, postContent, postDate, media, active) values (null, ?, ?, ?, NOW(), ?, 1)")).thenReturn(ps);

        int userId = -1;
        String postHeader = "";
        String postContent = "";
        int media = -1;
        
        PostDAO pd = new PostDAO(dbConn); 
        boolean result = pd.makePost(userId, postHeader, postContent, media);
        assertEquals(expResult, result);
    }
    

    /**
     * Test of deletePost method, of class PostDAO.
     */
    @Test
    public void DeletePost_PostIdProvided_ReturnTrue() throws SQLException {
        
        boolean expResult = true;
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
        
        when(dbConn.prepareStatement("UPDATE post SET status = 0, postHeader = '[deleted]', postContent = '[deleted]' WHERE postId = ?")).thenReturn(ps);

        int postId = 1;
        
        PostDAO pd = new PostDAO(dbConn); 
        boolean result = pd.deletePost(postId);
        assertEquals(expResult, result);
    }
    
    @Test
    public void DeletePost_PostIdNotProvided_ReturnFalse() throws SQLException {
        
        boolean expResult = false;
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
        
        when(dbConn.prepareStatement("UPDATE post SET status = 0, postHeader = '[deleted]', postContent = '[deleted]' WHERE postId = ?")).thenReturn(ps);

        int postId = -1;
        
        PostDAO pd = new PostDAO(dbConn); 
        boolean result = pd.deletePost(postId);
        assertEquals(expResult, result);
    }

    /**
     * Test of updatePost method, of class PostDAO.
     */
    @Test
    public void updatePost_DetailsProvided_ReturnTrue() throws SQLException  {
        
        boolean expResult = true;
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
        
        when(dbConn.prepareStatement("UPDATE post SET content = ? WHERE postId = ?")).thenReturn(ps);

        int postId = 1;
        String title = "newTitle";
        String content = "newContent";
        
        PostDAO pd = new PostDAO(dbConn); 
        boolean result = pd.updatePost(postId, title, content);
        assertEquals(expResult, result);
    }
    
    
    @Test
    public void updatePost_DetailsNotProvided_ReturnTrue() throws SQLException  {
        
        boolean expResult = true;
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
        
        when(dbConn.prepareStatement("UPDATE post SET content = ? WHERE postId = ?")).thenReturn(ps);

        int postId = -1;
        String title = null;
        String content = null;
        
        PostDAO pd = new PostDAO(dbConn); 
        boolean result = pd.updatePost(postId, title, content);
        assertEquals(expResult, result);
    }

}
