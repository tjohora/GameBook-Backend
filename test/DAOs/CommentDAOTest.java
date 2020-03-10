/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Comment;
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
public class CommentDAOTest {

    public CommentDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllComments method, of class CommentDAO.
     */
    @Test
    public void getAllComments_returnAllResults_getThreeReturns() throws SQLException {
        // Create expected results
        Comment c1 = new Comment(1, 1, 1, "Username1", "Content1", "11/1/2020", 1);
        Comment c2 = new Comment(2, 2, 2, "Username2", "Content2", "11/1/2020", 1);
        Comment c3 = new Comment(3, 3, 3, "Username3", "Content3", "11/1/2020", 1);
        ArrayList<Comment> expectedResult = new ArrayList();
        expectedResult.add(c1);
        expectedResult.add(c2);
        expectedResult.add(c3);

        //Create Mock objects
        Connection mockDBcon = mock(Connection.class);
        PreparedStatement mockPs = mock(PreparedStatement.class);
        ResultSet mockRs = mock(ResultSet.class);

        //Populate the mocks
        when(mockDBcon.prepareStatement("Select u.userName, c.userId, c.postID, c.commentID, c.content, c.commentDate, c.active from comments c inner join users u on u.userId = c.userId where active = 1")).thenReturn(mockPs);
        when(mockPs.executeQuery()).thenReturn(mockRs);
        when(mockRs.next()).thenReturn(true, true, true, false);

        //Fill in Result Set
        when(mockRs.getInt("userId")).thenReturn(c1.getUserId(), c2.getUserId(), c3.getUserId());
        when(mockRs.getInt("postID")).thenReturn(c1.getPostID(), c2.getPostID(), c3.getPostID());
        when(mockRs.getInt("commentID")).thenReturn(c1.getCommentID(), c2.getCommentID(), c3.getCommentID());
        when(mockRs.getString("userName")).thenReturn(c1.getUserName(), c2.getUserName(), c3.getUserName());
        when(mockRs.getString("content")).thenReturn(c1.getContent(), c2.getContent(), c3.getContent());
        when(mockRs.getString("commentDate")).thenReturn(c1.getCommentDate(), c2.getCommentDate(), c3.getCommentDate());
        when(mockRs.getInt("active")).thenReturn(c1.getActive(), c2.getActive(), c3.getActive());

        CommentDAO cd = new CommentDAO(mockDBcon);
        List<Comment> result = cd.getAllComments();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getAllComments_returnAllActiveResults_getTwoReturns() throws SQLException {
        // Create expected results
        Comment c1 = new Comment(1, 1, 1, "Username1", "Content1", "11/1/2020", 1);
        Comment c2 = new Comment(2, 2, 2, "Username2", "Content2", "11/1/2020", 1);
        Comment c3 = new Comment(3, 3, 3, "Username3", "Content3", "11/1/2020", 1);
        ArrayList<Comment> expectedResult = new ArrayList();
        expectedResult.add(c1);
        expectedResult.add(c2);

        //Create Mock objects
        Connection mockDBcon = mock(Connection.class);
        PreparedStatement mockPs = mock(PreparedStatement.class);
        ResultSet mockRs = mock(ResultSet.class);

        //Populate the mocks
        when(mockDBcon.prepareStatement("Select u.userName, c.userId, c.postID, c.commentID, c.content, c.commentDate, c.active from comments c inner join users u on u.userId = c.userId where active = 1")).thenReturn(mockPs);
        when(mockPs.executeQuery()).thenReturn(mockRs);
        when(mockRs.next()).thenReturn(true, true, false);

        //Fill in Result Set
        when(mockRs.getInt("userId")).thenReturn(c1.getUserId(), c2.getUserId(), c3.getUserId());
        when(mockRs.getInt("postID")).thenReturn(c1.getPostID(), c2.getPostID(), c3.getPostID());
        when(mockRs.getInt("commentID")).thenReturn(c1.getCommentID(), c2.getCommentID(), c3.getCommentID());
        when(mockRs.getString("userName")).thenReturn(c1.getUserName(), c2.getUserName(), c3.getUserName());
        when(mockRs.getString("content")).thenReturn(c1.getContent(), c2.getContent(), c3.getContent());
        when(mockRs.getString("commentDate")).thenReturn(c1.getCommentDate(), c2.getCommentDate(), c3.getCommentDate());
        when(mockRs.getInt("active")).thenReturn(c1.getActive(), c2.getActive(), c3.getActive());

        CommentDAO cd = new CommentDAO(mockDBcon);
        List<Comment> result = cd.getAllComments();
        assertEquals(expectedResult, result);
    }

    /**
     * Test of getCommentsOfUser method, of class CommentDAO.
     */
    @Test
    public void getCommentsOfUser_UserIdExists_GetOneResult() throws SQLException {
        // Create expected results
        Comment c1 = new Comment(1, 1, 1, "Username1", "Content1", "11/1/2020", 1);
        ArrayList<Comment> expectedResult = new ArrayList();
        expectedResult.add(c1);

        //Create Mock objects
        Connection mockDBcon = mock(Connection.class);
        PreparedStatement mockPs = mock(PreparedStatement.class);
        ResultSet mockRs = mock(ResultSet.class);

        //Populate the mocks
        when(mockDBcon.prepareStatement("Select u.userName, c.userId, c.postID, c.commentID, c.content, c.commentDate, c.active from comments c inner join users u on u.userId = c.userId where active = 1 && userId = ?")).thenReturn(mockPs);
        when(mockPs.executeQuery()).thenReturn(mockRs);
        when(mockRs.next()).thenReturn(true, false);

        //Fill in Result Set
        when(mockRs.getInt("userId")).thenReturn(c1.getUserId());
        when(mockRs.getInt("postID")).thenReturn(c1.getPostID());
        when(mockRs.getInt("commentID")).thenReturn(c1.getCommentID());
        when(mockRs.getString("userName")).thenReturn(c1.getUserName());
        when(mockRs.getString("content")).thenReturn(c1.getContent());
        when(mockRs.getString("commentDate")).thenReturn(c1.getCommentDate());
        when(mockRs.getInt("active")).thenReturn(c1.getActive());

        CommentDAO cd = new CommentDAO(mockDBcon);
        List<Comment> result = cd.getCommentsOfUser(1);
        assertEquals(expectedResult, result);
    }

    @Test
    public void getCommentsOfUser_UserIdDoesNotExist_ReturnEmptyArray() throws SQLException {
        // Create expected results
        ArrayList<Comment> expectedResult = new ArrayList();

        //Create Mock objects
        Connection mockDBcon = mock(Connection.class);
        PreparedStatement mockPs = mock(PreparedStatement.class);
        ResultSet mockRs = mock(ResultSet.class);

        //Populate the mocks
        when(mockDBcon.prepareStatement("Select u.userName, c.userId, c.postID, c.commentID, c.content, c.commentDate, c.active from comments c inner join users u on u.userId = c.userId where active = 1 && userId = ?")).thenReturn(mockPs);
        when(mockPs.executeQuery()).thenReturn(mockRs);
        when(mockRs.next()).thenReturn(false);

        CommentDAO cd = new CommentDAO(mockDBcon);
        List<Comment> result = cd.getCommentsOfUser(-1);
        assertEquals(expectedResult, result);
    }

    /**
     * Test of getCommentsOfPost method, of class CommentDAO.
     */
    @Test
    public void GetCommentsOfPost_PostIdExists_GetOneResult() throws SQLException {
        // Create expected results
        Comment c1 = new Comment(1, 1, 1, "Username1", "Content1", "11/1/2020", 1);
        ArrayList<Comment> expectedResult = new ArrayList();
        expectedResult.add(c1);

        //Create Mock objects
        Connection mockDBcon = mock(Connection.class);
        PreparedStatement mockPs = mock(PreparedStatement.class);
        ResultSet mockRs = mock(ResultSet.class);

        //Populate the mocks
        when(mockDBcon.prepareStatement("Select u.userName, c.userId, c.postID, c.commentID, c.content, c.commentDate, c.active from comments c inner join users u on u.userId = c.userId where active = 1 &&  postId = ? order by commentID desc")).thenReturn(mockPs);
        when(mockPs.executeQuery()).thenReturn(mockRs);
        when(mockRs.next()).thenReturn(true, false);

        //Fill in Result Set
        when(mockRs.getInt("userId")).thenReturn(c1.getUserId());
        when(mockRs.getInt("postID")).thenReturn(c1.getPostID());
        when(mockRs.getInt("commentID")).thenReturn(c1.getCommentID());
        when(mockRs.getString("userName")).thenReturn(c1.getUserName());
        when(mockRs.getString("content")).thenReturn(c1.getContent());
        when(mockRs.getString("commentDate")).thenReturn(c1.getCommentDate());
        when(mockRs.getInt("active")).thenReturn(c1.getActive());

        CommentDAO cd = new CommentDAO(mockDBcon);
        List<Comment> result = cd.getCommentsOfPost(1);
        assertEquals(expectedResult, result);
    }

    @Test
    public void getCommentsOfPost_PostIdDoesNotExist_ReturnEmptyArray() throws SQLException {
        // Create expected results
        ArrayList<Comment> expectedResult = new ArrayList();

        //Create Mock objects
        Connection mockDBcon = mock(Connection.class);
        PreparedStatement mockPs = mock(PreparedStatement.class);
        ResultSet mockRs = mock(ResultSet.class);

        //Populate the mocks
        when(mockDBcon.prepareStatement("Select u.userName, c.userId, c.postID, c.commentID, c.content, c.commentDate, c.active from comments c inner join users u on u.userId = c.userId where active = 1 &&  postId = ? order by commentID desc")).thenReturn(mockPs);
        when(mockPs.executeQuery()).thenReturn(mockRs);
        when(mockRs.next()).thenReturn(false);

        CommentDAO cd = new CommentDAO(mockDBcon);
        List<Comment> result = cd.getCommentsOfPost(-1);
        assertEquals(expectedResult, result);
    }

//    /**
//     * Test of makeCommment method, of class CommentDAO.
//     */
//    @Test
    @Test
    public void makeComment_AllDetailsProvided_ReturnTrue() throws SQLException {

        boolean expResult = true;
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(dbConn.prepareStatement("insert into comments (userId, postID, commentId, content, commentDate, active) values (?, ?, null, ?, NOW(), 1)")).thenReturn(ps);

        int userId = 1;
        int postId = 1;
        String content = "Content1";

        CommentDAO pd = new CommentDAO(dbConn);
        boolean result = pd.makeComment(userId, postId, content);
        assertEquals(expResult, result);
    }
//
//    /**
//     * Test of deleteComment method, of class CommentDAO.
//     */

    @Test
    public void deleteComment_CommentIdProvided_returnTrue() throws SQLException {
        boolean expResult = true;
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(dbConn.prepareStatement("UPDATE comments SET active = 0, content = '[deleted]' WHERE commentID = ?")).thenReturn(ps);

        int postId = 1;

        CommentDAO pd = new CommentDAO(dbConn);
        boolean result = pd.deleteComment(postId);
        assertEquals(expResult, result);
    }

//    /**
//     * Test of updateComment method, of class CommentDAO.
//     */
    @Test
    public void updateComment_CommentIdProvided_returnTrue() throws SQLException {
        boolean expResult = true;
        Connection dbConn = mock(Connection.class);
        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        when(dbConn.prepareStatement("UPDATE comments SET content = ? WHERE commentID = ?")).thenReturn(ps);

        int postId = 1;
        String content = "NewContent";

        CommentDAO pd = new CommentDAO(dbConn);
        boolean result = pd.updateComment(postId, content);
        assertEquals(expResult, result);
    }

}
